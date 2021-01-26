package com.farmers.buyers.modules.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.farmers.buyers.R;
import com.farmers.buyers.modules.home.HomeActivity;
import com.farmers.buyers.modules.signUp.OtpActivity;
import com.farmers.buyers.modules.signUp.SignUpActivity;

public class LoginActivity extends AppCompatActivity {
    private TextView registerTv, forgotPassword;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       init();
       listener();
    }

    private void listener() {
        registerTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, OtpActivity.class);
                intent.putExtra("fromForgetPassword",true);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                finish();
            }
        });
    }

    private void init() {
        registerTv = findViewById(R.id.login_register_tv);
        forgotPassword = findViewById(R.id.login_forgot_password_tv);
        loginBtn = findViewById(R.id.login_btn);
    }




}