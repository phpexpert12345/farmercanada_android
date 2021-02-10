package com.farmers.seller.modules.broadcastMessage.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseActivity;

public class CreateMessageActivity extends BaseActivity implements View.OnClickListener {

    private Button bt_broadcast_draft, bt_broadcast_publish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);

        setupToolbar(new BaseActivity.ToolbarConfig("Enter Account Details", true, new View.OnClickListener() {
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

    @Override
    public Boolean showToolbar() {
        return true;
    }

    private void init() {
        bt_broadcast_publish = findViewById(R.id.bt_broadcast_publish);
        bt_broadcast_draft = findViewById(R.id.bt_broadcast_draft);

        bt_broadcast_publish.setOnClickListener(this);
        bt_broadcast_draft.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_broadcast_draft:
                break;

            case R.id.bt_broadcast_publish:
                break;
        }
    }
}