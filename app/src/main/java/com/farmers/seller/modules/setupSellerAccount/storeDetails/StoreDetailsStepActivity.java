package com.farmers.seller.modules.setupSellerAccount.storeDetails;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.farmers.buyers.R;
import com.farmers.buyers.common.ImageUtil;
import com.farmers.buyers.common.utils.AlertHelper;
import com.farmers.buyers.common.utils.OnAlertClickListener;
import com.farmers.buyers.storage.LatLngToGeoLocation;
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
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class StoreDetailsStepActivity extends AppCompatActivity implements View.OnClickListener {

    private int AUTOCOMPLETE_REQUEST_CODE = 100;

    private ImageView img_back;
    private TextView tv_toolbar_name;
    private Button bt_next_store_details;
    private TextInputEditText locationEt, cityEt, stateEt, storeNameEt;
    private CircleImageView storeImage;
    private LinearLayout chooseStoreImageLl;
    private Place placeAddress;
    private boolean SearchFlag = false;
    private AutocompleteSupportFragment autocompleteFragment;
    public PlacesClient placesClient;
    public LatLngToGeoLocation latLngToGeoLocation = new LatLngToGeoLocation();
    private ImageUtil imageUtil = new ImageUtil();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_details_step);

        img_back = findViewById(R.id.tv_setup_seller_back_img);
        tv_toolbar_name = findViewById(R.id.tv_setup_seller_toolbar_name);
        tv_toolbar_name.setText("Setup Seller Account");
        bt_next_store_details = findViewById(R.id.bt_next_store_details);
        locationEt = findViewById(R.id.setup_store_location_et);
        cityEt = findViewById(R.id.store_setup_state_et);
        stateEt = findViewById(R.id.store_setup_city_et);
        storeImage = findViewById(R.id.store_setup__store_image);
        storeNameEt = findViewById(R.id.setup_seller_store_name_et);
        chooseStoreImageLl = findViewById(R.id.store_detail_choose_image_ll);

        img_back.setOnClickListener(this);
        bt_next_store_details.setOnClickListener(this);

        Places.initialize(getApplicationContext(), getApplication().getString(R.string.google_place_api));
        placesClient = Places.createClient(this);

        if (!Places.isInitialized()) {//
            Places.initialize(getApplicationContext(), getApplication().getString(R.string.google_place_api));
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

        init();
    }

    private void showProfilePicturePickerDialog() {
        AlertHelper.showAlert(this, "Choose Store Photo", "Choose your store Picture", true, "Camera", true, "Gallery", true, new OnAlertClickListener() {
            @Override
            public void onNegativeBtnClicked() {
                ImageUtil.choosePhotoFromGallery(StoreDetailsStepActivity.this, 102);
//                cameraProvider.openGallery();
            }

            @Override
            public void onPositiveBtnClicked() {
                ImageUtil.takePhotoFromCamera(StoreDetailsStepActivity.this, 201);
            }
        });
    }

    private void init() {

        chooseStoreImageLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProfilePicturePickerDialog();
            }
        });
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
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
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
        }

        if (requestCode == 102) {
            storeImage.setImageBitmap(ImageUtil.phototakenFromGallery(data, StoreDetailsStepActivity.this).getBitmap());
        }
        if (requestCode == 201) {
            storeImage.setImageBitmap(ImageUtil.photoTakenFromCamera(data, StoreDetailsStepActivity.this).getBitmap());
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

                Intent intent = new Intent(this, ServiceDetailsStepActivity.class);
                StoreSetupExtra extra = new StoreSetupExtra();
                extra.setName(storeNameEt.getText().toString());
                extra.setLocation(locationEt.getText().toString());
                extra.setCity(cityEt.getText().toString());
                extra.setState(stateEt.getText().toString());
                intent.putExtra("storeExtra", extra);
                startActivity(intent);
                break;
        }
    }
}