package com.farmers.buyers.modules.signUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.farmers.buyers.R;

public class OtpActivity extends AppCompatActivity {
    private Button requestOtpBtn;
    private boolean extra = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        init();
        listener();
    }

    private void listener() {
        requestOtpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OtpActivity.this, SubmitOtpActivity.class);
                if (getIntent().getBooleanExtra("fromForgetPassword", false)){
                    intent.putExtra("fromForgetPassword", true);
                }
                else {
                    intent.putExtra("fromForgetPassword", false);
                }
                startActivity(intent);
                finish();
            }
        });
    }

    private void init() {
        extra = getIntent().getBooleanExtra("fromForgetPassword", false);
        requestOtpBtn = findViewById(R.id.request_otp_btn);
    }
}