package com.farmers.buyers.modules.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.farmers.buyers.R;
import com.farmers.buyers.modules.home.HomeActivity;
import com.farmers.buyers.modules.onBoarding.OnBoardingActivity;
import com.farmers.buyers.modules.seller.addProduct.AddProductActivity;
import com.farmers.buyers.modules.seller.product.ProductListActivity;
import com.farmers.buyers.storage.SharedPreferenceManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (SharedPreferenceManager.getInstance().getIsLoggedIn()) {
                    Intent intent = new Intent(SplashActivity.this, ProductListActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(SplashActivity.this, OnBoardingActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, 5000);
    }

}