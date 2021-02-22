package com.farmers.buyers.modules.address;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.farmers.buyers.R;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.address.model.AddressApiModel;
import com.farmers.buyers.modules.address.model.AddAddressRequestParams;
import com.farmers.buyers.modules.address.model.MyAddressViewModel;
import com.farmers.buyers.storage.LatLngToGeoLocation;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.Arrays;
import java.util.List;

public class AddNewAddressActivity extends BaseActivity implements View.OnClickListener {

    private ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(MyAddressViewModel.class)) {
                return (T) new MyAddressViewModel();
            }
            return null;
        }
    };

    public int AUTOCOMPLETE_REQUEST_CODE = 100;
    public MyAddressViewModel viewModel = factory.create(MyAddressViewModel.class);
    private MutableLiveData<DataFetchState<AddressApiModel>> stateMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<AddressApiModel>> editStateMachine = new MutableLiveData<>();

    public LatLngToGeoLocation latLngToGeoLocation = new LatLngToGeoLocation();
    private Button bt_submit;
    private EditText ed_name_of_address, ed_complete_address, ed_city, ed_state, ed_postal_code, ed_mobile_number;
    public Place placeAddress;
    private boolean SearchFlag = false;
    private AutocompleteSupportFragment autocompleteFragment;
    public PlacesClient placesClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_address);

        setupToolbar(new BaseActivity.ToolbarConfig("Add New Address", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        }, false, new ToolbarMenuConfig(R.drawable.ic_notification, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        })));

        init();
    }

    private void init() {
        bt_submit = findViewById(R.id.bt_submit);
        ed_name_of_address = findViewById(R.id.ed_name_of_address);
        ed_complete_address = findViewById(R.id.ed_complete_address);
        ed_city = findViewById(R.id.ed_city);
        ed_state = findViewById(R.id.ed_state);
        ed_postal_code = findViewById(R.id.ed_postal_code);
        ed_mobile_number = findViewById(R.id.ed_mobile_number);

        if (getIntent().getStringExtra("KEY_FROM").equalsIgnoreCase("EDIT_ADDRESS")) {
            ed_name_of_address.setText(getIntent().getStringExtra("ADDRESS_TITLE"));
            ed_complete_address.setText(getIntent().getStringExtra("ADDRESS"));
            ed_city.setText("");
            ed_state.setText("");
            ed_postal_code.setText("");
            ed_mobile_number.setText("");
        } else {
        }

        stateMachine.observe(this, dataFetchState -> {
            switch (dataFetchState.status) {
                case ERROR: {
                    dismissLoader();
                    Toast.makeText(AddNewAddressActivity.this, dataFetchState.status_message, Toast.LENGTH_SHORT).show();
                    break;
                }
                case LOADING: {
                    showLoader();
                    break;
                }
                case SUCCESS: {
                    dismissLoader();
                    Toast.makeText(AddNewAddressActivity.this, dataFetchState.status_message, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddNewAddressActivity.this, MyAddressActivity.class));
                    finish();
                    break;
                }
            }
        });

        editStateMachine.observe(this, dataFetchState -> {
            switch (dataFetchState.status) {
                case ERROR: {
                    dismissLoader();
                    Toast.makeText(AddNewAddressActivity.this, dataFetchState.status_message, Toast.LENGTH_SHORT).show();
                    break;
                }
                case LOADING: {
                    showLoader();
                    break;
                }
                case SUCCESS: {
                    dismissLoader();
                    Toast.makeText(AddNewAddressActivity.this, dataFetchState.status_message, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddNewAddressActivity.this, MyAddressActivity.class));
                    finish();
                    break;
                }
            }
        });

        Places.initialize(getApplicationContext(), getApplication().getString(R.string.google_place_api));
        placesClient = Places.createClient(AddNewAddressActivity.this);

        if (!Places.isInitialized()) {//
            Places.initialize(getApplicationContext(), getApplication().getString(R.string.google_place_api));
        }

        ed_complete_address.setOnClickListener(view -> searchPlace());

        bt_submit.setOnClickListener(this);
    }

    @Override
    public Boolean showToolbar() {
        return true;
    }

    @Override
    public void onClick(View view) {
        if (getIntent().getStringExtra("KEY_FROM").equalsIgnoreCase("EDIT_ADDRESS")) {
            AddAddressRequestParams addAddressRequestParams = new AddAddressRequestParams(AppController.get().getLoginId(),
                    getIntent().getStringExtra("ADDRESS_ID"), ed_name_of_address.getText().toString().trim(),
                    ed_complete_address.getText().toString().trim(), ed_city.getText().toString().trim(),
                    ed_state.getText().toString().trim(), ed_postal_code.getText().toString().trim(),
                    ed_mobile_number.getText().toString().trim(), AppController.get().getAuthenticationKey());

            viewModel.editAddress(stateMachine, addAddressRequestParams);
        } else {
            AddAddressRequestParams addAddressRequestParams = new AddAddressRequestParams(AppController.get().getLoginId(), ed_name_of_address.getText().toString().trim(),
                    ed_complete_address.getText().toString().trim(), ed_city.getText().toString().trim(),
                    ed_state.getText().toString().trim(), ed_postal_code.getText().toString().trim(),
                    ed_mobile_number.getText().toString().trim(), AppController.get().getAuthenticationKey());

            viewModel.addAddress(stateMachine, addAddressRequestParams);
        }
    }

    private void searchPlace() {
        if (!Places.isInitialized()) {//
            Places.initialize(getApplicationContext(), getApplication().getString(R.string.google_place_api));
        }
        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS, Place.Field.ADDRESS_COMPONENTS);
        Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields).build(AddNewAddressActivity.this);
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
                    ed_complete_address.setText(latLngToGeoLocation.getAddressLine(AddNewAddressActivity.this, lat, lng));
                    ed_city.setText(latLngToGeoLocation.getLocality(AddNewAddressActivity.this, lat, lng));
                    ed_state.setText(latLngToGeoLocation.getAdminArea(AddNewAddressActivity.this, lat, lng));
                    ed_postal_code.setText(latLngToGeoLocation.getPostalCode(AddNewAddressActivity.this, lat, lng));
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
}