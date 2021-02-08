package com.farmers.seller.modules.setupSellerAccont.storeDetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.farmers.buyers.R;
import com.farmers.seller.modules.setupSellerAccont.serviceDetails.ServiceDetailsStepActivity;

public class StoreDetailsStepActivity extends AppCompatActivity implements View.OnClickListener {

    public ImageView img_back;
    public TextView tv_toolbar_name;
    public Button bt_next_store_details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_details_step);

        img_back = findViewById(R.id.img_back);
        tv_toolbar_name = findViewById(R.id.tv_toolbar_name);
        tv_toolbar_name.setText("Setup Seller Account");
        bt_next_store_details = findViewById(R.id.bt_next_store_details);

        img_back.setOnClickListener(this);
        bt_next_store_details.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;

            case R.id.bt_next_store_details:
                startActivity(new Intent(StoreDetailsStepActivity.this, ServiceDetailsStepActivity.class));
                break;
        }
    }
}