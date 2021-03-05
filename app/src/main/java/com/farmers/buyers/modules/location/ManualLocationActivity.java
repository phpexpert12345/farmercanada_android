package com.farmers.buyers.modules.location;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.farmers.buyers.R;
import com.farmers.buyers.common.utils.DroidPrefs;
import com.farmers.buyers.modules.login.LoginActivity;
import com.farmers.buyers.storage.GPSTracker;
import com.farmers.buyers.storage.SharedPreferenceManager;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.Arrays;
import java.util.List;

import static com.farmers.buyers.app.App.getAppContext;

public class ManualLocationActivity extends AppCompatActivity implements OnMapReadyCallback {

    public int AUTOCOMPLETE_REQUEST_CODE = 100;
    private Button confirmLocation;
    private SupportMapFragment mapFragment;
    private GoogleMap mMap;
    private GPSTracker gpsTracker;
    private LatLng latLng = new LatLng(23.257979, 77.413513);
    public Place placeAddress;
    private boolean SearchFlag = false;
    private AutocompleteSupportFragment autocompleteFragment;
    public PlacesClient placesClient;
    public TextView tv_address;
    public EditText ed_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_location);

        gpsTracker = new GPSTracker(ManualLocationActivity.this);
        latLng = new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude());
        init();
        listener();
    }

    private void init() {

        confirmLocation = findViewById(R.id.manual_location_confirm_btn);
        tv_address = findViewById(R.id.tv_address);
        ed_search = findViewById(R.id.ed_search);
        tv_address.setText(gpsTracker.getAddressLine(this));
        Places.initialize(getApplicationContext(), getApplication().getString(R.string.google_place_api));
        placesClient = Places.createClient(ManualLocationActivity.this);

       /* if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), getApplication().getString(R.string.google_place_api));
        }*/

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);

       /* autocompleteFragment = (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS));

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {

                placeAddress = place;
                Log.d("TAG", String.valueOf(place));
                SearchFlag = true;
                latLng = place.getLatLng();
                mapFragment.getMapAsync(ManualLocationActivity.this);
                tv_address.setText(String.valueOf(placeAddress.getName() + ", " + String.valueOf(placeAddress.getAddress())));
            }

            @Override
            public void onError(Status status) {
                Log.i("TAG", "An error occurred: " + status);
            }
        });*/

        ed_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchPlace();
            }
        });
    }

    private void listener() {
        confirmLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(placeAddress!=null) {
                    DroidPrefs.apply(getApplicationContext(),"Current_Location",placeAddress.getAddress());
//                        SharedPreferenceManager.getInstance().setSharedPreference("Current_Location", placeAddress.getAddress());
                }
                else{
                    DroidPrefs.apply(getApplicationContext(),"Current_Location",gpsTracker.getAddressLine(ManualLocationActivity.this));
//                        SharedPreferenceManager.getInstance().setSharedPreference("Current_Location", gpsTracker.getAddressLine(ManualLocationActivity.this));
                }
                if(!SharedPreferenceManager.getInstance().getIsLoggedIn()) {
                    startActivity(new Intent(ManualLocationActivity.this, LoginActivity.class));
                }
                else{


                    setResult(Activity.RESULT_OK);

                }
                finish();
            }
        });
    }

    private void searchPlace() {
        if (!Places.isInitialized()) {//
            Places.initialize(getApplicationContext(), getApplication().getString(R.string.google_place_api));
        }
        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS, Place.Field.ADDRESS_COMPONENTS);
        Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields).build(ManualLocationActivity.this);
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
                try {
                    latLng = place.getLatLng();
                    mapFragment.getMapAsync(ManualLocationActivity.this);
                    ed_search.setText(String.valueOf(placeAddress.getName()));
                    tv_address.setText(String.valueOf(placeAddress.getName() + ", " + String.valueOf(placeAddress.getAddress())));
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
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.clear();
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        mMap.setOnMapLoadedCallback(() -> {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            if (SearchFlag) {
                mMap.addMarker(new MarkerOptions().position(latLng).title(placeAddress.getName()));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
            } else {
                mMap.addMarker(new MarkerOptions().position(latLng).title("India"));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
            }
        });
    }
}