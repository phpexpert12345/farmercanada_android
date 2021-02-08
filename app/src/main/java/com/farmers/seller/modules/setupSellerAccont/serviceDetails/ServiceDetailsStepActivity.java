package com.farmers.seller.modules.setupSellerAccont.serviceDetails;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.farmers.buyers.R;
import com.farmers.seller.modules.setupSellerAccont.storeDetails.StoreDetailsStepActivity;
import com.farmers.seller.modules.setupSellerAccont.verification.VerificationActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ServiceDetailsStepActivity extends FragmentActivity implements OnMapReadyCallback, RadioGroup.OnCheckedChangeListener,
        View.OnClickListener {

    private GoogleMap mMap;
    private SupportMapFragment mapFragment;
    private RadioGroup radio_group;
    private RadioButton radio_seller, radio_buyer;
    public ImageView img_back;
    public TextView tv_toolbar_name;
    public Button bt_next_service_details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_details_step);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        img_back = findViewById(R.id.img_back);
        tv_toolbar_name = findViewById(R.id.tv_toolbar_name);
        tv_toolbar_name.setText("Setup Seller Account");
        bt_next_service_details = findViewById(R.id.bt_next_service_details);
        radio_group = findViewById(R.id.radio_group);
        radio_group.setOnCheckedChangeListener(this);

        img_back.setOnClickListener(this);
        bt_next_service_details.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;

            case R.id.bt_next_service_details:
                startActivity(new Intent(ServiceDetailsStepActivity.this, VerificationActivity.class));
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.radio_pickup:
                Toast.makeText(this, "Pickup", Toast.LENGTH_SHORT).show();
                break;

            case R.id.radio_delivery:
                Toast.makeText(this, "Delivery", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        LatLng washington = new LatLng(28.457523, 77.026344);
        mMap.addMarker(new MarkerOptions().position(washington).title("New Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(washington));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(washington, 14));

    }
}