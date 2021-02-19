package com.farmers.buyers.modules.forgotPassword;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.home.HomeActivity;
import com.farmers.buyers.modules.login.LoginActivity;
import com.farmers.buyers.modules.login.LoginViewModel;
import com.farmers.buyers.modules.login.model.LoginApiModel;
import com.farmers.buyers.modules.seller.product.ProductListActivity;

public class ForgotPassword extends BaseActivity {

    private ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(ForgotPasswordViewModel.class)) {
                return (T) new ForgotPasswordViewModel();
            }
            return null;
        }
    };

    public ForgotPasswordViewModel viewModel = factory.create(ForgotPasswordViewModel.class);
    private MutableLiveData<DataFetchState<LoginApiModel>> stateMachine = new MutableLiveData<>();

    public EditText otp_number_et, ed_forgot_password, ed_confirm_forgot_password;
    public Button forgot_password_save_btn;
    public int role = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        setupToolbar(new ToolbarConfig("Reset Password", true, new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ForgotPassword.super.onBackPressed();
            }
        }, false, null));

        init();
        listener();
    }

    private void init() {
        otp_number_et = findViewById(R.id.otp_number_et);
        ed_forgot_password = findViewById(R.id.ed_forgot_password);
        ed_confirm_forgot_password = findViewById(R.id.ed_confirm_forgot_password);
        forgot_password_save_btn = findViewById(R.id.forgot_password_save_btn);

        stateMachine.observe(this, new Observer<DataFetchState<LoginApiModel>>() {
            @Override
            public void onChanged(DataFetchState dataFetchState) {
                switch (dataFetchState.status) {
                    case ERROR: {
                        dismissLoader();
                        Toast.makeText(ForgotPassword.this, dataFetchState.status_message, Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case LOADING: {
                        showLoader();
                        break;
                    }
                    case SUCCESS: {
                        dismissLoader();
                        Toast.makeText(ForgotPassword.this, dataFetchState.status_message, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ForgotPassword.this, LoginActivity.class));
                        finish();
                        break;
                    }
                }
            }
        });
    }

    private void listener() {
        forgot_password_save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobile_number = otp_number_et.getText().toString().trim();
                String password = ed_forgot_password.getText().toString();
                String confirm_password = ed_confirm_forgot_password.getText().toString();

                viewModel.doForgotPassword(stateMachine, mobile_number,
                        getIntent().getStringExtra("USER_ID"),
                        password, confirm_password);
            }
        });
    }

    @Override
    public Boolean showToolbar() {
        return true;
    }
}