package com.farmers.buyers.modules.wallet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.app.App;
import com.farmers.buyers.common.model.SimpleTitleItem;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.accountDetails.AccountDetailsActivity;
import com.farmers.buyers.modules.addMoney.AddMoneyWallet;
import com.farmers.buyers.modules.home.homeFragment.HomeFragmentViewModel;
import com.farmers.buyers.modules.home.models.AllDataModel;
import com.farmers.buyers.modules.wallet.adapter.WalletHistoryAdapter;
import com.farmers.buyers.modules.wallet.model.WalletHeaderItems;
import com.farmers.buyers.modules.wallet.view.WalletHeaderViewHolder;
import com.farmers.buyers.modules.wallet.view.WalletViewModel;

import java.util.ArrayList;
import java.util.List;

public class WalletActivity extends BaseActivity implements WalletHeaderViewHolder.WalletHeaderClickListener {

    private ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(WalletViewModel.class)) {
                return (T) new WalletViewModel();
            }
            return null;
        }
    };
    public WalletViewModel viewModel = factory.create(WalletViewModel.class);
    private MutableLiveData<DataFetchState<AllDataModel>> stateMachine = new MutableLiveData<>();

    private RecyclerView recyclerView;
    private WalletHistoryAdapter adapter;
    private LinearLayout ll_add_money;
    private LinearLayout ll_data_not_available;
    private TextView tv_error_msg;
    private TextView text_wallet;
    private ImageView image_back_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
//        setupToolbar(new ToolbarConfig("Wallet", true, new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        }, false, new ToolbarMenuConfig(R.drawable.ic_notification, new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        })));
        init();
    }

    private void init() {
        ll_data_not_available = findViewById(R.id.ll_data_not_available);
        tv_error_msg = findViewById(R.id.tv_error_msg);
        ll_add_money = findViewById(R.id.ll_add_money);
        recyclerView = findViewById(R.id.wallet_recyclerView);
        text_wallet=findViewById(R.id.text_wallet);
        text_wallet.setText("Wallet");
        image_back_button=findViewById(R.id.wallet_back);
        image_back_button.setOnClickListener(v->{
            App.wallet_updated=true;
            onBackClicked();
        });
        adapter = new WalletHistoryAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        stateMachine.observe(this, dataFetchState -> {
            switch (dataFetchState.status) {
                case ERROR: {
                    dismissLoader();
                    Toast.makeText(this, dataFetchState.status_message, Toast.LENGTH_SHORT).show();
                    recyclerView.setVisibility(View.GONE);
                    tv_error_msg.setText(dataFetchState.status_message);
                    ll_data_not_available.setVisibility(View.VISIBLE);
                    break;
                }
                case LOADING: {
                    showLoader();
                    break;
                }
                case SUCCESS: {
                    success();
                    break;
                }
            }
        });

        ll_add_money.setOnClickListener(view -> startActivityForResult(new Intent(WalletActivity.this, AddMoneyWallet.class),34));
        getWalletHistoryData();
    }

    private void getWalletHistoryData() {
        viewModel.getWalletHistoryList(stateMachine,this);
    }

    private void success() {
        dismissLoader();

        adapter.updateData(viewModel.items);
    }

    @Override
    public Boolean showToolbar() {
        return false;
    }

    @Override
    public void onBackClicked() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        App.wallet_updated=true;
       finish();

    }

    @Override
    public void onWithdrawClicked() {
        startActivity(new Intent(this, AccountDetailsActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK){
            if(requestCode==34){

                getWalletHistoryData();
            }
        }
    }
}