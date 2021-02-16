package com.farmers.buyers.modules.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.farmers.buyers.R;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.home.HomeActivity;
import com.farmers.buyers.modules.onBoarding.OnBoardingActivity;
import com.farmers.buyers.storage.SharedPreferenceManager;

public class SplashActivity extends BaseActivity {

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

        SharedPreferenceManager.getInstance().setDeviceId(Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID));

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
                if (appControllerContract.getIsLoggedIn()) {
                    Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
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
        Toast.makeText(this, error, Toast.LENGTH_SHORT ).show();
    }
}