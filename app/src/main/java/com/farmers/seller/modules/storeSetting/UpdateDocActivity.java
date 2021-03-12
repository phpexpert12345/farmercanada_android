package com.farmers.seller.modules.storeSetting;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.farmers.buyers.R;
import com.google.android.material.textfield.TextInputEditText;

public class UpdateDocActivity extends AppCompatActivity {
    LinearLayout linear_step_doc;
    ImageView tv_setup_seller_back_img;
    TextView tv_setup_seller_toolbar_name;
    TextInputEditText setup_seller_store_name_et;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_upload);
        linear_step_doc=findViewById(R.id.linear_step_doc);
        tv_setup_seller_back_img=findViewById(R.id.tv_setup_seller_back_img);
        tv_setup_seller_toolbar_name=findViewById(R.id.tv_setup_seller_toolbar_name);
        linear_step_doc.setVisibility(View.GONE);
    }
}
