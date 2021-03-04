package com.farmers.seller.modules.setupSellerAccount.documentUpload;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.ImageUtil;
import com.farmers.buyers.modules.login.LoginActivity;
import com.farmers.buyers.modules.seller.product.ProductListActivity;
import com.farmers.seller.modules.ourOrders.OurOrdersActivity;
import com.farmers.seller.modules.setupSellerAccount.storeDetails.StoreSetupExtra;

public class DocumentUploadActivity extends AppCompatActivity implements View.OnClickListener {

    public ImageView img_back;
    public TextView tv_toolbar_name;
    public Button bt_next_document_upload;

    private StoreSetupExtra extra = new StoreSetupExtra();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_upload);

        img_back = findViewById(R.id.tv_setup_seller_back_img);
        tv_toolbar_name = findViewById(R.id.tv_setup_seller_toolbar_name);
        tv_toolbar_name.setText("Setup Seller Account");
        bt_next_document_upload = findViewById(R.id.bt_next_document_upload);

        bt_next_document_upload.setOnClickListener(this);

        init();
    }

    private void init() {
        extra = (StoreSetupExtra) getIntent().getSerializableExtra("storeExtra");
        Log.e("name", extra.getName());
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;

            case R.id.bt_next_document_upload:
                startActivity(new Intent(DocumentUploadActivity.this, OurOrdersActivity.class));
                // finish();
                break;
        }
    }
}