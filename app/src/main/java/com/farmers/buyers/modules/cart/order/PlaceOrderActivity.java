package com.farmers.buyers.modules.cart.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.common.model.SimpleTitleItem;
import com.farmers.buyers.common.model.SingleTextItem;
import com.farmers.buyers.common.utils.EqualSpacingItemDecoration;
import com.farmers.buyers.common.utils.LinearSpacesItemDecoration;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.address.model.AddressApiModel;
import com.farmers.buyers.modules.cart.MyCartTransformer;
import com.farmers.buyers.modules.cart.OrderSuccessDialog;
import com.farmers.buyers.modules.cart.myCart.model.chargeTax.TaxData;
import com.farmers.buyers.modules.cart.order.adapter.PlaceOrderAdapter;
import com.farmers.buyers.modules.cart.order.adapter.TimeListAdapter;
import com.farmers.buyers.modules.cart.order.model.submit.SubmitRequestParam;
import com.farmers.buyers.modules.cart.order.model.submit.SubmitResponse;
import com.farmers.buyers.modules.cart.order.view.PlaceOrderSlotItemViewHolder;
import com.farmers.buyers.modules.signUp.SignUpViewModel;
import com.farmers.buyers.modules.signUp.model.SignUpApiModel;
import com.farmers.buyers.storage.Constant;
import com.farmers.buyers.storage.SharedPreferenceManager;

import java.util.ArrayList;
import java.util.List;

public class PlaceOrderActivity extends BaseActivity implements OrderSuccessDialog.OnDialogClickListeners,
        PlaceOrderSlotItemViewHolder.SlotCheckedListener, TimeListAdapter.TimeItemClickListener {

    private RecyclerView recyclerView;
    private Button paymentButton;
    private PlaceOrderAdapter adapter;
    private OrderSuccessDialog dialog;
    private ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(SubmitOrderViewModel.class)) {
                return (T) new SubmitOrderViewModel();
            }
            return null;
        }
    };
    private SubmitOrderViewModel viewModel = factory.create(SubmitOrderViewModel.class);
    private MutableLiveData<DataFetchState<SubmitResponse>> submitMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<AddressApiModel>> dateStateMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<AddressApiModel>> timeStateMachine = new MutableLiveData<>();
    private AppController appController = AppController.get();
    private RecyclerView.Adapter mTimeAdapter;
    private RecyclerView rv_time_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        ToolbarConfig config = new ToolbarConfig("Add Date & Time", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        }, true, new ToolbarMenuConfig(R.drawable.ic_notification, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }));

        setupToolbar(config);
        init();
        listener();

        dateStateMachine.observe(this, response -> {
            switch (response.status) {
                case LOADING:
                    showLoader();
                    break;
                case SUCCESS:
                    dismissLoader();
                    Toast.makeText(PlaceOrderActivity.this, response.data.getStatus_message(), Toast.LENGTH_SHORT).show();
                    adapter.updateData(viewModel.items);
                    callTimeSlot(response.data.getData().getAllDateList().get(0).current_date);
                    break;
                case ERROR:
                    dismissLoader();
                    break;
            }
        });

        timeStateMachine.observe(this, response -> {
            switch (response.status) {
                case LOADING:
                    showLoader();
                    break;
                case SUCCESS:
                    dismissLoader();
                    Toast.makeText(PlaceOrderActivity.this, response.data.getStatus_message(), Toast.LENGTH_SHORT).show();
                    mTimeAdapter = new TimeListAdapter(PlaceOrderActivity.this, response.data.getData().getAllTimeList(),
                            this);
                    rv_time_list.setAdapter(mTimeAdapter);
                    break;
                case ERROR:
                    dismissLoader();
                    break;
            }
        });

        submitMachine.observe(this, response -> {
            switch (response.status) {
                case LOADING:
                    showLoader();
                    break;
                case SUCCESS:
                    dismissLoader();
                    Toast.makeText(PlaceOrderActivity.this, response.data.getStatusMessage(), Toast.LENGTH_SHORT).show();
                    break;
                case ERROR:
                    dismissLoader();
                    break;
            }
        });
    }

    private void callTimeSlot(String date) {

        SubmitRequestParam requestParam = new SubmitRequestParam(
                AppController.get().getAuthenticationKey(),
                AppController.get().getLoginId(),
                String.valueOf(SharedPreferenceManager.getInstance().getSharedPreferences("FARM_ID", "")),
                date);
        viewModel.getOrderTimeByDate(timeStateMachine, requestParam);
    }

    private void init() {
        recyclerView = findViewById(R.id.place_order_recyclerView);
        paymentButton = findViewById(R.id.place_order_btn);
        adapter = new PlaceOrderAdapter(this);
        dialog = new OrderSuccessDialog(this, this, false);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new EqualSpacingItemDecoration(40, EqualSpacingItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        rv_time_list = findViewById(R.id.rv_time_list);
        rv_time_list.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        SubmitRequestParam requestParam = new SubmitRequestParam(AppController.get().getAuthenticationKey(),
                AppController.get().getLoginId());

        viewModel.getOrderDate(dateStateMachine, requestParam);
    }

    private void listener() {
        paymentButton.setOnClickListener(view -> {
            Intent intent = getIntent();
            TaxData taxData = (TaxData) intent.getSerializableExtra(Constant.DATA_INTENT);
            SubmitRequestParam param = new SubmitRequestParam(appController.getAuthenticationKey(),
                    "0",
                    "0",
                    "201301",
                    "Noida",
                    "Sectore -62, Noida",
                    "",
                    "0",
                    "",
                    "10:00 PM",
                    "2021-02-20",
                    "0",
                    String.valueOf(taxData.getDiscountAmount()),
                    "335",
                    taxData.getDeliveryCharge(),
                    taxData.getDeliveryCharge(),
                    taxData.getPackageFeeAmount(),
                    taxData.getgSTTaxAmount(),
                    "300",
                    "Paypal",
                    "2",
                    appController.getLoginId(),
                    "0,0",
                    "kg,gram",
                    "123_1,124_0",
                    "10.00,14.00",
                    "1,1",
                    "123,124",
                    "132323646545",
                    "2");

            viewModel.submitOrder(submitMachine, param);
        });
    }

    @Override
    public Boolean showToolbar() {
        return true;
    }

    @Override
    public void onCancelClicked() {
        dialog.dismissDialog();
    }

    @Override
    public void onSubmitClicked() {
        dialog.dismissDialog();
        startActivity(new Intent(this, OrderSuccessDetailActivity.class));
    }

    @Override
    public void onSlotSelected(String slot, int position) {

        adapter.notifyDataSetChanged();
        adapter.notifyItemChanged(position);

        SubmitRequestParam requestParam = new SubmitRequestParam(
                AppController.get().getAuthenticationKey(),
                AppController.get().getLoginId(),
                String.valueOf(SharedPreferenceManager.getInstance().getSharedPreferences("FARM_ID", "")),
                slot);
        viewModel.getOrderTimeByDate(timeStateMachine, requestParam);
    }

    @Override
    public void onTimeItemClicked(String timeSlot) {
        Toast.makeText(PlaceOrderActivity.this, timeSlot, Toast.LENGTH_SHORT).show();
    }
}