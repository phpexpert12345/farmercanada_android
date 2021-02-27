package com.farmers.buyers.modules.referFriends;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseActivity;

public class ReferFriendsActivity extends BaseActivity implements View.OnClickListener {

    Button bt_share;
    LinearLayout ll_copy;
    TextView tv_referral_code;

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
        ll_copy = findViewById(R.id.ll_copy);
        tv_referral_code = findViewById(R.id.tv_referral_code);
        bt_share = findViewById(R.id.bt_share);
        bt_share.setOnClickListener(this);
        ll_copy.setOnClickListener(this);
    }

    @Override
    public Boolean showToolbar() {
        return true;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.bt_share:
                sendToWhatsapp();
                break;
            case R.id.ll_copy:
                copyText(tv_referral_code.getText().toString().trim());
                break;
        }
    }

    private void sendToWhatsapp() {
        Intent waIntent = new Intent(Intent.ACTION_SEND);
        waIntent.setType("text/plain");
        waIntent.putExtra(Intent.EXTRA_SUBJECT, "Farmer Android App");
        waIntent.putExtra(Intent.EXTRA_TEXT, "Share message");
        startActivity(Intent.createChooser(waIntent, "Share via"));
    }

    private void copyText(String text) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("text label", text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(getApplicationContext(), "Copied", Toast.LENGTH_SHORT).show();
    }
}