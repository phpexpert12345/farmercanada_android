package com.farmers.buyers.modules.signUp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.signUp.model.SignUpApiModel;
import com.farmers.seller.modules.setupSellerAccont.storeDetails.StoreDetailsStepActivity;
import com.google.android.material.textfield.TextInputEditText;

public class SignUpActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    private TextView termsConditionTv;
    private Button signUpBtn;
    private TextInputEditText nameEt, emailEt, numberEt, passwordEt;
    private RadioGroup user_type_radio_group;
    private RadioButton radio_seller, radio_buyer;

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
                Toast.makeText(this, "Buyer", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void loading() {
        showLoader();
    }

    private void success() {
        dismissLoader();
        Intent intent = new Intent(SignUpActivity.this, OtpActivity.class);
        intent.putExtra("fromForgetPassword", false);
        startActivity(intent);
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
        viewModel.doSignUp(stateMachine, nameEt.getText().toString(), numberEt.getText().toString(), emailEt.getText().toString(), passwordEt.getText().toString());
    }
}