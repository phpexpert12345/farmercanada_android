package com.farmers.seller.modules.referFriends;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.seller.modules.editProfile.SellerEditProfileActivity;

public class SellerReferFriendsActivity extends BaseActivity implements View.OnClickListener {

    Button bt_share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_refer_friends);

        setupToolbar(new ToolbarConfig("Refer a Friends", true, new View.OnClickListener() {
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
        bt_share = findViewById(R.id.bt_share);
        bt_share.setOnClickListener(this);
    }

    @Override
    public Boolean showToolbar() {
        return true;
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(SellerReferFriendsActivity.this, SellerEditProfileActivity.class));
    }
}