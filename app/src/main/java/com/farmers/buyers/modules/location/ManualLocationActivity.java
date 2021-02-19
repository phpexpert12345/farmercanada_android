package com.farmers.buyers.modules.location;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.farmers.buyers.R;
import com.farmers.buyers.modules.login.LoginActivity;

public class ManualLocationActivity extends AppCompatActivity {

    private Button confirmLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_location);
        init();
        listener();

    }

    private void init() {
        confirmLocation = findViewById(R.id.manual_location_confirm_btn);
    }

    private void listener() {
        confirmLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManualLocationActivity.this, LoginActivity.class));
            }
        });
    }
}