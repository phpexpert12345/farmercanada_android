package com.farmers.buyers.modules.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.farmers.buyers.R;
import com.farmers.buyers.app.App;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.home.HomeActivity;
import com.farmers.buyers.modules.login.model.LoginApiModel;
import com.farmers.buyers.modules.login.model.LoginRequestParams;
import com.farmers.buyers.modules.seller.product.ProductListActivity;
import com.farmers.buyers.modules.signUp.OtpActivity;
import com.farmers.buyers.modules.signUp.SignUpActivity;
import com.farmers.seller.modules.ourOrders.OurOrdersActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.farmers.seller.modules.setupSellerAccount.storeDetails.StoreDetailsStepActivity;
import com.farmers.seller.modules.setupSellerAccount.storeDetails.StoreDetailsStepActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.iid.FirebaseInstanceId;

public class LoginActivity extends BaseActivity {
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;
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
    RelativeLayout relative_google,relative_face;
    //todo 0 for buyer 1 for seller
    private int role = 2;  //todo 2 for buyer 1 for seller
    private MutableLiveData<DataFetchState<LoginApiModel>> stateMachine = new MutableLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        App.finish_activity=false;
        init();
        listener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(App.finish_activity){
            finish();
        }
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
                intent.putExtra("FROM", "Login");
                startActivity(intent);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.login_seller_radio: {
                        role = 1;
                        break;
                    }
                    case R.id.login_buyer_radio: {
                        role = 2;
                        break;
                    }
                }
            }
        });

        loginBtn.setOnClickListener(view -> {

            String email = mobileEt.getText().toString();
            String password = passwordEt.getText().toString();

            viewModel.doLogin(
                    stateMachine, email, password, role, AppController.get().getDeviceId());

        });
    }

    private void init() {
        registerTv = findViewById(R.id.login_register_tv);
        forgotPassword = findViewById(R.id.login_forgot_password_tv);
        loginBtn = findViewById(R.id.login_btn);
        mobileEt = findViewById(R.id.login_email_et);
        passwordEt = findViewById(R.id.login_password_et);
        radioGroup = findViewById(R.id.login_radio_group);
        relative_google=findViewById(R.id.relative_google);
        relative_face=findViewById(R.id.relative_face);
        relative_google.setOnClickListener(v->{
//            GoogleLogin();
        });
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        stateMachine.observe(this, new Observer<DataFetchState<LoginApiModel>>() {
            @Override
            public void onChanged(DataFetchState dataFetchState) {
                switch (dataFetchState.status) {
                    case ERROR: {
                        dismissLoader();
                        Toast.makeText(LoginActivity.this, dataFetchState.status_message, Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case LOADING: {
                        showLoader();
                        break;
                    }
                    case SUCCESS: {
                        dismissLoader();
                        Toast.makeText(LoginActivity.this, dataFetchState.status_message, Toast.LENGTH_SHORT).show();

                        switch (viewModel.userType) {
                            case "Seller": {
                                if (viewModel.isStoreSetup.equals("Yes")) {
                                    startActivity(new Intent(LoginActivity.this, OurOrdersActivity.class));
                                }
                                else {
                                    startActivity(new Intent(LoginActivity.this, StoreDetailsStepActivity.class));
                                }
                                finish();
                                break;
                            }
                            case "Buyer" : {
                                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                finish();
                                break;
                            }


                        }
                        break;
                    }
                }
            }
        });
    }
    private void GoogleLogin(){
        // Configure Google Sign In
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Toast.makeText(this, account.getEmail(), Toast.LENGTH_SHORT).show();
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately

                // [START_EXCLUDE]

                // [END_EXCLUDE]
            }
        }
    }
}