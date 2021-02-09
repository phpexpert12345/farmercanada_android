package com.farmers.seller.modules.setupSellerAccount.documentUpload;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.farmers.buyers.R;

public class DocumentUploadActivity extends AppCompatActivity implements View.OnClickListener {

    public ImageView img_back;
    public TextView tv_toolbar_name;
    public Button bt_next_document_upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_upload);

        img_back = findViewById(R.id.img_back);
        tv_toolbar_name = findViewById(R.id.tv_toolbar_name);
        tv_toolbar_name.setText("Setup Seller Account");
        bt_next_document_upload = findViewById(R.id.bt_next_document_upload);

        img_back.setOnClickListener(this);
        bt_next_document_upload.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;

            case R.id.bt_next_document_upload:
                //  startActivity(new Intent(VerificationActivity.this, DocumentUploadActivity.class));
                break;
        }
    }
}