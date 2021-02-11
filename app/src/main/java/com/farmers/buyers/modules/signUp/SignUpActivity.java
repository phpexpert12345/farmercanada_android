package com.farmers.buyers.modules.signUp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.home.HomeActivity;
import com.farmers.buyers.modules.login.LoginActivity;
import com.farmers.buyers.modules.signUp.model.SignUpApiModel;
import com.farmers.buyers.modules.signUp.model.SignUpRequestParams;
import com.farmers.seller.modules.setupSellerAccount.storeDetails.StoreDetailsStepActivity;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SignUpActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, LocationListener {
    private TextView termsConditionTv;
    private Button signUpBtn;
    private TextInputEditText nameEt, emailEt, numberEt, passwordEt;
    private RadioGroup user_type_radio_group;
    private RadioButton radio_seller, radio_buyer;
    private String account_country = "";
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    private Integer account_type = 0;
    protected Context context;
    private String account_city, account_state, account_address, account_long, account_lat, device_id, device_platform, account_phone_code;

    private ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(SignUpViewModel.class)) {
                return (T) new SignUpViewModel();
            }
            return null;
        }
    };

    private SignUpViewModel viewModel = factory.create(SignUpViewModel.class);
    private MutableLiveData<DataFetchState<SignUpApiModel>> stateMachine = new MutableLiveData<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        init();
        listener();
    }

    @Override
    public Boolean showToolbar() {
        return false;
    }

    private void init() {
        termsConditionTv = findViewById(R.id.sign_up_terms_condition_tv);
        signUpBtn = findViewById(R.id.sign_up_submit_btn);
        nameEt = findViewById(R.id.signUp_name_et);
        emailEt = findViewById(R.id.signUp_email_et);
        numberEt = findViewById(R.id.signUp_mobile_et);
        passwordEt = findViewById(R.id.signUp_password_et);
        user_type_radio_group = findViewById(R.id.user_type_radio_group);
        user_type_radio_group.setOnCheckedChangeListener(this);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 3, this);
        }

        stateMachine.observe(this, new Observer<DataFetchState<SignUpApiModel>>() {
            @Override
            public void onChanged(DataFetchState<SignUpApiModel> signUpApiModelDataFetchState) {
                switch (signUpApiModelDataFetchState.status) {
                    case LOADING: {
                        loading();
                        break;
                    }

                    case SUCCESS: {
                        success();
                        break;
                    }

                    case ERROR: {
                        error(signUpApiModelDataFetchState.message);
                        break;
                    }
                }
            }
        });
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.radio_seller:
                startActivity(new Intent(SignUpActivity.this, StoreDetailsStepActivity.class));
                break;

            case R.id.radio_buyer:
                account_type = 1;
                Toast.makeText(this, "Buyer", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void loading() {
        showLoader();
    }

    private void success() {
        dismissLoader();
        startActivity(new Intent(SignUpActivity.this, HomeActivity.class));
        finish();
//        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
//        intent.putExtra("fromForgetPassword",false);
//        startActivity(intent);
    }

    private void error(String error) {
        dismissLoader();
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    private void listener() {
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doSignUp();
            }
        });
    }

    private void doSignUp() {
        SignUpRequestParams signUpRequestParams = new SignUpRequestParams(nameEt.getText().toString(), numberEt.getText().toString(), emailEt.getText().toString(), passwordEt.getText().toString(), account_type, account_country, account_state, account_city, account_address, account_lat, account_long, "91", "", "Android");
        viewModel.doSignUp(stateMachine, signUpRequestParams);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        if (location != null) {
            account_lat = String.valueOf(location.getLatitude());
            account_long = String.valueOf(location.getLongitude());
            new AddressAsynck().execute(location);
        }
    }

    private class AddressAsynck extends AsyncTask<Location, Integer, String> {

        @Override
        protected String doInBackground(Location... locations) {
            Geocoder geocoder = new Geocoder(SignUpActivity.this, Locale.getDefault());
            List<Address> addresses = null;
            try {
                addresses = geocoder.getFromLocation(locations[0].getLatitude(), locations[0].getLatitude(), 1);
                account_address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                account_city = addresses.get(0).getLocality();
                account_state = addresses.get(0).getAdminArea();
                account_country = addresses.get(0).getCountryName();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}