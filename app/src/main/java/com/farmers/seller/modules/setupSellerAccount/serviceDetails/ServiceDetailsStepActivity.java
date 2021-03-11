package com.farmers.seller.modules.setupSellerAccount.serviceDetails;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.common.utils.EqualSpacingItemDecoration;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.modules.followers.FollowersViewModel;
import com.farmers.buyers.modules.profile.editProfile.EditProfileActivity;
import com.farmers.seller.modules.setupSellerAccount.SetupStoreViewModel;
import com.farmers.seller.modules.setupSellerAccount.documentUpload.DocumentUploadActivity;
import com.farmers.seller.modules.setupSellerAccount.serviceDetails.view.StoreDeliveryRangeViewHolder;
import com.farmers.seller.modules.setupSellerAccount.storeDetails.StoreSetupExtra;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ServiceDetailsStepActivity extends BaseActivity implements OnMapReadyCallback,
        View.OnClickListener, StoreDeliveryRangeViewHolder.RangeSelectedListener {

    private GoogleMap mMap;
    private SupportMapFragment mapFragment;
    public ImageView img_back;
    public TextView tv_toolbar_name;
    public Button bt_next_service_details;
    private CheckBox pickUpCheck, deliveryCheck;
    private LinearLayout pickUpCheckLl, deliveryCheckLl;
    private RecyclerView recyclerView;
    private EditText deliveryChargesEt, additionalChargesEt, deliveryMessageEt, deliveryMinimumOrderEt, pickUpMinimumOrderEt, pickUpOrderMessageEt;


    private SetupStoreDeliveryRangeAdapter adapter;
    private String radius = "", deliveryCharges, additionalDeliveryCharges, deliveryMsg, pickupCharges, pickupMsg, minimumDeliveryOrder, minimumPickupORder;
    private int deliveryType = 0; // 1 for pickup 2 for delivery
    private int checkStatus = 0;
    private ArrayList<String> orderTypeList = new ArrayList<>();

    private StoreSetupExtra extra = new StoreSetupExtra();

    private SetupStoreViewModel viewModel;

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
        deliveryCheck = findViewById(R.id.store_setup_delivery_check);
        pickUpCheckLl = findViewById(R.id.store_setup_pickup_ll);
        deliveryCheckLl = findViewById(R.id.store_setup_delivery_ll);
        recyclerView = findViewById(R.id.setup_store_delivery_range_recyclerView);
        deliveryChargesEt = findViewById(R.id.store_setup_delivery_charges_et);
        additionalChargesEt = findViewById(R.id.store_setup_additional_charges_et);
        deliveryMessageEt = findViewById(R.id.store_setup_delivery_message_et);
        deliveryMinimumOrderEt = findViewById(R.id.store_setup_minimum_order_et);
        pickUpMinimumOrderEt = findViewById(R.id.store_setup_pickup_minimum_order_et);
        pickUpOrderMessageEt = findViewById(R.id.store_setup_pickup_message_et);

        img_back.setOnClickListener(this);
        bt_next_service_details.setOnClickListener(this);
        extra = (StoreSetupExtra) getIntent().getSerializableExtra("storeExtra");


        bindViewModel();
        viewModel.prepareRageItems();
        init();

    }

    private void bindViewModel() {
        ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                if (modelClass.isAssignableFrom(SetupStoreViewModel.class)) {
                    return (T) new SetupStoreViewModel((StoreSetupExtra) getIntent().getSerializableExtra("storeExtra"));
                }
                return null;
            }
        };
        viewModel = factory.create(SetupStoreViewModel.class);
    }

    private void init() {
        adapter = new SetupStoreDeliveryRangeAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView.addItemDecoration(new EqualSpacingItemDecoration(20));
        adapter.updateData(viewModel.items);


        extra.setPickup_available("0");
        extra.setDelivery_available("0");

        pickUpCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkStatus = 1;
                    deliveryType = 1;
                    orderTypeList.add("0");
                    pickUpCheckLl.setVisibility(View.VISIBLE);
                    extra.setPickup_available("1");
                } else {
                    checkStatus = 0;
                    orderTypeList.remove("0");
                    pickUpCheckLl.setVisibility(View.GONE);
                    extra.setPickup_available("0");
                }
            }
        });

        deliveryCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkStatus = 2;
                    deliveryType = 2;
                    orderTypeList.add("1");
                    deliveryCheckLl.setVisibility(View.VISIBLE);
                    extra.setDelivery_available("1");

                } else {
                    checkStatus = 0;
                    orderTypeList.remove("1");
                    deliveryCheckLl.setVisibility(View.GONE);
                    extra.setDelivery_available("0");
                }
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        bt_next_service_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deliveryCharges = deliveryChargesEt.getText().toString();
                additionalDeliveryCharges = additionalChargesEt.getText().toString();
                deliveryMsg = deliveryMessageEt.getText().toString();
                minimumDeliveryOrder = deliveryMinimumOrderEt.getText().toString();
                minimumPickupORder = pickUpMinimumOrderEt.getText().toString();
                pickupMsg = pickUpOrderMessageEt.getText().toString();

                if (radius.isEmpty()) {
                    Toast.makeText(ServiceDetailsStepActivity.this, "Please choose radius", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (checkStatus == 1) {

                    if (minimumPickupORder.isEmpty()) {
                        Toast.makeText(ServiceDetailsStepActivity.this, "Minimum order can not be empty", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (pickupMsg.isEmpty()) {
                        Toast.makeText(ServiceDetailsStepActivity.this, "Pickup message can not be empty", Toast.LENGTH_SHORT).show();
                        return;
                    }

                } else if (checkStatus == 2) {
                    if (deliveryCharges.isEmpty()) {
                        Toast.makeText(ServiceDetailsStepActivity.this, "Delivery charges can not be empty", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (minimumDeliveryOrder.isEmpty()) {
                        Toast.makeText(ServiceDetailsStepActivity.this, "Minimum order can not be empty", Toast.LENGTH_SHORT).show();
                        return;
                    }

                } else {
                    Toast.makeText(ServiceDetailsStepActivity.this, "Please Select Order type", Toast.LENGTH_SHORT).show();
                    return;
                }

             /*   if (deliveryType == 1) {

                    if (deliveryCharges.isEmpty()) {
                        Toast.makeText(ServiceDetailsStepActivity.this, "Delivery charges can not be empty", Toast.LENGTH_SHORT).show();
                        return;
                    }
               *//*     if (additionalDeliveryCharges.isEmpty()) {
                        Toast.makeText(ServiceDetailsStepActivity.this, "Additional charges can not be empty", Toast.LENGTH_SHORT).show();
                        return;
                    }*//*

                 *//*    if (deliveryMsg.isEmpty()) {
                        Toast.makeText(ServiceDetailsStepActivity.this, "Delivery Message can not be empty", Toast.LENGTH_SHORT).show();
                        return;
                    }*//*
                    if (minimumDeliveryOrder.isEmpty()) {
                        Toast.makeText(ServiceDetailsStepActivity.this, "Minimum order can not be empty", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } else {
                    if (minimumPickupORder.isEmpty()) {
                        Toast.makeText(ServiceDetailsStepActivity.this, "Minimum order can not be empty", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (pickupMsg.isEmpty()) {
                        Toast.makeText(ServiceDetailsStepActivity.this, "Pickup message can not be empty", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }*/

                if (orderTypeList.isEmpty()) {
                    Toast.makeText(ServiceDetailsStepActivity.this, "Please Select Order type", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(ServiceDetailsStepActivity.this, DocumentUploadActivity.class);
                extra.setRadius(radius);
                extra.setDeliveryType(Extensions.convert(orderTypeList));
                extra.setDeliveryCharges(deliveryCharges);
                extra.setAdditionalCharges(additionalDeliveryCharges);
                extra.setDeliveryMessage(deliveryMsg);
                extra.setMinimumDeliveryOrder(minimumDeliveryOrder);
                extra.setPickupCharges(pickupCharges);
                extra.setPickupMessage(pickupMsg);
                extra.setMinimumPickupOrder(minimumPickupORder);
                intent.putExtra("storeExtra", extra);
                startActivity(intent);
            }
        });
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

    @Override
    public void onRangeSelectListener(int position, String range) {
        this.radius = range;
        extra.setRadius(range);
        adapter.notifyDataSetChanged();
        adapter.notifyItemChanged(position);
    }
}