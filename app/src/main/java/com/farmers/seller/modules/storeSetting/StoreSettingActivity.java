package com.farmers.seller.modules.storeSetting;

import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.seller.modules.workingHour.WorkingHourActivity;

public class StoreSettingActivity extends BaseActivity implements View.OnClickListener {

    private CardView card_change_store, card_service, card_document, card_working_hours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_setting);

        setupToolbar(new ToolbarConfig("Store Setting", true, new View.OnClickListener() {
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
        card_change_store = findViewById(R.id.card_change_store);
        card_service = findViewById(R.id.card_service);
        card_document = findViewById(R.id.card_document);
        card_working_hours = findViewById(R.id.card_working_hours);

        card_change_store.setOnClickListener(this);
        card_service.setOnClickListener(this);
        card_document.setOnClickListener(this);
        card_working_hours.setOnClickListener(this);
    }

    @Override
    public Boolean showToolbar() {
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_change_store:
                break;
            case R.id.card_service:
                break;
            case R.id.card_document:
                break;
            case R.id.card_working_hours:
                startActivity(new Intent(StoreSettingActivity.this, WorkingHourActivity.class));
                break;
        }
    }
}