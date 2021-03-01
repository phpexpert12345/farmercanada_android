package com.farmers.buyers.modules.signUp;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.changePassword.ChangePasswordActivity;
import com.farmers.buyers.modules.forgotPassword.ForgotPassword;
import com.farmers.buyers.modules.forgotPassword.ForgotPasswordViewModel;
import com.farmers.buyers.modules.login.model.LoginApiModel;
import com.google.android.material.textfield.TextInputEditText;

public class OtpActivity extends BaseActivity {

    private Button requestOtpBtn;
    private TextInputEditText mobileNumberEt;
    private boolean extra = false;

    private ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(ForgotPasswordViewModel.class)) {
                return (T) new ForgotPasswordViewModel();
            }
            return null;
        }
    };

    private ForgotPasswordViewModel viewModel = factory.create(ForgotPasswordViewModel.class);
    private MutableLiveData<DataFetchState<LoginApiModel>> stateMachine = new MutableLiveData<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        init();
        listener();
    }

    @Override
    public Boolean showToolbar() {
        return false;
    }

    private void listener() {
        requestOtpBtn.setOnClickListener(view -> {
            if (mobileNumberEt.getText().toString().isEmpty()) {
                Toast.makeText(OtpActivity.this, "Please enter mobile number", Toast.LENGTH_SHORT).show();
            } else {
                viewModel.doVerifyMobileForgotPassword(stateMachine, mobileNumberEt.getText().toString(), 1);
            }
        });


    }

    private void init() {
        extra = getIntent().getBooleanExtra("fromForgetPassword", false);
        requestOtpBtn = findViewById(R.id.request_otp_btn);
        mobileNumberEt = findViewById(R.id.otp_number_et);

        stateMachine.observe(this, new Observer<DataFetchState<LoginApiModel>>() {
            @Override
            public void onChanged(DataFetchState<LoginApiModel> dataFetchState) {
                switch (dataFetchState.status) {
                    case ERROR: {
                        dismissLoader();
                        Toast.makeText(OtpActivity.this, dataFetchState.status_message, Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case LOADING: {
                        showLoader();
                        break;
                    }
                    case SUCCESS: {
                        dismissLoader();
                        //Toast.makeText(OtpActivity.this, dataFetchState.data.getStatus_message() + " -> " + dataFetchState.data.getData().getMobile_OTP(), Toast.LENGTH_LONG).show();
                        Toast.makeText(OtpActivity.this, dataFetchState.status_message, Toast.LENGTH_SHORT).show();
                        if (getIntent().getStringExtra("FROM").equalsIgnoreCase("Login")) {
                            Intent intent = new Intent(OtpActivity.this, ForgotPassword.class);
                            if (getIntent().getBooleanExtra("fromForgetPassword", false)) {
                                intent.putExtra("fromForgetPassword", true);
                            } else {
                                intent.putExtra("fromForgetPassword", false);
                            }

                            intent.putExtra("USER_ID", dataFetchState.data.getData().getLoginId());
                            intent.putExtra("otp", dataFetchState.data.getData().getMobile_OTP());
                            startActivity(intent);
                            finish();
                        } else if (getIntent().getStringExtra("FROM").equalsIgnoreCase("My Profile")) {
                            Intent intent = new Intent(OtpActivity.this, ChangePasswordActivity.class);
                            intent.putExtra("USER_ID", dataFetchState.data.getData().getLoginId());
                            intent.putExtra("otp", dataFetchState.data.getData().getMobile_OTP());
                            startActivity(intent);
                            finish();
                        }
                        break;
                    }
                }
            }
        });
    }

    private void loading() {
        showLoader();
    }

    private void success() {
        dismissLoader();
    }

    private void error(String error) {
        dismissLoader();
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}