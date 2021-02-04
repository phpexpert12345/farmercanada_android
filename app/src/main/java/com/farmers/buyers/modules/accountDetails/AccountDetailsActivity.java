package com.farmers.buyers.modules.accountDetails;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.modules.changePassword.ChangePasswordActivity;
import com.farmers.buyers.modules.profile.EditProfileActivity;

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

        order_track_dialog(this);
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
        startActivity(new Intent(AccountDetailsActivity.this, ChangePasswordActivity.class));
    }

    private void write_about_farm_dialog(Activity activity) {

        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.write_about_farm_dialog, null);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity, R.style.NewDialog);
        alertDialogBuilder.setView(promptsView);

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void notify_me_switches_dialog(Activity activity) {

        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.notify_me_switches_dialog, null);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity, R.style.NewDialog);
        alertDialogBuilder.setView(promptsView);

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void order_track_dialog(Activity activity) {

        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.order_track_dialog, null);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity, R.style.NewDialog);
        alertDialogBuilder.setView(promptsView);

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}