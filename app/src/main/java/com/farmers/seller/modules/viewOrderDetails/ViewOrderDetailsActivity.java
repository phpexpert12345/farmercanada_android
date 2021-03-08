package com.farmers.seller.modules.viewOrderDetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.farmers.buyers.R;
import com.farmers.buyers.common.utils.AlertHelper;
import com.farmers.buyers.common.utils.OnAlertClickListener;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.seller.addProduct.AddProductActivity;
import com.farmers.seller.modules.ourOrders.OurOrdersViewModel;
import com.farmers.seller.modules.ourOrders.adapter.SubRecordAdapter;
import com.farmers.seller.modules.ourOrders.model.AllOrderResponse;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewOrderDetailsActivity extends BaseActivity implements View.OnClickListener {
    private ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(OurOrdersViewModel.class)) {
                return (T) new OurOrdersViewModel();
            }
            return null;
        }
    };
    public OurOrdersViewModel viewModel = factory.create(OurOrdersViewModel.class);
    private MutableLiveData<DataFetchState<AllOrderResponse>> orderDetailsStateMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<AllOrderResponse>> orderAcceptStateMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<AllOrderResponse>> orderDeclineStateMachine = new MutableLiveData<>();

    public String KEY = "";
    public String ORDER_NUMBER = "";
    public String mobileNumber = "";
    public LinearLayout ll_order_status, ll_order_delivered, ll_order_reject_accept, ll_calling, ll_order_reject, ll_order_accept;
    public Button bt_track_order;

    TextView tv_customer_name, tv_date, tv_order_number, tv_total_amount, tv_customer_address,
            tv_customer_contact, tv_customer_email, tv_customer_message;
    TextView tv_subtotal_amount, tv_delivery_amount, tv_service_tax, tv_discount, tv_invoice_total, tv_print_invoice;
    CircleImageView civ_farm_image;
    RecyclerView rv_sub_product_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order_details);

        setupToolbar(new ToolbarConfig("Order Details", true, new View.OnClickListener() {
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

    private void init() {

        KEY = getIntent().getStringExtra("KEY");
        ORDER_NUMBER = getIntent().getStringExtra("ORDER_NUMBER");

        ll_order_status = findViewById(R.id.ll_order_status);
        ll_order_delivered = findViewById(R.id.ll_order_delivered);
        ll_order_reject_accept = findViewById(R.id.ll_order_reject_accept);
        bt_track_order = findViewById(R.id.bt_track_order);

        tv_customer_name = findViewById(R.id.tv_customer_name);
        tv_date = findViewById(R.id.tv_date);
        tv_order_number = findViewById(R.id.tv_order_number);
        tv_total_amount = findViewById(R.id.tv_total_amount);
        tv_customer_address = findViewById(R.id.tv_customer_address);
        civ_farm_image = findViewById(R.id.civ_farm_image);
        rv_sub_product_list = findViewById(R.id.rv_sub_product_list);
        tv_customer_contact = findViewById(R.id.tv_customer_contact);
        tv_customer_email = findViewById(R.id.tv_customer_email);
        tv_customer_message = findViewById(R.id.tv_customer_message);
        tv_subtotal_amount = findViewById(R.id.tv_subtotal_amount);
        tv_delivery_amount = findViewById(R.id.tv_delivery_amount);
        tv_service_tax = findViewById(R.id.tv_service_tax);
        tv_discount = findViewById(R.id.tv_discount);
        tv_invoice_total = findViewById(R.id.tv_invoice_total);
        tv_print_invoice = findViewById(R.id.tv_print_invoice);
        ll_calling = findViewById(R.id.ll_calling);
        ll_order_reject = findViewById(R.id.ll_order_reject);
        ll_order_accept = findViewById(R.id.ll_order_accept);

        ll_order_status.setVisibility(View.GONE);
        ll_order_delivered.setVisibility(View.GONE);
        ll_order_reject_accept.setVisibility(View.GONE);

        ll_order_status.setOnClickListener(this);
        ll_order_delivered.setOnClickListener(this);
        ll_order_reject_accept.setOnClickListener(this);
        bt_track_order.setOnClickListener(this);
        ll_calling.setOnClickListener(this);
        ll_order_reject.setOnClickListener(this);
        ll_order_accept.setOnClickListener(this);

        viewModel.getOrderDetails(orderDetailsStateMachine, ORDER_NUMBER);

        orderDetailsStateMachine.observe(this, farmListResponseDataFetchState -> {
            switch (farmListResponseDataFetchState.status) {
                case ERROR:
                    dismissLoader();
                    Toast.makeText(this, farmListResponseDataFetchState.status_message, Toast.LENGTH_SHORT).show();
                    break;
                case SUCCESS:
                    successData(farmListResponseDataFetchState);
                    break;
                case LOADING:
                    showLoader();
                    break;
            }
        });

        orderAcceptStateMachine.observe(this, farmListResponseDataFetchState -> {
            switch (farmListResponseDataFetchState.status) {
                case ERROR:
                    dismissLoader();
                    Toast.makeText(this, farmListResponseDataFetchState.status_message, Toast.LENGTH_SHORT).show();
                    break;
                case SUCCESS:
                    successOrderAccept(farmListResponseDataFetchState);
                    break;
                case LOADING:
                    showLoader();
                    break;
            }
        });

        orderDeclineStateMachine.observe(this, farmListResponseDataFetchState -> {
            switch (farmListResponseDataFetchState.status) {
                case ERROR:
                    dismissLoader();
                    Toast.makeText(this, farmListResponseDataFetchState.status_message, Toast.LENGTH_SHORT).show();
                    break;
                case SUCCESS:
                    successOrderDecline(farmListResponseDataFetchState);
                    break;
                case LOADING:
                    showLoader();
                    break;
            }
        });

        if (KEY.equalsIgnoreCase("reject_accept")) {
            ll_order_reject_accept.setVisibility(View.VISIBLE);
        } else if (KEY.equalsIgnoreCase("order_preparing")) {
            ll_order_status.setVisibility(View.VISIBLE);
        } else if (KEY.equalsIgnoreCase("order_delivered")) {
            ll_order_delivered.setVisibility(View.VISIBLE);
        }
    }

    private void successOrderDecline(DataFetchState<AllOrderResponse> farmListResponseDataFetchState) {
        dismissLoader();
        viewModel.getOrderDetails(orderDetailsStateMachine, ORDER_NUMBER);
    }

    private void successOrderAccept(DataFetchState<AllOrderResponse> farmListResponseDataFetchState) {
        AlertHelper.showAlert(ViewOrderDetailsActivity.this, "Order Details",
                farmListResponseDataFetchState.status_message, true, "Ok",
                false, "", true, new OnAlertClickListener() {
                    @Override
                    public void onNegativeBtnClicked() {
                        viewModel.getOrderDetails(orderDetailsStateMachine, ORDER_NUMBER);
                        finish();
                    }

                    @Override
                    public void onPositiveBtnClicked() {
                        finish();
                    }
                });
    }

    private void successData(DataFetchState<AllOrderResponse> farmListResponseDataFetchState) {
        dismissLoader();
        Glide.with(this)
                .load(farmListResponseDataFetchState.data.getData().getGetOrderList().get(0).farm_logo)
                .placeholder(R.drawable.farm_image)
                .into(civ_farm_image);

        tv_customer_name.setText(farmListResponseDataFetchState.data.getData().getGetOrderList().get(0).customer_name);
        tv_date.setText(farmListResponseDataFetchState.data.getData().getGetOrderList().get(0).order_time);
        tv_order_number.setText(farmListResponseDataFetchState.data.getData().getGetOrderList().get(0).order_number);
        tv_total_amount.setText("$" + farmListResponseDataFetchState.data.getData().getGetOrderList().get(0).Total_amount);
        tv_customer_address.setText(farmListResponseDataFetchState.data.getData().getGetOrderList().get(0).farm_address);
        mobileNumber = farmListResponseDataFetchState.data.getData().getGetOrderList().get(0).customer_mobile;
        tv_customer_contact.setText(farmListResponseDataFetchState.data.getData().getGetOrderList().get(0).customer_mobile);
        tv_customer_email.setText(farmListResponseDataFetchState.data.getData().getGetOrderList().get(0).customer_email);
        tv_customer_address.setText(farmListResponseDataFetchState.data.getData().getGetOrderList().get(0).customer_address);
        // tv_customer_message.setText(farmListResponseDataFetchState.data.getData().getGetOrderList().get(0).customer_address);
        tv_subtotal_amount.setText("$" + farmListResponseDataFetchState.data.getData().getGetOrderList().get(0).subtotal);
        tv_delivery_amount.setText("$" + farmListResponseDataFetchState.data.getData().getGetOrderList().get(0).delivery_amount);
        tv_service_tax.setText("$" + farmListResponseDataFetchState.data.getData().getGetOrderList().get(0).service_tax_amount);
        tv_discount.setText("$" + farmListResponseDataFetchState.data.getData().getGetOrderList().get(0).discount_amount);

        try {
            Double total = Double.parseDouble(farmListResponseDataFetchState.data.getData().getGetOrderList().get(0).delivery_amount) +
                    Double.parseDouble(farmListResponseDataFetchState.data.getData().getGetOrderList().get(0).service_tax_amount) +
                    Double.parseDouble(farmListResponseDataFetchState.data.getData().getGetOrderList().get(0).discount_amount) +
                    Double.parseDouble(farmListResponseDataFetchState.data.getData().getGetOrderList().get(0).subtotal);
            tv_invoice_total.setText("$" + String.valueOf(total));
        } catch (Exception e) {
            tv_invoice_total.setText("$" + farmListResponseDataFetchState.data.getData().getGetOrderList().get(0).Total_amount);
        }

        SubRecordAdapter subRecordAdapter = new SubRecordAdapter(
                farmListResponseDataFetchState.data.getData().getGetOrderList().get(0).OrderRecordList);
        rv_sub_product_list.setHasFixedSize(true);
        rv_sub_product_list.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        rv_sub_product_list.setAdapter(subRecordAdapter);
        rv_sub_product_list.setNestedScrollingEnabled(false);
    }

    @Override
    public Boolean showToolbar() {
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_order_status:
                break;

            case R.id.ll_order_delivered:
                break;

            case R.id.ll_order_reject_accept:
                break;

            case R.id.bt_track_order:
                order_track_dialog(this);
                break;
            case R.id.ll_calling:
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + mobileNumber));//change the number
                startActivity(callIntent);
                break;
            case R.id.ll_order_reject:
                order_decline_dialog(this);
                break;

            case R.id.ll_order_accept:
                AlertHelper.showAlert(ViewOrderDetailsActivity.this, "Order Details",
                        "You want to accept this order ?", true, "Ok",
                        false, "", true, new OnAlertClickListener() {
                            @Override
                            public void onNegativeBtnClicked() {
                                finish();
                            }

                            @Override
                            public void onPositiveBtnClicked() {
                                viewModel.orderAccept(orderAcceptStateMachine, ORDER_NUMBER);
                            }
                        });
                break;
        }
    }

    public void order_track_dialog(Activity activity) {

        final Dialog dialog = new Dialog(activity, R.style.NewDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.order_track_dialog);

        Button bt_order_track = dialog.findViewById(R.id.bt_order_track);
        Button bt_continue = dialog.findViewById(R.id.bt_continue);

        bt_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        bt_order_track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(lp);
        dialog.show();
    }

    public void order_decline_dialog(Activity activity) {

        final Dialog dialog = new Dialog(activity, R.style.NewDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.order_decline_dialog);

        EditText ed_message = dialog.findViewById(R.id.ed_message);
        Button bt_order_track = dialog.findViewById(R.id.bt_order_track);
        Button bt_continue = dialog.findViewById(R.id.bt_continue);

        bt_continue.setOnClickListener(view -> {
            if (TextUtils.isEmpty(ed_message.getText().toString().trim())) {
                Toast.makeText(ViewOrderDetailsActivity.this, "Please enter message", Toast.LENGTH_SHORT).show();
            } else {
                dialog.dismiss();
                viewModel.orderDecline(orderAcceptStateMachine, ORDER_NUMBER,
                        ed_message.getText().toString().trim());
            }
        });

        bt_order_track.setOnClickListener(view -> dialog.dismiss());

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(lp);
        dialog.show();
    }
}