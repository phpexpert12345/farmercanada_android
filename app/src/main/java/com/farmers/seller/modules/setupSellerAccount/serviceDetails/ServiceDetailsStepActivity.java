package com.farmers.seller.modules.setupSellerAccount.serviceDetails;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.seller.modules.setupSellerAccount.documentUpload.DocumentUploadActivity;
import com.farmers.seller.modules.setupSellerAccount.verification.VerificationActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ServiceDetailsStepActivity extends BaseActivity implements OnMapReadyCallback, RadioGroup.OnCheckedChangeListener,
        View.OnClickListener {

    private GoogleMap mMap;
    private SupportMapFragment mapFragment;
    public ImageView img_back;
    public TextView tv_toolbar_name;
    public Button bt_next_service_details;

    CheckBox pickUpCheck, deliveryCheck;
    LinearLayout pickUpCheckLl, deliveryCheckLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_details_step);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        img_back = findViewById(R.id.tv_setup_seller_back_img);
        tv_toolbar_name = findViewById(R.id.tv_setup_seller_toolbar_name);
        tv_toolbar_name.setText("Setup Seller Account");
        bt_next_service_details = findViewById(R.id.bt_next_service_details);
        pickUpCheck = findViewById(R.id.store_setup_pickup_check);
        deliveryCheck = findViewById(R.id.store_setup_pickup_check);
        pickUpCheckLl = findViewById(R.id.store_setup_pickup_ll);
        deliveryCheckLl = findViewById(R.id.store_setup_delivery_ll);

        img_back.setOnClickListener(this);
        bt_next_service_details.setOnClickListener(this);


        init();
    }

    private void init() {
    }


    @Override
    public Boolean showToolbar() {
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;

            case R.id.bt_next_service_details:
                startActivity(new Intent(ServiceDetailsStepActivity.this, DocumentUploadActivity.class));
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