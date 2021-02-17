package com.farmers.buyers.modules.addMoney;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.farmers.buyers.R;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.addMoney.model.AddMoneyRequestParams;
import com.farmers.buyers.modules.signUp.SignUpActivity;
import com.farmers.buyers.modules.signUp.SignUpViewModel;
import com.farmers.buyers.modules.signUp.SubmitOtpActivity;
import com.farmers.buyers.modules.signUp.model.SignUpApiModel;
import com.farmers.buyers.modules.signUp.model.SignUpRequestParams;
import com.farmers.buyers.modules.wallet.WalletActivity;

import org.w3c.dom.Text;

public class AddMoneyWallet extends BaseActivity implements View.OnClickListener {

    private ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(AddMoneyViewModel.class)) {
                return (T) new AddMoneyViewModel();
            }
            return null;
        }
    };

    private AddMoneyViewModel viewModel = factory.create(AddMoneyViewModel.class);
    private MutableLiveData<DataFetchState<SignUpApiModel>> stateMachine = new MutableLiveData<>();

    public ImageView wallet_back_image;
    public TextView tv_remain_wallet_balance;
    public Button bt_add;
    public EditText ed_amount, ed_transaction_id, ed_status;
    private AppController appController = AppController.get();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money_wallet);

        init();
    }

    private void init() {
        wallet_back_image = findViewById(R.id.wallet_back_image);
        tv_remain_wallet_balance = findViewById(R.id.tv_remain_wallet_balance);
        bt_add = findViewById(R.id.bt_add);
        ed_amount = findViewById(R.id.ed_amount);
        ed_transaction_id = findViewById(R.id.ed_transaction_id);
        ed_status = findViewById(R.id.ed_status);

        tv_remain_wallet_balance.setText(appController.getWalletAmount());

        stateMachine.observe(this, signUpApiModelDataFetchState -> {
            switch (signUpApiModelDataFetchState.status) {
                case LOADING: {
                    loading();
                    break;
                }

                case SUCCESS: {
                    success(signUpApiModelDataFetchState.status_message);
                    break;
                }

                case ERROR: {
                    error(signUpApiModelDataFetchState.status_message);
                    break;
                }
            }
        });

        bt_add.setOnClickListener(this);
        wallet_back_image.setOnClickListener(this);
    }

    private void loading() {
        showLoader();
    }

    private void success(String msg) {
        dismissLoader();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AddMoneyWallet.this, WalletActivity.class);
        startActivity(intent);
        finish();
    }

    private void error(String error) {
        dismissLoader();
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Boolean showToolbar() {
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_add:
                addMoney();
                break;

            case R.id.wallet_back_image:
                finish();
                break;
        }
    }

    private void addMoney() {
        AddMoneyRequestParams addMoneyRequestParams = new AddMoneyRequestParams(AppController.get().getLoginId(),
                ed_amount.getText().toString().trim(),
                ed_transaction_id.getText().toString().trim(),
                ed_status.getText().toString().trim(), AppController.get().getAuthenticationKey());

        viewModel.addMoney(stateMachine, addMoneyRequestParams);
    }
}