package com.farmers.buyers.modules.referFriends;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseActivity;

public class ReferFriendsActivity extends BaseActivity implements View.OnClickListener {

    Button bt_share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer_friends);
        setupToolbar(new ToolbarConfig("Refer Friends", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        }, true, new ToolbarMenuConfig(R.drawable.ic_notification, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        })));

        init();
    }

    private void init() {
        bt_share = findViewById(R.id.bt_share);
        bt_share.setOnClickListener(this);
    }

    @Override
    public Boolean showToolbar() {
        return true;
    }

    @Override
    public void onClick(View view) {
        sendToWhatsapp();
    }

    private void sendToWhatsapp() {
        Intent waIntent = new Intent(Intent.ACTION_SEND);
        waIntent.setType("text/plain");
        waIntent.putExtra(Intent.EXTRA_SUBJECT, "Farmer Android App");
        waIntent.putExtra(Intent.EXTRA_TEXT, "Share message");
        startActivity(Intent.createChooser(waIntent, "Share via"));
    }
}