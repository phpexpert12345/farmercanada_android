package com.farmers.seller.modules.storeSetting;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
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
import androidx.core.app.ActivityCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.farmers.buyers.R;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.common.utils.AlertHelper;
import com.farmers.buyers.common.utils.CameraProvider;
import com.farmers.buyers.common.utils.OnAlertClickListener;
import com.farmers.buyers.common.utils.Util;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.remote.ApiConstants;
import com.farmers.buyers.storage.LatLngToGeoLocation;
import com.farmers.seller.modules.broadcastMessage.activity.CreateMessageActivity;
import com.farmers.seller.modules.setupSellerAccount.storeDetails.StoreDetailsStepActivity;
import com.farmers.seller.modules.storeSetting.model.StoreFarmDetails;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import kotlin.Unit;

public class ChangeStoreActivity extends BaseActivity {
    LinearLayout linear_step,store_detail_choose_image_ll;
    ImageView tv_setup_seller_back_img,tv_setup_seller_log_out_img;
    TextView tv_setup_seller_toolbar_name;
    CheckBox store_setup_homemade_check,store_setup_local_check;
    EditText setup_seller_store_name_et;
    TextInputEditText setup_store_location_et,store_setup_state_et,store_setup_city_et;
    Button bt_next_store_details;
    StoreFarmDetails storeFarmDetails;
    CircleImageView store_setup__store_image;
    private int AUTOCOMPLETE_REQUEST_CODE = 100;
    private static final int GALLERY_REQUEST_CODE = 201;
    private static final int CAMERA_REQUEST_CODE = 202;
    public PlacesClient placesClient;
    public LatLngToGeoLocation latLngToGeoLocation = new LatLngToGeoLocation();
    private String country, postCode;
    private AppController appController = AppController.get();
    private Double lat;
    private Double lang;
    private ImageView micImage;
    private CameraProvider cameraProvider = new CameraProvider(this, CAMERA_REQUEST_CODE, GALLERY_REQUEST_CODE);
    ImageView store_setup_mic_img;
    private Place placeAddress;
    private boolean SearchFlag = false;
    private String farmType = "2";
    private ArrayList<String> farmTypeList = new ArrayList<>();
    String home_made="";
    String local="";
    private String[] PERMISSIONS;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_details_step);
        linear_step=findViewById(R.id.linear_step);
        tv_setup_seller_back_img=findViewById(R.id.tv_setup_seller_back_img);
        tv_setup_seller_log_out_img=findViewById(R.id.tv_setup_seller_log_out_img);
        tv_setup_seller_toolbar_name=findViewById(R.id.tv_setup_seller_toolbar_name);
        store_setup_homemade_check=findViewById(R.id.store_setup_homemade_check);
        store_setup_local_check=findViewById(R.id.store_setup_local_check);
        setup_seller_store_name_et=findViewById(R.id.setup_seller_store_name_et);
        setup_store_location_et=findViewById(R.id.setup_store_location_et);
        store_setup_state_et=findViewById(R.id.store_setup_state_et);
        store_setup_city_et=findViewById(R.id.store_setup_city_et);
        bt_next_store_details=findViewById(R.id.bt_next_store_details);
        store_setup_mic_img=findViewById(R.id.store_setup_mic_img);
        store_detail_choose_image_ll=findViewById(R.id.store_detail_choose_image_ll);
        store_setup__store_image=findViewById(R.id.store_setup__store_image);
        bt_next_store_details.setText("Update");
        tv_setup_seller_toolbar_name.setText("Update Store");
        linear_step.setVisibility(View.GONE);
        tv_setup_seller_log_out_img.setVisibility(View.GONE);
        tv_setup_seller_back_img.setOnClickListener(v->{
            finish();
        });
        Places.initialize(getApplicationContext(), AppController.get().getGoogleApiKey());
        placesClient = Places.createClient(this);
        if (!Places.isInitialized()) {//
            Places.initialize(getApplicationContext(), AppController.get().getGoogleApiKey());
        }
        storeFarmDetails= (StoreFarmDetails) getIntent().getSerializableExtra("store");
        setStoreDetails();

    }

    @Override
    public Boolean showToolbar() {
        return false;
    }

    private void setStoreDetails(){
        setup_seller_store_name_et.setText(storeFarmDetails.farm_name);
        setup_store_location_et.setText(storeFarmDetails.farm_address);
        store_setup_city_et.setText(storeFarmDetails.store_city_name);
        store_setup_state_et.setText(storeFarmDetails.store_state_name);
        if(storeFarmDetails.store_type_farm_home.equalsIgnoreCase("No")){
            home_made="0";
            store_setup_homemade_check.setChecked(false);

        }
        else if(storeFarmDetails.store_type_farm_home.equalsIgnoreCase("Yes")){
            home_made="1";
            store_setup_homemade_check.setChecked(true);
            farmTypeList.add("1");
        }
        if(storeFarmDetails.store_type_farm_local.equalsIgnoreCase("No")){
            local="0";
            store_setup_local_check.setChecked(false);
        }
        else if(storeFarmDetails.store_type_farm_local.equalsIgnoreCase("Yes")){
            local="1";
            store_setup_local_check.setChecked(true);
            farmTypeList.add("0");
        }
//        if(storeFarmDetails.form_type_name!=null) {
//            if (storeFarmDetails.form_type_name.equalsIgnoreCase("Local Farm")) {
//                store_setup_local_check.setChecked(true);
//                store_setup_homemade_check.setChecked(false);
//            } else {
//                store_setup_local_check.setChecked(false);
//                store_setup_homemade_check.setChecked(true);
//            }
//        }
        setup_store_location_et.setOnClickListener(v->{
            searchPlace();
        });
        store_setup_homemade_check.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                farmTypeList.add("1");
                home_made="1";
                farmType="0";
            }
            else{
                farmTypeList.remove("1");
                home_made="0";

            }

        });
        store_setup_local_check.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                farmTypeList.add("0");
               local="1";
                farmType="1";
            }
            else{
                farmTypeList.remove("0");
                local="0";
            }

        });
        bt_next_store_details.setOnClickListener(v->{
            if (setup_seller_store_name_et.getText().toString().isEmpty()) {
                Toast.makeText(this, "Store Name can not be empty", Toast.LENGTH_SHORT).show();
                return;
            }
          else  if (setup_store_location_et.getText().toString().isEmpty()) {
                Toast.makeText(this, "Location can not be empty", Toast.LENGTH_SHORT).show();
                return;
            }

           else if (store_setup_state_et.getText().toString().isEmpty()) {
                Toast.makeText(this, "State can not be empty", Toast.LENGTH_SHORT).show();
                return;
            }
           else if (store_setup_city_et.getText().toString().isEmpty()) {
                Toast.makeText(this, "City can not be empty", Toast.LENGTH_SHORT).show();
                return;
            }
          else  if (farmTypeList.size()==0) {
                Toast.makeText(this, "Please Select Farm Type", Toast.LENGTH_SHORT).show();
                return;
            }
          else {
                editStore();
            }
        });
        store_setup_mic_img.setOnClickListener(v->{
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            try {
                startActivityForResult(intent, 1001);
            } catch (ActivityNotFoundException a) {

            }
        });
        PERMISSIONS = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
store_detail_choose_image_ll.setOnClickListener(v->{
    if (!Util.hasPermissions(ChangeStoreActivity.this, PERMISSIONS)) {
        ActivityCompat.requestPermissions(ChangeStoreActivity.this, PERMISSIONS, 1);
    } else {
        showProfilePicturePickerDialog();
    }
});

    }
    private void showProfilePicturePickerDialog() {
        AlertHelper.showAlert(this, "Choose Store Photo", "Choose your store Picture", true, "Camera", true, "Gallery", true, new OnAlertClickListener() {
            @Override
            public void onNegativeBtnClicked() {
                cameraProvider.openGallery();
            }

            @Override
            public void onPositiveBtnClicked() {
                cameraProvider.openCamera();
            }
        });
    }
    private void searchPlace(){
        if (!Places.isInitialized()) {//
            Places.initialize(getApplicationContext(), getApplication().getString(R.string.google_place_api));
        }
        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS, Place.Field.ADDRESS_COMPONENTS);
        Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields).build(this);
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
    }
    private void editStore(){
        if(postCode==null){
            postCode=storeFarmDetails.farm_postcode;

        }
        StringBuilder stringBuilder= new StringBuilder();
if(farmTypeList.size()>0){
    for(int i=0;i<farmTypeList.size();i++){
        if(stringBuilder.length()>0){
            stringBuilder.append(",");
        }
        stringBuilder.append(farmTypeList.get(i));
    }
}
        showLoader();
//        { "store_type_farm": null, "store_type_local": null, "store_type": null, "store_name": null, "store_address": null, "store_city": null, "store_state": null, "store_country": null, "store_post_code": null, "farm_id": null, "store_cover_image": null, "store_logo": null, "LoginId": null, "auth_key": null }
        String url= ApiConstants.EDIT_STORE+"?auth_key="+appController.getAuthenticationKey()+"&store_name="+setup_seller_store_name_et.getText().toString()+"&store_address="+setup_store_location_et.getText().toString()+"&store_city="+store_setup_city_et.getText().toString()+"&store_state="+store_setup_state_et.getText().toString()+"&store_post_code="+postCode+"&store_country="+country+"&farm_id="+storeFarmDetails.farm_id+"&store_cover_image="+"&store_logo="+"&LoginId="+appController.getLoginId()+"&store_type_farm="+home_made+"&store_type_local="+local+"&store_type="+stringBuilder.toString();
        Log.i("url",url);
        StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
dismissLoader();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String status_message=jsonObject.getString("status_message");
                    AlertHelper.showAlert(ChangeStoreActivity.this, "Store Update",
                           status_message, true, "Ok",
                            false, "", true, new OnAlertClickListener() {
                                @Override
                                public void onNegativeBtnClicked() {
                                    finish();
                                }

                                @Override
                                public void onPositiveBtnClicked() {

                                    finish();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        cameraProvider.processOnActivityResult(requestCode, resultCode, data, (file, s) -> {
            store_setup__store_image.setImageURI(Uri.fromFile(file));
            return Unit.INSTANCE;
        });

        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case 100: {
                    if (resultCode == RESULT_OK) {
                        Place place = Autocomplete.getPlaceFromIntent(data);

                        placeAddress = place;
                        SearchFlag = true;
                        double lat = placeAddress.getLatLng().latitude;
                        double lng = placeAddress.getLatLng().longitude;
                        try {
                            setup_store_location_et.setText(latLngToGeoLocation.getAddressLine(this, lat, lng));
                            store_setup_city_et.setText(latLngToGeoLocation.getLocality(this, lat, lng));
                            store_setup_state_et.setText(latLngToGeoLocation.getAdminArea(this, lat, lng));
                            country = latLngToGeoLocation.getCountryName(this, lat, lng);
                            postCode = latLngToGeoLocation.getPostalCode(this, lat, lng);
                            this.lat = lat;
                            lang = lng;
//                    extra.setCountry(country);
//                    extra.setPostalCode(postCode);


                            if (store_setup_city_et.getText().toString().isEmpty()) {
                                store_setup_city_et.setEnabled(false);
                            } else {
                                store_setup_city_et.setEnabled(true);
                            }

                            if (store_setup_state_et.getText().toString().isEmpty()) {
                                store_setup_state_et.setEnabled(false);
                            } else {
                                store_setup_state_et.setEnabled(true);
                            }


                        } catch (Exception e) {
                            Log.i("TAG", "An error occurred: GeoCoder" + e.getMessage());
                        }
                        Log.d("TAG", place.getAddress());

                    } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                        Status status = Autocomplete.getStatusFromIntent(data);
                        Toast.makeText(this, "Some went wrong. Search again", Toast.LENGTH_SHORT).show();
                        Log.i("TAG", status.getStatusMessage());
                    }
                    break;
                }

                case 1001: {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    setup_seller_store_name_et.setText(result.get(0));
                    break;
                }

//                    case CAMERA_REQUEST_CODE : {
//                        storeImage.setImageBitmap(imageUtil.getFileBitmapOfCameraResponse(data, StoreDetailsStepActivity.this).getBitmap());
////                        storeImage.setImageBitmap(ImageUtil.getResizedFileBitmapModel(ImageUtil.phototakenFromGallery(data, StoreDetailsStepActivity.this)).getBitmap());
//                        extra.setStoreLogo(imageUtil.getFileBitmapOfCameraResponse(data, StoreDetailsStepActivity.this).getFile());
//                        break;
//                    }
//                    case GALLERY_REQUEST_CODE : {
//                        storeImage.setImageBitmap(imageUtil.photoTakenFromGallery(data, StoreDetailsStepActivity.this).getBitmap());
//                        extra.setStoreLogo(imageUtil.photoTakenFromGallery(data, StoreDetailsStepActivity.this).getFile());
//
//                        break;
//                    }
            }
        }


    }
}
