package com.farmers.buyers.modules.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.multidex.MultiDex;

import com.farmers.buyers.R;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.common.utils.AlertHelper;
import com.farmers.buyers.common.utils.OnAlertClickListener;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.home.HomeActivity;
import com.farmers.buyers.modules.login.LoginActivity;
import com.farmers.buyers.modules.onBoarding.OnBoardingActivity;
import com.farmers.buyers.storage.SharedPreferenceManager;
import com.farmers.seller.modules.ourOrders.OurOrdersActivity;
import com.farmers.seller.modules.setupSellerAccount.storeDetails.StoreDetailsStepActivity;
import com.google.firebase.iid.FirebaseInstanceId;

public class SplashActivity extends BaseActivity {

    private String FirebaseToken;
    private AppController appControllerContract = AppController.get();
    private ViewModelProvider.Factory factory = new androidx.lifecycle.ViewModelProvider.Factory() {

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(SplashViewModel.class)) {
                return (T) new SplashViewModel();
            }
            return null;
        }
    };
    private SplashViewModel viewModel = factory.create(SplashViewModel.class);

    private MutableLiveData<DataFetchState<AuthenticationApiModel>> stateMachine = new MutableLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        init();
    }

    private void init() {
        try {
            FirebaseInstanceId.getInstance().getInstanceId()
                    .addOnCompleteListener(task -> {
                        if (!task.isSuccessful()) {
                            return;
                        }
                        FirebaseToken = task.getResult().getToken();
                         SharedPreferenceManager.getInstance().setDeviceId(FirebaseToken);
                        Log.d("HomeActivity ", "FCM TOKEN " + FirebaseToken);
                    });
        } catch (Exception e) {
            e.getMessage();
        }

      //  SharedPreferenceManager.getInstance().setDeviceId(Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID));

        stateMachine.observe(this, new Observer<DataFetchState<AuthenticationApiModel>>() {
            @Override
            public void onChanged(DataFetchState<AuthenticationApiModel> state) {
                switch (state.status) {
                    case LOADING: {
                        loading();
                        break;
                    }
                    case SUCCESS: {
                        success();
                        break;
                    }
                    case ERROR: {
                        error(state.status_message);
                        break;
                    }

                }
            }
        });

        authenticateUser();
    }

    @Override
    public Boolean showToolbar() {
        return false;
    }

    private void authenticateUser() {
        viewModel.authenticateUser(stateMachine);
    }

    private void loading() {
        showLoader();
    }

    private void success() {
        dismissLoader();
//
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e("loginID", appControllerContract.getLoginId());
                if (appControllerContract.getIsLoggedIn()) {
                    switch (appControllerContract.getRole()) {
                        case "Seller": {
                            if (appControllerContract.getIsStoreSetup()) {
                                startActivity(new Intent(SplashActivity.this, OurOrdersActivity.class));
                            } else {
                                startActivity(new Intent(SplashActivity.this, StoreDetailsStepActivity.class));
                            }
                            finish();
                            break;
                        }
                        case "Buyer": {
                            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                            finish();
                            break;
                        }
                    }
                } else {
                    Intent intent = new Intent(SplashActivity.this, OnBoardingActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 5000);

    }

    private void error(String error) {
        dismissLoader();
        AlertHelper.showAlert(this, "Authentication Error", error, true, "Retry", true, "Exit", false, new OnAlertClickListener() {
            @Override
            public void onNegativeBtnClicked() {
                finish();
            }

            @Override
            public void onPositiveBtnClicked() {
                authenticateUser();
            }
        });
//        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }
}