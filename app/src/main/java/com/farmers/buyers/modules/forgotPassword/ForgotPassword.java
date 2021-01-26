package com.farmers.buyers.modules.forgotPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseActivity;

public class ForgotPassword extends BaseActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        setupToolbar(new ToolbarConfig("Reset Password", true, new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ForgotPassword.super.onBackPressed();
            }
        }, false, null));
    }

    @Override
    public Boolean showToolbar() {
        return true;
    }
}