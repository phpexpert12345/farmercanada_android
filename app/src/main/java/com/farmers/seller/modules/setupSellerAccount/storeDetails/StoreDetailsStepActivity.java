package com.farmers.seller.modules.setupSellerAccount.storeDetails;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.farmers.buyers.R;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.common.utils.AlertHelper;
import com.farmers.buyers.common.utils.CameraProvider;
import com.farmers.buyers.common.utils.OnAlertClickListener;
import com.farmers.buyers.common.utils.Util;
import com.farmers.buyers.modules.login.LoginActivity;
import com.farmers.buyers.storage.LatLngToGeoLocation;
import com.farmers.buyers.storage.SharedPreferenceManager;
import com.farmers.seller.modules.setupSellerAccount.serviceDetails.ServiceDetailsStepActivity;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class StoreDetailsStepActivity extends AppCompatActivity implements View.OnClickListener {

    private int AUTOCOMPLETE_REQUEST_CODE = 100;
    private static final int GALLERY_REQUEST_CODE = 201;
    private static final int CAMERA_REQUEST_CODE = 202;

    private ImageView img_back, logOutImg;
    private TextView tv_toolbar_name;
    private Button bt_next_store_details;
    private TextInputEditText locationEt, cityEt, stateEt;
    EditText storeNameEt;
    private CircleImageView storeImage;
    private LinearLayout chooseStoreImageLl;
    private Place placeAddress;
    private boolean SearchFlag = false;
    private AutocompleteSupportFragment autocompleteFragment;
    public PlacesClient placesClient;
    public LatLngToGeoLocation latLngToGeoLocation = new LatLngToGeoLocation();
    private String country, postCode;
    StoreSetupExtra extra = new StoreSetupExtra();
    private String[] PERMISSIONS;
    private CheckBox homeMadeCheck, localFarmCheck;
    private String farmType = "2"; // 1 for local 2 for homeMade
    private ArrayList<String> farmTypeList = new ArrayList<>();
    private Double lat;
    private Double lang;
    private ImageView micImage;
    private CameraProvider cameraProvider = new CameraProvider(this, CAMERA_REQUEST_CODE, GALLERY_REQUEST_CODE);


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_details_step);

        img_back = findViewById(R.id.tv_setup_seller_back_img);
        tv_toolbar_name = findViewById(R.id.tv_setup_seller_toolbar_name);
        bt_next_store_details = findViewById(R.id.bt_next_store_details);
        locationEt = findViewById(R.id.setup_store_location_et);
        cityEt = findViewById(R.id.store_setup_state_et);
        stateEt = findViewById(R.id.store_setup_city_et);
        storeImage = findViewById(R.id.store_setup__store_image);
        storeNameEt = findViewById(R.id.setup_seller_store_name_et);
        chooseStoreImageLl = findViewById(R.id.store_detail_choose_image_ll);
        homeMadeCheck = findViewById(R.id.store_setup_homemade_check);
        localFarmCheck = findViewById(R.id.store_setup_local_check);
        micImage = findViewById(R.id.store_setup_mic_img);
        logOutImg = findViewById(R.id.tv_setup_seller_log_out_img);

        img_back.setOnClickListener(this);
        bt_next_store_details.setOnClickListener(this);

        Places.initialize(getApplicationContext(), AppController.get().getGoogleApiKey());
        placesClient = Places.createClient(this);

        if (!Places.isInitialized()) {//
            Places.initialize(getApplicationContext(), AppController.get().getGoogleApiKey());
        }


        locationEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchPlace();
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        micImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

                try {
                    startActivityForResult(intent, 1001);
                } catch (ActivityNotFoundException a) {

                }
            }
        });

        init();
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

    private void init() {
        PERMISSIONS = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};


        chooseStoreImageLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Util.hasPermissions(StoreDetailsStepActivity.this, PERMISSIONS)) {
                    ActivityCompat.requestPermissions(StoreDetailsStepActivity.this, PERMISSIONS, 1);
                } else {
                    showProfilePicturePickerDialog();
                }
            }
        });

        homeMadeCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    farmTypeList.add("1");
                    farmType = "1";
                    extra.setStore_type_farm("1");
                } else {
                    farmTypeList.remove("1");
                    farmType = "0";
                    extra.setStore_type_farm("0");
                }

                Log.e("item", farmTypeList.toString());
            }
        });

        localFarmCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    farmTypeList.add("0");
                    farmType = "1";
                    extra.setStore_type_local("1");
                } else {
                    farmTypeList.remove("0");
                    farmType = "0";
                    extra.setStore_type_local("0");
                }
                Log.e("item", farmTypeList.toString());

            }
        });

        logOutImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutAlert();
            }
        });

        extra.setStore_type_farm("0");
        extra.setStore_type_local("0");
    }


    private void searchPlace() {
        if (!Places.isInitialized()) {//
            Places.initialize(getApplicationContext(), getApplication().getString(R.string.google_place_api));
        }
        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS, Place.Field.ADDRESS_COMPONENTS);
        Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields).build(this);
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        cameraProvider.processOnActivityResult(requestCode, resultCode, data, (file, s) -> {
            storeImage.setImageURI(Uri.fromFile(file));
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
                            locationEt.setText(latLngToGeoLocation.getAddressLine(this, lat, lng));
                            cityEt.setText(latLngToGeoLocation.getLocality(this, lat, lng));
                            stateEt.setText(latLngToGeoLocation.getAdminArea(this, lat, lng));
                            country = latLngToGeoLocation.getCountryName(this, lat, lng);
                            postCode = latLngToGeoLocation.getPostalCode(this, lat, lng);
                            this.lat = lat;
                            lang = lng;
//                    extra.setCountry(country);
//                    extra.setPostalCode(postCode);


                            if (cityEt.getText().toString().isEmpty()) {
                                cityEt.setEnabled(false);
                            } else {
                                cityEt.setEnabled(true);
                            }

                            if (stateEt.getText().toString().isEmpty()) {
                                stateEt.setEnabled(false);
                            } else {
                                stateEt.setEnabled(true);
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
                    storeNameEt.setText(result.get(0));
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


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;

            case R.id.bt_next_store_details:
                if (storeNameEt.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Store Name can not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (locationEt.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Location can not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (stateEt.getText().toString().isEmpty()) {
                    Toast.makeText(this, "State can not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (cityEt.getText().toString().isEmpty()) {
                    Toast.makeText(this, "City can not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (farmTypeList.isEmpty()) {
                    Toast.makeText(this, "Please Select Farm Type", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(this, ServiceDetailsStepActivity.class);
                extra.setName(storeNameEt.getText().toString());
                extra.setLocation(locationEt.getText().toString());
                extra.setCity(cityEt.getText().toString());
                extra.setState(stateEt.getText().toString());
                extra.setCountry(country);
                extra.setPostalCode(postCode);
                extra.setCompanyType(Extensions.convert(farmTypeList));
                extra.setMapLat(lat);
                extra.setMapLong(lang);
                intent.putExtra("storeExtra", extra);
                startActivity(intent);
                break;
        }
    }


    private void showLogoutAlert() {
        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Farmer Alert");
        builder.setMessage("Are you sure! You want to logout ?");
        builder.setPositiveButton("Ok", (dialogInterface, i) -> {
            SharedPreferenceManager.getInstance().clearUserInfo();
            startActivity(new Intent(this, LoginActivity.class));
            this.finish();
        });
        builder.setNegativeButton("Cancel", (dialogInterface, i) -> {
            dialogInterface.dismiss();
        });
        builder.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}