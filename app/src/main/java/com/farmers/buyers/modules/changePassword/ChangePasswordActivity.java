package com.farmers.buyers.modules.changePassword;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.farmers.buyers.modules.forgotPassword.ForgotPassword;
import com.farmers.buyers.modules.forgotPassword.ForgotPasswordViewModel;
import com.farmers.buyers.modules.login.LoginActivity;
import com.farmers.buyers.modules.login.model.LoginApiModel;
import com.farmers.buyers.modules.referFriends.ReferFriendsActivity;

public class ChangePasswordActivity extends BaseActivity implements View.OnClickListener {


    private ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(ChangePasswordViewModel.class)) {
                return (T) new ChangePasswordViewModel();
            }
            return null;
        }
    };

    public ChangePasswordViewModel viewModel = factory.create(ChangePasswordViewModel.class);
    private MutableLiveData<DataFetchState<LoginApiModel>> stateMachine = new MutableLiveData<>();

    Button bt_save;
    ImageView image_back_button;
    EditText ed_old_password, ed_new_password, ed_confirm_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        init();
    }

    private void init() {
        bt_save = findViewById(R.id.bt_save);
        image_back_button = findViewById(R.id.change_password_back_image);

        image_back_button.setOnClickListener(this);
        bt_save.setOnClickListener(this);

        stateMachine.observe(this, new Observer<DataFetchState<LoginApiModel>>() {
            @Override
            public void onChanged(DataFetchState dataFetchState) {
                switch (dataFetchState.status) {
                    case ERROR: {
                        dismissLoader();
                        // Toast.makeText(ChangePasswordActivity.this, dataFetchState.message, Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case LOADING: {
                        showLoader();
                        break;
                    }
                    case SUCCESS: {
                        dismissLoader();
                        //  startActivity(new Intent(ChangePasswordActivity.this, LoginActivity.class));
                        //  finish();
                        break;
                    }
                }
            }
        });
    }

    @Override
    public Boolean showToolbar() {
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.change_password_back_image:
                finish();
                break;
            case R.id.bt_save:
                String mobile_number = ed_old_password.getText().toString().trim();
                String password = ed_new_password.getText().toString();
                String confirm_password = ed_confirm_password.getText().toString();
                viewModel.doChangePassword(stateMachine, mobile_number, password, confirm_password);
                break;
        }
    }
}