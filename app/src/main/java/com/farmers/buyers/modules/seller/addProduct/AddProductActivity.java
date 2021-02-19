package com.farmers.buyers.modules.seller.addProduct;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.modules.seller.product.ProductListActivity;

import java.util.ArrayList;

public class AddProductActivity extends BaseActivity {

    private ImageView micImage;
    private EditText productNameEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        setupToolbar(new ToolbarConfig("Add Product", true, R.drawable.ic_arrow_back_black, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        }, false, null));

        init();
    }

    private void init() {
        micImage = findViewById(R.id.add_product_mic_img);
        productNameEt = findViewById(R.id.add_product_name_et);

        micImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

                try {
                    startActivityForResult(intent, 101);
                } catch (ActivityNotFoundException a) {

                }
            }
        });
    }

    @Override


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 101: {

                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    productNameEt.setText(result.get(0));
                }
                break;
            }

        }
    }

    @Override
    public Boolean showToolbar() {
        return true;
    }
}