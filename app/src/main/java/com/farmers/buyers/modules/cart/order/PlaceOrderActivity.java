package com.farmers.buyers.modules.cart.order;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.farmers.buyers.R;
import com.farmers.buyers.app.App;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.common.model.SimpleTitleItem;
import com.farmers.buyers.common.model.SingleTextItem;
import com.farmers.buyers.common.model.StripePay;
import com.farmers.buyers.common.utils.EqualSpacingItemDecoration;
import com.farmers.buyers.common.utils.LinearSpacesItemDecoration;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.address.model.AddressApiModel;
import com.farmers.buyers.modules.cart.MyCartTransformer;
import com.farmers.buyers.modules.cart.OrderSuccessDialog;
import com.farmers.buyers.modules.cart.checkout.model.CheckOutCartAddressItems;
import com.farmers.buyers.modules.cart.myCart.model.MyCartItem;
import com.farmers.buyers.modules.cart.myCart.model.cartList.CartListResponse;
import com.farmers.buyers.modules.cart.myCart.model.cartList.CartReqParam;
import com.farmers.buyers.modules.cart.myCart.model.cartList.FarmProductCartList;
import com.farmers.buyers.modules.cart.myCart.model.chargeTax.TaxData;
import com.farmers.buyers.modules.cart.order.adapter.PlaceOrderAdapter;
import com.farmers.buyers.modules.cart.order.adapter.TimeListAdapter;
import com.farmers.buyers.modules.cart.order.model.submit.SubmitRequestParam;
import com.farmers.buyers.modules.cart.order.model.submit.SubmitResponse;
import com.farmers.buyers.modules.cart.order.view.PlaceOrderSlotItemViewHolder;
import com.farmers.buyers.modules.signUp.SignUpViewModel;
import com.farmers.buyers.modules.signUp.model.SignUpApiModel;
import com.farmers.buyers.remote.ApiConstants;
import com.farmers.buyers.storage.Constant;
import com.farmers.buyers.storage.SharedPreferenceManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stripe.android.ApiResultCallback;
import com.stripe.android.Stripe;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.stripe.android.view.CardMultilineWidget;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PlaceOrderActivity extends BaseActivity implements OrderSuccessDialog.OnDialogClickListeners,
        PlaceOrderSlotItemViewHolder.SlotCheckedListener, TimeListAdapter.TimeItemClickListener {

    private RecyclerView recyclerView;
    private Button paymentButton;
    private PlaceOrderAdapter adapter;
    CheckOutCartAddressItems address;
    private List<RecyclerViewListItem> items = new ArrayList<>();
    private OrderSuccessDialog dialog;
    private Dialog pay_dialog;
    String price,quantity,itemid,item_unit_type,str_sizeid,extraitemid;
    Double subTotalAmount = 0.0;
    String order_type;
    String pay_type;
    TaxData taxData;
    String date;
    String time;
    StripePay stripePay;

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
    private MutableLiveData<DataFetchState<CartListResponse>> cartListMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<AddressApiModel>> dateStateMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<AddressApiModel>> timeStateMachine = new MutableLiveData<>();
    private AppController appController = AppController.get();
    private RecyclerView.Adapter mTimeAdapter;
    private RecyclerView rv_time_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        App.finish_activity=false;
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
        prepareItems();
        init();
        listener();




        dateStateMachine.observe(this, response -> {
            switch (response.status) {
                case LOADING:
                    showLoader();
                    break;
                case SUCCESS:
                    dismissLoader();
                    adapter.updateData(viewModel.items);
                    date=response.data.getData().getAllDateList().get(0).current_date;
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
                    mTimeAdapter = new TimeListAdapter(PlaceOrderActivity.this, response.data.getData().getAllTimeList(),
                            this);
                    rv_time_list.setAdapter(mTimeAdapter);
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
            if(time==null){
                Toast.makeText(this, "Please select time", Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent = new Intent();
                intent.putExtra("time", time);
                intent.putExtra("date", date);
                setResult(Activity.RESULT_OK, intent);
                finish();

            }

//            SubmitRequestParam param = new SubmitRequestParam(appController.getAuthenticationKey(),
//                    "0",
//                    "0",
//                    "",
//                    "",
//                    address.getAddress(),
//                    "",
//                    String.valueOf(type),
//                    "",
//                    time,
//                    date,
//                    "0",
//                    String.valueOf(taxData.getDiscountAmount()),
//                    taxData.getSubTotal(),
//                    taxData.getDeliveryCharge(),
//                    taxData.getDeliveryCharge(),
//                    taxData.getPackageFeeAmount(),
//                    taxData.getgSTTaxAmount(),
//                    subTotalAmount.toString(),
//                    String.valueOf(pay_type),
//                    address.getAddress_id(),
//                    appController.getLoginId(),
//                    "",
//                    item_unit_type,
//                    str_sizeid,
//                    price,
//                    quantity,
//                    itemid,
//                    "",
//                    SharedPreferenceManager.getInstance().getSharedPreferences("FARM_ID", "").toString());
//
//            viewModel.submitOrder(submitMachine, param);
        });
    }

    private void prepareItems() {
        items.add(new SimpleTitleItem("Choose delivery slot for this address"));
        items.add(MyCartTransformer.getPlaceOrderSlot());
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
date=slot;
        SubmitRequestParam requestParam = new SubmitRequestParam(
                AppController.get().getAuthenticationKey(),
                AppController.get().getLoginId(),
                String.valueOf(SharedPreferenceManager.getInstance().getSharedPreferences("FARM_ID", "")),
                slot);
        viewModel.getOrderTimeByDate(timeStateMachine, requestParam);
    }

    @Override
    public void onTimeItemClicked(String timeSlot) {
        time=timeSlot;
        Toast.makeText(PlaceOrderActivity.this, timeSlot, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(App.finish_activity){
            finish();
        }
    }


}