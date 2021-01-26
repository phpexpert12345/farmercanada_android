package com.farmers.buyers.modules.location;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.farmers.buyers.R;
import com.farmers.buyers.modules.login.LoginActivity;

public class LocationAccessActivity extends AppCompatActivity {

    private Button manualLocationButton, allowLocationAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_acces);
        init();
        listener();
    }

    private void init() {
        manualLocationButton = findViewById(R.id.location_access_manual_btn);
        allowLocationAccess = findViewById(R.id.location_access_button);
    }

    private void listener() {
        manualLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LocationAccessActivity.this, ManualLocationActivity.class));
            }
        });

        allowLocationAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LocationAccessActivity.this, LoginActivity.class));
            }
        });
    }
}