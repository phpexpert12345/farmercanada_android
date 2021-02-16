package com.farmers.buyers.modules.address;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
import com.farmers.buyers.modules.signUp.model.SignUpApiModel;

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

    public MyAddressViewModel viewModel = factory.create(MyAddressViewModel.class);
    private MutableLiveData<DataFetchState<AddressApiModel>> stateMachine = new MutableLiveData<>();

    private Button bt_submit;
    private EditText ed_name_of_address, ed_complete_address, ed_city, ed_state, ed_postal_code, ed_mobile_number;

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

        stateMachine.observe(this, new Observer<DataFetchState<AddressApiModel>>() {
            @Override
            public void onChanged(DataFetchState<AddressApiModel> dataFetchState) {
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
            }
        });

        bt_submit.setOnClickListener(this);
    }

    @Override
    public Boolean showToolbar() {
        return true;
    }

    @Override
    public void onClick(View view) {

        AddAddressRequestParams addAddressRequestParams = new AddAddressRequestParams(AppController.get().getLoginId(), ed_name_of_address.getText().toString().trim(),
                ed_complete_address.getText().toString().trim(), ed_city.getText().toString().trim(),
                ed_state.getText().toString().trim(), ed_postal_code.getText().toString().trim(),
                ed_mobile_number.getText().toString().trim(), AppController.get().getAuthenticationKey());

        viewModel.addAddress(stateMachine, addAddressRequestParams);
    }
}