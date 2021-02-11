package com.farmers.buyers.modules.signUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
import com.farmers.buyers.modules.signUp.model.SendOtpApiModel;
import com.farmers.buyers.modules.signUp.model.SignUpApiModel;
import com.farmers.buyers.storage.SharedPreferenceManager;
import com.google.android.material.textfield.TextInputEditText;

public class OtpActivity extends BaseActivity {
    private Button requestOtpBtn;
    private TextInputEditText mobileNumberEt;
    private boolean extra = false;

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
    private MutableLiveData<DataFetchState<SendOtpApiModel>> stateMachine = new MutableLiveData<>();


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
        requestOtpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mobileNumberEt.getText().toString().isEmpty()) {
                    Toast.makeText(OtpActivity.this, "Please enter mobile number", Toast.LENGTH_SHORT).show();
                }
                else { Intent intent = new Intent(OtpActivity.this, SubmitOtpActivity.class);
                if (getIntent().getBooleanExtra("fromForgetPassword", false)){
                    intent.putExtra("fromForgetPassword", true);
                }
                else {
                    intent.putExtra("fromForgetPassword", false);
                }

                intent.putExtra("number", mobileNumberEt.getText().toString());
                startActivity(intent);
                finish();}
            }
        });




    }

    private void init() {
        extra = getIntent().getBooleanExtra("fromForgetPassword", false);
        requestOtpBtn = findViewById(R.id.request_otp_btn);
        mobileNumberEt = findViewById(R.id.otp_number_et);

    }


    private void resendOtp() {
        SharedPreferenceManager.getInstance().setIsComingFrom(0);
        viewModel.resendOtp(stateMachine, mobileNumberEt.getText().toString());
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