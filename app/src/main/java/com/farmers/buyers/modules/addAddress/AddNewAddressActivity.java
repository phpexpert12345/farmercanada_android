package com.farmers.buyers.modules.addAddress;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.modules.support.list.SupportActivity;

public class AddNewAddressActivity extends BaseActivity implements View.OnClickListener {

    private Button bt_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_address);

        setupToolbar(new BaseActivity.ToolbarConfig("Add New Address", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        }, false, new BaseActivity.ToolbarMenuConfig(R.drawable.ic_notification, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        })));

        init();
    }

    private void init() {
        bt_submit = findViewById(R.id.bt_submit);

        bt_submit.setOnClickListener(this);
    }

    @Override
    public Boolean showToolbar() {
        return true;
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(AddNewAddressActivity.this, SupportActivity.class));
    }
}