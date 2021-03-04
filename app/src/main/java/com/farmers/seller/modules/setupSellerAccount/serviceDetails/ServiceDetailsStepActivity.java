package com.farmers.seller.modules.setupSellerAccount.serviceDetails;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.utils.EqualSpacingItemDecoration;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.modules.followers.FollowersViewModel;
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

public class ServiceDetailsStepActivity extends BaseActivity implements OnMapReadyCallback, RadioGroup.OnCheckedChangeListener,
        View.OnClickListener, StoreDeliveryRangeViewHolder.RangeSelectedListener {

    private GoogleMap mMap;
    private SupportMapFragment mapFragment;
    public ImageView img_back;
    public TextView tv_toolbar_name;
    public Button bt_next_service_details;
    private CheckBox pickUpCheck, deliveryCheck;
    private LinearLayout pickUpCheckLl, deliveryCheckLl;
    private RecyclerView recyclerView;
    private SetupStoreDeliveryRangeAdapter adapter;
    private String radius, deliveryType, deliveryCharges, additionalDeliveryCharges, deliveryMsg, pickupCharges, pickupMsg, minimumDeliveryOrder, minimumPickupORder;
    private StoreSetupExtra extra;



    private ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(SetupStoreViewModel .class)) {
                return (T) new SetupStoreViewModel();
            }
            return null;
        }
    };

    private SetupStoreViewModel viewModel = factory.create(SetupStoreViewModel.class);



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

        img_back.setOnClickListener(this);
        bt_next_service_details.setOnClickListener(this);

        viewModel.prepareRageItems();
        init();
    }

    private void init() {
        extra  = (StoreSetupExtra) getIntent().getSerializableExtra("storeExtra");
        adapter = new SetupStoreDeliveryRangeAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView.addItemDecoration(new EqualSpacingItemDecoration(20));
        adapter.updateData(viewModel.items);


        pickUpCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    deliveryType = "1";
                    pickUpCheckLl.setVisibility(View.VISIBLE);
                    deliveryCheckLl.setVisibility(View.GONE);
                }
                else {
                    pickUpCheckLl.setVisibility(View.GONE);
                }
            }
        });

        deliveryCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    deliveryCharges = "2";
                    deliveryCheckLl.setVisibility(View.VISIBLE);
                    pickUpCheckLl.setVisibility(View.GONE);

                }
                else {
                    deliveryCheckLl.setVisibility(View.GONE);
                }
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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

            case R.id.bt_next_service_details:
                Intent intent = new Intent(this, DocumentUploadActivity.class);
                extra.setRadius(radius);
                extra.setDeliveryCharges(deliveryCharges);
                extra.setAdditionalCharges(additionalDeliveryCharges);
                extra.setDeliveryMessage(deliveryMsg);
                extra.setMinimumDeliveryOrder(minimumDeliveryOrder);
                extra.setPickupCharges(pickupCharges);
                extra.setPickupMessage(pickupMsg);
                extra.setMinimumPickupOrder(minimumPickupORder);
                intent.putExtra("storeExtra", extra);
                startActivity(intent);
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

    @Override
    public void onRangeSelectListener(String range, int position) {
        adapter.notifyDataSetChanged();
        adapter.notifyItemChanged(position);
        this.radius = range;

    }
}