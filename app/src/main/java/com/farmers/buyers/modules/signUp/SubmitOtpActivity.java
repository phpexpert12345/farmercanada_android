package com.farmers.buyers.modules.signUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.farmers.buyers.R;
import com.farmers.buyers.modules.forgotPassword.ForgotPassword;
import com.farmers.buyers.modules.login.LoginActivity;

public class SubmitOtpActivity extends AppCompatActivity {

    private TextView resendOtp;
    private Button submitOtp;
    private Boolean extra = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_otp);

        init();
        listener();
    }

    private void listener() {
        submitOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (extra) {
                    startActivity(new Intent(SubmitOtpActivity.this, ForgotPassword.class));
                }
                else {
                    startActivity(new Intent(SubmitOtpActivity.this, LoginActivity.class));
                }
                finish();
            }
        });

        resendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SubmitOtpActivity.this, OtpActivity.class));
                finish();
            }
        });
    }

    private void init() {
        extra = getIntent().getBooleanExtra("fromForgetPassword", false);
        resendOtp = findViewById(R.id.submit_otp_resend_tv);
        submitOtp = findViewById(R.id.submit_otp_btn);

    }
}