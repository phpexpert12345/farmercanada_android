package com.farmers.buyers.modules.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import com.farmers.buyers.modules.home.HomeActivity;
import com.farmers.buyers.modules.login.model.LoginApiModel;
import com.farmers.buyers.modules.login.model.LoginRequestParams;
import com.farmers.buyers.modules.seller.product.ProductListActivity;
import com.farmers.buyers.modules.signUp.OtpActivity;
import com.farmers.buyers.modules.signUp.SignUpActivity;
import com.google.android.material.textfield.TextInputEditText;


public class LoginActivity extends BaseActivity {

    private ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(LoginViewModel.class)) {
                return (T) new LoginViewModel();
            }
            return null;
        }
    };

    private LoginViewModel viewModel = factory.create(LoginViewModel.class);
    private TextView registerTv, forgotPassword;
    private TextInputEditText mobileEt, passwordEt;
    private Button loginBtn;
    private RadioGroup radioGroup;
    private int role;  //todo 0 for buyer 1 for seller
    private MutableLiveData<DataFetchState<LoginApiModel>> stateMachine = new MutableLiveData<>();
    Integer user_type=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        listener();
    }

    @Override
    public Boolean showToolbar() {
        return false;
    }

    private void listener() {

        registerTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, OtpActivity.class);
                intent.putExtra("fromForgetPassword", true);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginRequestParams loginRequestParams=new LoginRequestParams(mobileEt.getText().toString(),passwordEt.getText().toString(),user_type);
                viewModel.doLogin(stateMachine,loginRequestParams);

            }
        });

    }

    private void init() {
        registerTv = findViewById(R.id.login_register_tv);
        forgotPassword = findViewById(R.id.login_forgot_password_tv);
        loginBtn = findViewById(R.id.login_btn);
        mobileEt = findViewById(R.id.login_email_et);
        passwordEt = findViewById(R.id.login_password_et);
        radioGroup = findViewById(R.id.login_radio_group);

        stateMachine.observe(this, new Observer<DataFetchState<LoginApiModel>>() {
            @Override
            public void onChanged(DataFetchState dataFetchState) {
                switch (dataFetchState.status) {
                    case ERROR: {
                        dismissLoader();
                        Toast.makeText(LoginActivity.this, dataFetchState.message, Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case LOADING: {
                        showLoader();
                        break;

                    }
                    case SUCCESS: {
                        dismissLoader();
                        if (role == 1) {
                            startActivity(new Intent(LoginActivity.this, ProductListActivity.class));
                        }
                        else {
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            finish();
                        }
                        break;


                    }
                }
            }
        });
    }

}