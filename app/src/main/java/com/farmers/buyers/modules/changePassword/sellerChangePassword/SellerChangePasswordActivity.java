package com.farmers.buyers.modules.changePassword.sellerChangePassword;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.modules.referFriends.ReferFriendsActivity;

public class SellerChangePasswordActivity extends BaseActivity implements View.OnClickListener {


    private Button bt_save;
    private ImageView image_back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_change_password);
        init();
    }

    private void init() {
        bt_save = findViewById(R.id.bt_save);
        image_back_button = findViewById(R.id.change_password_back_image);

        image_back_button.setOnClickListener(this);
        bt_save.setOnClickListener(this);
    }

    @Override
    public Boolean showToolbar() {
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.change_password_back_image:
                finish();
                break;
            case R.id.bt_save:
                startActivity(new Intent(SellerChangePasswordActivity.this, ReferFriendsActivity.class));
                break;
        }

    }
}