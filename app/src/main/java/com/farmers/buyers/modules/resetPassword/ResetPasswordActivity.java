package com.farmers.buyers.modules.resetPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.modules.referFriends.ReferFriendsActivity;
import com.google.android.material.tabs.TabLayout;

public class ResetPasswordActivity extends BaseActivity implements View.OnClickListener {

    Button bt_save;
    TextView text_screen_title;
    ImageView image_back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        init();
    }

    private void init() {
        bt_save = findViewById(R.id.bt_save);
        text_screen_title = findViewById(R.id.text_screen_title);
        image_back_button = findViewById(R.id.image_back_button);
        text_screen_title.setText("Change Password");

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
            case R.id.image_back_button:
                finish();
                break;
            case R.id.bt_save:
                startActivity(new Intent(ResetPasswordActivity.this, ReferFriendsActivity.class));
                break;
        }
    }
}