package com.farmers.seller.modules.broadcastMessage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.utils.AlertHelper;
import com.farmers.buyers.common.utils.OnAlertClickListener;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.seller.addProduct.AddProductActivity;
import com.farmers.buyers.modules.seller.product.ProductListActivity;
import com.farmers.buyers.modules.support.SupportTransformer;
import com.farmers.buyers.modules.support.adapter.SupportAdapter;
import com.farmers.seller.modules.broadcastMessage.BroadCastMessageTransformer;
import com.farmers.seller.modules.broadcastMessage.adapter.BroadCastMessageListAdapter;
import com.farmers.seller.modules.broadcastMessage.model.BroadcastMessageListItem;
import com.farmers.seller.modules.broadcastMessage.model.BroadcastMessageResponse;
import com.farmers.seller.modules.broadcastMessage.view.BroadcastMessageListViewHolder;
import com.farmers.seller.modules.ourOrders.OurOrdersViewModel;
import com.farmers.seller.modules.ourOrders.adapter.OurOrderListAdapter;
import com.farmers.seller.modules.ourOrders.model.AllOrderResponse;
import com.farmers.seller.modules.viewOrderDetails.ViewOrderDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class BroadcastMessageActivity extends BaseActivity implements BroadcastMessageListViewHolder.BroadcastItemClickListener,
        View.OnClickListener {
    private ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(BroadcastMessageViewModel.class)) {
                return (T) new BroadcastMessageViewModel();
            }
            return null;
        }
    };

    public BroadcastMessageViewModel viewModel = factory.create(BroadcastMessageViewModel.class);
    private MutableLiveData<DataFetchState<BroadcastMessageResponse>> broadcastMessageStateMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<BroadcastMessageResponse>> deleteBroadcastMessageStateMachine = new MutableLiveData<>();

    private RecyclerView rv_broadcast_list;
    private BroadCastMessageListAdapter adapter;
    public LinearLayout ll_create_message;
    private LinearLayout ll_data_not_available;
    private TextView tv_error_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_message);

        setupToolbar(new ToolbarConfig("Broadcast Message", true, new View.OnClickListener() {
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

    @Override
    public Boolean showToolbar() {
        return true;
    }

    private void init() {
        ll_create_message = findViewById(R.id.ll_create_message);

        rv_broadcast_list = findViewById(R.id.rv_broadcast_list);
        adapter = new BroadCastMessageListAdapter(this);
        rv_broadcast_list.setAdapter(adapter);
        rv_broadcast_list.setLayoutManager(new LinearLayoutManager(this));
        ll_data_not_available = findViewById(R.id.ll_data_not_available);
        tv_error_msg = findViewById(R.id.tv_error_msg);

        broadcastMessageStateMachine.observe(this, farmListResponseDataFetchState -> {
            switch (farmListResponseDataFetchState.status) {
                case ERROR:
                    dismissLoader();
                    rv_broadcast_list.setVisibility(View.GONE);
                    tv_error_msg.setText(farmListResponseDataFetchState.status_message);
                    ll_data_not_available.setVisibility(View.VISIBLE);
                    Toast.makeText(this, farmListResponseDataFetchState.status_message, Toast.LENGTH_SHORT).show();
                    break;
                case SUCCESS:
                    dismissLoader();
                    rv_broadcast_list.setVisibility(View.VISIBLE);
                    tv_error_msg.setText(farmListResponseDataFetchState.status_message);
                    ll_data_not_available.setVisibility(View.GONE);
                    adapter.updateData(viewModel.items);
                    break;
                case LOADING:
                    showLoader();
                    break;
            }
        });

        deleteBroadcastMessageStateMachine.observe(this, farmListResponseDataFetchState -> {
            switch (farmListResponseDataFetchState.status) {
                case ERROR:
                    dismissLoader();
                    Toast.makeText(this, farmListResponseDataFetchState.status_message, Toast.LENGTH_SHORT).show();
                    break;
                case SUCCESS:
                    dismissLoader();
                    viewModel.getBroadcastMessage(broadcastMessageStateMachine);
                    break;
                case LOADING:
                    showLoader();
                    break;
            }
        });

        ll_create_message.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getBroadcastMessage(broadcastMessageStateMachine);
    }

    private void prepareItems() {
    }

    @Override
    public void onBroadcastItemClicked(BroadcastMessageListItem item) {
        AlertHelper.showAlert(BroadcastMessageActivity.this, "Broadcast Message",
                "Are you sure you want to delete this message", true, "Ok",
                false, "", true, new OnAlertClickListener() {
                    @Override
                    public void onNegativeBtnClicked() {
                        finish();
                    }

                    @Override
                    public void onPositiveBtnClicked() {
                        viewModel.deleteBroadcastMessage(deleteBroadcastMessageStateMachine, item.getMessageID());
                    }
                });
    }

    @Override
    public void onBroadcastItemEditClicked(BroadcastMessageListItem item) {
        startActivity(new Intent(BroadcastMessageActivity.this, CreateMessageActivity.class).
                putExtra("FROM", "Edit").
                putExtra("messageId", item.getMessageID()).
                putExtra("Title", item.getMessage_title()).
                putExtra("Message", item.getMessage_content()).
                putExtra("PublishOn", item.getTarget_audience()));
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(BroadcastMessageActivity.this, CreateMessageActivity.class).
                putExtra("FROM", "Add"));
    }
}