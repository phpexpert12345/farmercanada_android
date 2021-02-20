package com.farmers.buyers.modules.location;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.farmers.buyers.R;
import com.farmers.buyers.modules.login.LoginActivity;
import com.farmers.buyers.storage.GPSTracker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class LocationAccessActivity extends FragmentActivity implements OnMapReadyCallback {

    public GoogleMap mMap;
    public SupportMapFragment mapFragment;
    public Double Latitude = 0.00;
    public Double Longitude = 0.00;
    Marker mCurrLocationMarker;
    private GPSTracker gpsTracker;
    private Button manualLocationButton, allowLocationAccess;
    private TextView tv_current_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_acces);
        gpsTracker = new GPSTracker(this);
        init();
        listener();
    }

    private void init() {
        manualLocationButton = findViewById(R.id.location_access_manual_btn);
        allowLocationAccess = findViewById(R.id.location_access_button);
        tv_current_location = findViewById(R.id.tv_current_location);
        Latitude = gpsTracker.getLatitude();
        Longitude = gpsTracker.getLongitude();

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.clear();
        mMap.getUiSettings().setZoomControlsEnabled(true);
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(Latitude, Longitude);
        mMap.addMarker(new MarkerOptions().position(sydney).title("I am here!"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 17));
        if (checkPermissions()) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
        }
        tv_current_location.setText(gpsTracker.getAddressLine(this));
    }

    private boolean checkPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            requestPermissionsh();
            return false;
        }
    }

    public void requestPermissionsh() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                100);
    }
}