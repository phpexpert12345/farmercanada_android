package com.farmers.seller.modules.editProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.farmers.buyers.R;
import com.farmers.seller.modules.broadcastMessage.activity.BroadcastMessageActivity;

public class SellerEditProfileActivity extends AppCompatActivity implements View.OnClickListener {

    Button edit_profile_save_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_edit_profile);

        edit_profile_save_btn = findViewById(R.id.edit_profile_save_btn);

        edit_profile_save_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(SellerEditProfileActivity.this, BroadcastMessageActivity.class));
    }
}