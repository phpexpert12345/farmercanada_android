package com.farmers.buyers.modules.onBoarding;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import com.farmers.buyers.R;
import com.farmers.buyers.common.utils.Util;
import com.farmers.buyers.modules.location.LocationAccessActivity;

public class OnBoardingActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TextView skipButton;
    private Button getStartButton;
    private TextView pageCount;
    private OnBoardingAdapter adapter;
    private int[] layouts;
    private String[] PERMISSIONS;
    private int page = 0;
    private Handler handler;
    private Runnable runnable;
    private int count = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        init();
        listener();
    }

    private void init() {
        handler = new Handler();
        viewPager = findViewById(R.id.on_boarding_viewPager);
        skipButton = findViewById(R.id.on_boarding_skip_btn);
        getStartButton = findViewById(R.id.on_boarding_get_start_btn);
        pageCount = findViewById(R.id.page_count);
        PERMISSIONS = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
        layouts = new int[]{R.layout.on_boarding_fragment_layout_one, R.layout.on_boarding_fragment_layout_two, R.layout.on_boarding_fragment_layout_three, R.layout.on_boarding_fragment_layout_four};
        if (!Util.hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, 1);
        }
        adapter = new OnBoardingAdapter(this, layouts);
        viewPager.setAdapter(adapter);

//        runnable = new Runnable() {
//            public void run() {
//                if (adapter.getCount() == page) {
//                    handler.removeCallbacks(this);
//
//                } else {
//                    page++;
//                }
//                viewPager.setCurrentItem(page, true);
//                handler.postDelayed(this, 5000);
//            }
//        };
    }

    private void listener() {

        getStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OnBoardingActivity.this, LocationAccessActivity.class));
                finish();
            }
        });
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(page==0) {
                    startActivity(new Intent(OnBoardingActivity.this, LocationAccessActivity.class));
                    finish();
                }
                else{
                    page++;
                    viewPager.setCurrentItem(page, true);

                }
            }
        });

        ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {

                pageCount.setText(position + 1 + "/" + layouts.length);

                if (position == layouts.length - 1) {
                    getStartButton.setVisibility(View.VISIBLE);
                    skipButton.setVisibility(View.GONE);
                } else if(position==0) {
                    skipButton.setVisibility(View.VISIBLE);
                    skipButton.setText("Skip");
                    getStartButton.setVisibility(View.GONE);
                }
                else{
                    skipButton.setVisibility(View.VISIBLE);
                    skipButton.setText("Next");
                    getStartButton.setVisibility(View.GONE);
                }

                page = position;

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        };

        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

    }

    @Override
    protected void onResume() {
        super.onResume();
//        handler.postDelayed(runnable, 5000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}