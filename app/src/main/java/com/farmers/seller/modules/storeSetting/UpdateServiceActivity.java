package com.farmers.seller.modules.storeSetting;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.farmers.buyers.R;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.common.utils.AlertHelper;
import com.farmers.buyers.common.utils.OnAlertClickListener;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.remote.ApiConstants;
import com.farmers.seller.modules.setupSellerAccount.SetupStoreTransformer;
import com.farmers.seller.modules.setupSellerAccount.serviceDetails.ServiceDetailsStepActivity;
import com.farmers.seller.modules.setupSellerAccount.serviceDetails.model.StoreDeliveryRangeItems;
import com.farmers.seller.modules.storeSetting.model.StoreFarmDetails;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UpdateServiceActivity extends BaseActivity implements OnMapReadyCallback{
    LinearLayout linear_step_services;
    ImageView tv_setup_seller_back_img;
    Button bt_next_service_details;
    TextView tv_setup_seller_toolbar_name;
    CheckBox store_setup_delivery_check,store_setup_pickup_check;
    private GoogleMap mMap;
    private SupportMapFragment mapFragment;
    LinearLayout store_setup_pickup_ll,store_setup_delivery_ll;
    RecyclerView setup_store_delivery_range_recyclerView;
    EditText store_setup_delivery_charges_et,store_setup_additional_charges_et,store_setup_delivery_message_et,store_setup_pickup_message_et;
    TextInputEditText store_setup_minimum_order_et,store_setup_pickup_minimum_order_et;
    StoreFarmDetails storeFarmDetails;
    List<StoreDeliveryRangeItems>list=new ArrayList<>();
    ServiceAdapter serviceAdapter;
    private AppController appController = AppController.get();
    String pickup_available,delivery_available,order_type;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_details_step);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        linear_step_services=findViewById(R.id.linear_step_services);
        tv_setup_seller_back_img=findViewById(R.id.tv_setup_seller_back_img);
        bt_next_service_details=findViewById(R.id.bt_next_service_details);
        tv_setup_seller_toolbar_name=findViewById(R.id.tv_setup_seller_toolbar_name);
        store_setup_delivery_check=findViewById(R.id.store_setup_delivery_check);
        store_setup_pickup_check=findViewById(R.id.store_setup_pickup_check);
        store_setup_pickup_ll=findViewById(R.id.store_setup_pickup_ll);
        store_setup_delivery_ll=findViewById(R.id.store_setup_delivery_ll);
        setup_store_delivery_range_recyclerView=findViewById(R.id.setup_store_delivery_range_recyclerView);
        store_setup_delivery_charges_et=findViewById(R.id.store_setup_delivery_charges_et);
        store_setup_additional_charges_et=findViewById(R.id.store_setup_additional_charges_et);
        store_setup_delivery_message_et=findViewById(R.id.store_setup_delivery_message_et);
        store_setup_pickup_message_et=findViewById(R.id.store_setup_pickup_message_et);
        store_setup_minimum_order_et=findViewById(R.id.store_setup_minimum_order_et);
        store_setup_pickup_minimum_order_et=findViewById(R.id.store_setup_pickup_minimum_order_et);
        tv_setup_seller_toolbar_name.setText("Update Service");
        linear_step_services.setVisibility(View.GONE);
        bt_next_service_details.setText("Update");
        tv_setup_seller_back_img.setOnClickListener(v->{
            finish();
        });
        storeFarmDetails= (StoreFarmDetails) getIntent().getSerializableExtra("store");
        serviceDetails();

    }

    @Override
    public Boolean showToolbar() {
        return false;
    }

    private void serviceDetails(){
        if(storeFarmDetails.pickup_available.equalsIgnoreCase("yes")){
            store_setup_pickup_check.setChecked(true);
            store_setup_pickup_ll.setVisibility(View.VISIBLE);
            pickup_available="1";
        }
        else{
            pickup_available="0";
        }
        if(storeFarmDetails.delivery_available.equalsIgnoreCase("yes")){
            store_setup_delivery_check.setChecked(true);
            store_setup_delivery_ll.setVisibility(View.VISIBLE);
            delivery_available="1";
        }
        else{
            delivery_available="0";
        }
        store_setup_delivery_charges_et.setText(storeFarmDetails.farm_delivery_charge);
        if(!storeFarmDetails.farm_additional_delivery_charge.equalsIgnoreCase("0")) {
            store_setup_additional_charges_et.setText(storeFarmDetails.farm_additional_delivery_charge);
        }
        store_setup_pickup_minimum_order_et.setText(storeFarmDetails.farm_minimum_pickup_charge);
        store_setup_minimum_order_et.setText(storeFarmDetails.farm_minimum_delivery_charge);
        store_setup_pickup_check.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                pickup_available="1";
                store_setup_pickup_ll.setVisibility(View.VISIBLE);
            }
            else{
                pickup_available="0";
                store_setup_pickup_ll.setVisibility(View.GONE);
            }
        });
        store_setup_delivery_check.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                delivery_available="1";
                store_setup_delivery_ll.setVisibility(View.VISIBLE);
            }
            else{
                delivery_available="0";
                store_setup_delivery_ll.setVisibility(View.GONE);
            }
        });
        setRangeAdapter();
        bt_next_service_details.setOnClickListener(v->{
            updateService();
        });



    }
    private void setRangeAdapter(){
        list.addAll(SetupStoreTransformer.getDeliveryRangeItem());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        serviceAdapter=new ServiceAdapter(list,this,storeFarmDetails.farm_delivery_radius+"KM");
        setup_store_delivery_range_recyclerView.setAdapter(serviceAdapter);
        setup_store_delivery_range_recyclerView.setLayoutManager(linearLayoutManager);
    }
    private void updateService(){
        String loc_radius="";
        order_type=pickup_available+","+delivery_available;
        if(serviceAdapter.selectedPosition==-1){
            Toast.makeText(UpdateServiceActivity.this, "Please choose radius", Toast.LENGTH_SHORT).show();
            return;
        }
         if(pickup_available.equalsIgnoreCase("1")){
            if(store_setup_pickup_minimum_order_et.getText().toString().isEmpty()){
                Toast.makeText(UpdateServiceActivity.this, "Pickup Minimum order can not be empty", Toast.LENGTH_SHORT).show();
                return;
            }
            else if(store_setup_pickup_message_et.getText().toString().isEmpty()){
                Toast.makeText(UpdateServiceActivity.this, "Pickup message can not be empty", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        else if(delivery_available.equalsIgnoreCase("1")){
             if (store_setup_delivery_charges_et.getText().toString().isEmpty()) {
                 Toast.makeText(UpdateServiceActivity.this, "Delivery charges can not be empty", Toast.LENGTH_SHORT).show();
                 return;
             }

             if (store_setup_minimum_order_et.getText().toString().isEmpty()) {
                 Toast.makeText(UpdateServiceActivity.this, "Delivery Minimum order can not be empty", Toast.LENGTH_SHORT).show();
                 return;
             }
         }
         else {
             Toast.makeText(UpdateServiceActivity.this, "Please Select Order type", Toast.LENGTH_SHORT).show();
             return;
         }
         if(order_type==null){
             Toast.makeText(UpdateServiceActivity.this, "Please Select Order type", Toast.LENGTH_SHORT).show();
             return;
         }
         else {
showLoader();
             if (serviceAdapter.selectedPosition >= 0) {
                 String title = list.get(serviceAdapter.selectedPosition).getTitle();
                 title = title.substring(0, title.lastIndexOf("KM"));
                 loc_radius = title;
             }

//        { "pickup_available": null, "delivery_available": null, "order_type": null, "pickup_minimum_amount": null, "pickup_message": null, "delivery_map_location_area": null, "delivery_location_distance": null, "delivery_charge": null, "delivery_additional_charge": null, "delivery_minimum_amount": null, "delivery_message": null, "LoginId": null, "auth_key": null }
             String url = ApiConstants.UPDATE_SERVICE + "?auth_key=" + appController.getAuthenticationKey() + "&LoginId=" + appController.getLoginId() + "&pickup_minimum_amount=" + store_setup_pickup_minimum_order_et.getText().toString() + "&delivery_charge=" + store_setup_delivery_charges_et.getText().toString() + "&delivery_additional_charge=" + store_setup_additional_charges_et.getText().toString() + "&delivery_message=" + store_setup_delivery_message_et.getText().toString() + "&delivery_minimum_amount=" + store_setup_minimum_order_et.getText().toString() + "&pickup_minimum_amount=" + store_setup_pickup_minimum_order_et.getText().toString() + "&pickup_message=" + store_setup_pickup_message_et.getText().toString() + "&delivery_location_distance=" + loc_radius + "&pickup_available=" + pickup_available + "&delivery_available=" + delivery_available + "&order_type=" + order_type+"&delivery_map_location_area=";
             Log.i("url", url);
             StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                 @Override
                 public void onResponse(String response) {
                     dismissLoader();
                     try {
                         JSONObject jsonObject=new JSONObject(response);
                         String status_message=jsonObject.getString("status_message");
                         boolean status=jsonObject.getBoolean("status");
                         AlertHelper.showAlert(UpdateServiceActivity.this, "Service Update",
                                 status_message, true, "Ok",
                                 false, "", true, new OnAlertClickListener() {
                                     @Override
                                     public void onNegativeBtnClicked() {
                                         if(status) {

                                             finish();
                                         }
                                     }

                                     @Override
                                     public void onPositiveBtnClicked() {
                                         if(status) {
                                             finish();
                                         }
                                     }
                                 });
                     } catch (JSONException e) {
                         e.printStackTrace();
                     }

                 }
             }, new Response.ErrorListener() {
                 @Override
                 public void onErrorResponse(VolleyError error) {
dismissLoader();
                 }
             });
             RequestQueue requestQueue= Volley.newRequestQueue(this);
             requestQueue.add(stringRequest);
         }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        String lat=storeFarmDetails.farm_latitude;
        String longt=storeFarmDetails.farm_longitude;
        LatLng washington = new LatLng(28.457523, 77.026344);
        mMap.addMarker(new MarkerOptions().position(washington).title("New Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(washington));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(washington, 14));
    }
}
