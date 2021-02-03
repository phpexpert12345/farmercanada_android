package com.farmers.buyers.modules.accountDetails;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.modules.resetPassword.ResetPasswordActivity;
import com.google.android.material.tabs.TabLayout;

public class AccountDetailsActivity extends BaseActivity implements View.OnClickListener {

    private Button bt_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);

        setupToolbar(new ToolbarConfig("Enter Account Details", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        }, false, new ToolbarMenuConfig(R.drawable.ic_notification, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        })));

        init();
    }

    private void init() {
        bt_save = findViewById(R.id.bt_save);

        bt_save.setOnClickListener(this);
    }

    @Override
    public Boolean showToolbar() {
        return true;
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(AccountDetailsActivity.this, ResetPasswordActivity.class));
    }
}