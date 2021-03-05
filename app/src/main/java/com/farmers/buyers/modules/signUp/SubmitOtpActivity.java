package com.farmers.buyers.modules.signUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.farmers.buyers.R;
import com.farmers.buyers.app.App;
import com.farmers.buyers.common.utils.GenericTextWatcher;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.forgotPassword.ForgotPassword;
import com.farmers.buyers.modules.home.HomeActivity;
import com.farmers.buyers.modules.login.LoginActivity;
import com.farmers.buyers.modules.signUp.model.SendOtpApiModel;
import com.farmers.buyers.modules.signUp.model.VerifyOtpApiModel;
import com.farmers.buyers.storage.SharedPreferenceManager;
import com.farmers.seller.modules.setupSellerAccount.storeDetails.StoreDetailsStepActivity;

public class SubmitOtpActivity extends BaseActivity {

    private TextView resendOtpTv, headerNumberTv;
    private Button submitOtp;
    private Boolean extra = false;
    private EditText otp_textbox_one;
    private EditText otp_textbox_two;
    private EditText otp_textbox_three;
    private EditText otp_textbox_four;
    private LinearLayout ll_resend_otp;
    private TextView tv_otpReadRemainingTime;

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
    private MutableLiveData<DataFetchState<VerifyOtpApiModel>> verifyOtpStateMachine = new MutableLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_otp);

        init();
        listener();
    }

    @Override
    public Boolean showToolbar() {
        return false;
    }

    private void listener() {
        submitOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String otp = otp_textbox_one.getText().toString() + otp_textbox_two.getText().toString() + otp_textbox_three.getText().toString() + otp_textbox_four.getText().toString();
                if (extra) {
                    viewModel.verifyRegistrationOtp(verifyOtpStateMachine, otp,
                            getIntent().getStringExtra("USER_ID"));
                } else {
                    viewModel.verifyRegistrationOtp(verifyOtpStateMachine, otp,
                            getIntent().getStringExtra("USER_ID"));
                }
            }
        });

        resendOtpTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resendOtp();
            }
        });
    }

    private void init() {
        extra = getIntent().getBooleanExtra("fromForgetPassword", false);
        resendOtpTv = findViewById(R.id.submit_otp_resend_tv);
        submitOtp = findViewById(R.id.submit_otp_btn);
        headerNumberTv = findViewById(R.id.numberTv);

        if (getIntent().getStringExtra("fromSignUp") != null) {
            headerNumberTv.setText("Please enter the OTP code sent to " + SharedPreferenceManager.getInstance().getSignUpPhoneNumber());
        } else {
            headerNumberTv.setText("Please enter the OTP code sent to " + getIntent().getStringExtra("number"));
        }

        otp_textbox_one = findViewById(R.id.otp_edit_box1);
        otp_textbox_two = findViewById(R.id.otp_edit_box2);
        otp_textbox_three = findViewById(R.id.otp_edit_box3);
        otp_textbox_four = findViewById(R.id.otp_edit_box4);
        ll_resend_otp = findViewById(R.id.ll_resend_otp);
        tv_otpReadRemainingTime = findViewById(R.id.tv_otpReadRemainingTime);


        EditText[] edit = {otp_textbox_one,
                otp_textbox_two, otp_textbox_three, otp_textbox_four};

        otp_textbox_one.addTextChangedListener(new GenericTextWatcher(otp_textbox_one, edit));
        otp_textbox_two.addTextChangedListener(new GenericTextWatcher(otp_textbox_two, edit));
        otp_textbox_three.addTextChangedListener(new GenericTextWatcher(otp_textbox_three, edit));
        otp_textbox_four.addTextChangedListener(new GenericTextWatcher(otp_textbox_four, edit));


        stateMachine.observe(this, new Observer<DataFetchState<SendOtpApiModel>>() {
            @Override
            public void onChanged(DataFetchState<SendOtpApiModel> sendOtpApiModelDataFetchState) {
                switch (sendOtpApiModelDataFetchState.status) {
                    case LOADING: {
                        loading();
                        break;
                    }

                    case SUCCESS: {
                        success();
                    }

                    case ERROR: {
                        error(sendOtpApiModelDataFetchState.status_message);
                    }

                }
            }
        });
        verifyOtpStateMachine.observe(this, new Observer<DataFetchState<VerifyOtpApiModel>>() {
            @Override
            public void onChanged(DataFetchState<VerifyOtpApiModel> verifyOtpApiModelDataFetchState) {
                switch (verifyOtpApiModelDataFetchState.status) {
                    case LOADING: {
                        loading();
                        break;
                    }

                    case SUCCESS: {
                        dismissLoader();
                        if (extra) {
                            startActivity(new Intent(SubmitOtpActivity.this, ForgotPassword.class));
                        } else {
                            if (verifyOtpApiModelDataFetchState.data.getData().getAccountType().equals("Seller")) {
                                startActivity(new Intent(SubmitOtpActivity.this, StoreDetailsStepActivity.class));
                            }
                            else {
                                startActivity(new Intent(SubmitOtpActivity.this, HomeActivity.class));
                            }
                        }
                        App.finish_activity=true;
                        finish();
                    }

                    case ERROR: {
                        error(verifyOtpApiModelDataFetchState.status_message);
                    }

                }
            }
        });

        startTimer();
    }

    private void loading() {
        showLoader();
    }

    private void success() {
        dismissLoader();
        startActivity(new Intent(this, HomeActivity.class));
    }

    private void error(String error) {
        dismissLoader();
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    private void resendOtp() {
        SharedPreferenceManager.getInstance().setIsComingFrom(0);
        viewModel.resendOtp(stateMachine, getIntent().getStringExtra("number"));
        startTimer();
    }

    @Override
    public void onBackPressed() {
        if (getIntent().getBooleanExtra("fromSignUp", false)) {
            startActivity(new Intent(SubmitOtpActivity.this, SignUpActivity.class));
            finish();
        } else {
            super.onBackPressed();
        }
    }

    private void startTimer() {
        ll_resend_otp.setVisibility(View.GONE);
        tv_otpReadRemainingTime.setVisibility(View.VISIBLE);
        countDownTimer.start();
    }

    CountDownTimer countDownTimer = new CountDownTimer(1 * 30000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            int seconds = (int) (millisUntilFinished / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;
            tv_otpReadRemainingTime.setText("" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
        }

        @Override
        public void onFinish() {
            ll_resend_otp.setVisibility(View.VISIBLE);
            tv_otpReadRemainingTime.setVisibility(View.GONE);
        }
    };
}