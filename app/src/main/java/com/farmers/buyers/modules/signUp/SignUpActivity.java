package com.farmers.buyers.modules.signUp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.farmers.buyers.R;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.home.HomeActivity;
import com.farmers.buyers.modules.signUp.model.SignUpApiModel;
import com.farmers.buyers.modules.signUp.model.SignUpRequestParams;
import com.farmers.buyers.storage.GPSTracker;
import com.farmers.seller.modules.setupSellerAccount.storeDetails.StoreDetailsStepActivity;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SignUpActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    private CheckBox termsConditionTv;
    private Button signUpBtn;
    private TextInputEditText nameEt, emailEt, numberEt, passwordEt, signUp_referral_et;
    private RadioGroup user_type_radio_group;
    public GPSTracker gpsTracker;
    private RadioButton radio_seller, radio_buyer;
    private String account_country = "";
    private Integer account_type = 1;//Buyer = 1 & Seller = 2
    protected Context context;
    private boolean check = false;
    private String account_city, account_state, account_address, account_long, account_lat, device_id, device_platform, account_phone_code;

    private ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(SignUpViewModel.class)) {
                return (T) new SignUpViewModel();
            }
            return null;
        }
    };

    private SignUpViewModel viewModel = factory.create(SignUpViewModel.class);
    private MutableLiveData<DataFetchState<SignUpApiModel>> stateMachine = new MutableLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        init();
        listener();
    }

    @Override
    public Boolean showToolbar() {
        return false;
    }

    private void init() {
        termsConditionTv = findViewById(R.id.sign_up_terms_condition_tv);
        signUpBtn = findViewById(R.id.sign_up_submit_btn);
        nameEt = findViewById(R.id.signUp_name_et);
        emailEt = findViewById(R.id.signUp_email_et);
        numberEt = findViewById(R.id.signUp_mobile_et);
        passwordEt = findViewById(R.id.signUp_password_et);
        signUp_referral_et = findViewById(R.id.signUp_referral_et);
        user_type_radio_group = findViewById(R.id.user_type_radio_group);
        user_type_radio_group.setOnCheckedChangeListener(this);

        gpsTracker = new GPSTracker(SignUpActivity.this);

        if (!checkPermissions()) {
            requestPermissionsh();
        }

        stateMachine.observe(this, new Observer<DataFetchState<SignUpApiModel>>() {
            @Override
            public void onChanged(DataFetchState<SignUpApiModel> signUpApiModelDataFetchState) {
                switch (signUpApiModelDataFetchState.status) {
                    case LOADING: {
                        loading();
                        break;
                    }

                    case SUCCESS: {
                        success(signUpApiModelDataFetchState.status_message,
                                signUpApiModelDataFetchState.data.getData().getMobile_OTP(),
                                signUpApiModelDataFetchState.data.getData().getLoginId());
                        break;
                    }

                    case ERROR: {
                        error(signUpApiModelDataFetchState.status_message);
                        break;
                    }
                }
            }
        });
        termsConditionTv.setOnCheckedChangeListener((compoundButton, b) -> check = b);
    }

    private boolean checkPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            requestPermissionsh();
            return false;
        }
    }

    public void requestPermissionsh() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                100);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.radio_seller:
                account_type = 2;
                startActivity(new Intent(SignUpActivity.this, StoreDetailsStepActivity.class));
                break;

            case R.id.radio_buyer:
                account_type = 1;
                Toast.makeText(this, "Buyer", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void loading() {
        showLoader();
    }

    private void success(String msg, String mobile_otp, String loginId) {
        dismissLoader();
        // Toast.makeText(this, msg + " -> " + mobile_otp, Toast.LENGTH_LONG).show();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SignUpActivity.this, SubmitOtpActivity.class);
        intent.putExtra("fromSignUp", true);
        intent.putExtra("USER_ID", loginId);
        startActivity(intent);
        finish();
    }

    private void error(String error) {
        dismissLoader();
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    private void listener() {
        signUpBtn.setOnClickListener(view -> {
            if (check) {
                doSignUp();
            } else {
                Toast.makeText(SignUpActivity.this, "Please check T&C", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void doSignUp() {
        SignUpRequestParams signUpRequestParams = new SignUpRequestParams(nameEt.getText().toString(), numberEt.getText().toString(),
                emailEt.getText().toString(), passwordEt.getText().toString(), signUp_referral_et.getText().toString().trim(), account_type,
                gpsTracker.getCountryName(this), gpsTracker.getAdminArea(this),
                gpsTracker.getLocality(this), gpsTracker.getAddressLine(this),
                String.valueOf(gpsTracker.getLatitude()), String.valueOf(gpsTracker.getLongitude()),
                "91", AppController.get().getDeviceId(),
                "Android", AppController.get().getAuthenticationKey());

        viewModel.doSignUp(stateMachine, signUpRequestParams);
    }
}