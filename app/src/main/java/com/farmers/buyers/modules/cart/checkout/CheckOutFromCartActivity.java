package com.farmers.buyers.modules.cart.checkout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.model.SimpleTitleItem;
import com.farmers.buyers.common.utils.DroidPrefs;
import com.farmers.buyers.common.utils.EqualSpacingItemDecoration;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.address.MyAddressActivity;
import com.farmers.buyers.modules.cart.MyCartTransformer;
import com.farmers.buyers.modules.cart.checkout.adapter.CheckOutCartItemAdapter;
import com.farmers.buyers.modules.cart.checkout.model.CheckOutCartAddressItems;
import com.farmers.buyers.modules.cart.checkout.model.PaymentMethodsItems;
import com.farmers.buyers.modules.cart.checkout.view.CheckOutFromCartAddressViewHolder;
import com.farmers.buyers.modules.cart.checkout.view.PaymentMethodsViewHolder;
import com.farmers.buyers.modules.cart.myCart.MyCartViewModel;
import com.farmers.buyers.modules.cart.myCart.model.chargeTax.TaxData;
import com.farmers.buyers.modules.cart.myCart.view.MyCartCheckoutViewHolder;
import com.farmers.buyers.modules.cart.order.PlaceOrderActivity;
import com.farmers.buyers.modules.farmDetail.model.FarmDeliveryStatus;
import com.farmers.buyers.modules.orders.OrderSingleton;
import com.farmers.buyers.storage.Constant;
import com.farmers.buyers.storage.GPSTracker;
import com.farmers.buyers.storage.SharedPreferenceManager;

import java.util.ArrayList;
import java.util.List;

public class CheckOutFromCartActivity extends BaseActivity implements MyCartCheckoutViewHolder.MyCartCheckOutClickListeners,
        MyCartCheckoutViewHolder.MyCoupounClickListeners, CheckOutFromCartAddressViewHolder.ChangeAddressCallback,
        PaymentMethodsViewHolder.PaymentMethodListener {

    private RecyclerView recyclerView;
    TaxData taxData;
    private CheckOutCartItemAdapter adapter;
    CheckOutCartAddressItems address;
    private FarmDeliveryStatus farmDeliveryStatus;
    private List<RecyclerViewListItem> items = new ArrayList<>();
    private ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(MyCartViewModel.class)) {
                return (T) new MyCartViewModel();
            }
            return null;
        }
    };
    private GPSTracker gpsTracker;
    private int paymentType = -1;
    private String pay_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out_from_cart);
        gpsTracker = new GPSTracker(this);
        farmDeliveryStatus= DroidPrefs.get(this,"delivery_radius",FarmDeliveryStatus.class);

        setupToolbar(new ToolbarConfig("CheckOut", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        }, true, new ToolbarMenuConfig(R.drawable.ic_notification, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        })));

        Intent intent = getIntent();
        taxData = (TaxData) intent.getSerializableExtra(Constant.DATA_INTENT);
        taxData.setApplyCouponButton(false);
        taxData.setRemoveDiscountButton(false);
        if (taxData.getDiscountAmount() > 1) {
            taxData.setDiscountTextView(true);
        } else {
            taxData.setDiscountTextView(false);
        }
        taxData.setDiscountAmount(OrderSingleton.getInstance().getCoupon_discount_amount());

        CheckOutCartAddressItems addressItems = new CheckOutCartAddressItems(
                "",
                "Change Address",
                "Please change address",
                "",
                true,
                true);

        prepareItem(taxData, addressItems);

        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.check_out_from_cart_recyclerView);
        adapter = new CheckOutCartItemAdapter(this, this, this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new EqualSpacingItemDecoration(50, EqualSpacingItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
        adapter.updateData(items);
    }

    private void prepareItem(TaxData taxData, CheckOutCartAddressItems addressItems) {
        items.clear();
        if (String.valueOf(SharedPreferenceManager.getInstance().getSharedPreferences("SERVICE_TYPE", "")).equals("0")) {
            items.add(new SimpleTitleItem("Delivery Address"));
            items.add(addressItems);
        } else {

        }
        items.add(new SimpleTitleItem("Payment Methods"));
        items.add(new PaymentMethodsItems());
        items.add(MyCartTransformer.getTaxDataItem(taxData));
    }

    @Override
    public Boolean showToolbar() {
        return true;
    }

    @Override
    public void onCheckOutClicked() {

        if (paymentType == -1) {
            Toast.makeText(CheckOutFromCartActivity.this, "Please choose payment method", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(CheckOutFromCartActivity.this, PlaceOrderActivity.class);
            intent.putExtra(Constant.DATA_INTENT, taxData);
            intent.putExtra("address",address);
            intent.putExtra("payment_type",paymentType);
            startActivity(intent);
        }
    }

    @Override
    public void onCouponClicked(String couponCode) {

    }

    @Override
    public void onEditAddressClicked(CheckOutCartAddressItems addressDetail) {
        Intent intent = new Intent(CheckOutFromCartActivity.this, MyAddressActivity.class);
        startActivityForResult(intent, 1254);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1254) {
            if (data != null) {
                 address = (CheckOutCartAddressItems) data.getSerializableExtra(Constant.DATA_INTENT);
                 if(address!=null){
                     double dis=distance(farmDeliveryStatus.farm_lat,farmDeliveryStatus.farm_long,address.getAddress_lat(),address.getAddress_long());
                     dis=dis*1.609;
                     if(dis>=farmDeliveryStatus.farm_delivery_status){
                         Toast.makeText(this, "We Don't Deliver here...", Toast.LENGTH_SHORT).show();
                     }
                     else{
                         Toast.makeText(this, "We Deliver here...", Toast.LENGTH_SHORT).show();
                     }
                 }

                prepareItem(taxData, address);
                adapter.updateData(items);
            }
        }
    }

    @Override
    public void onPaymentMethodCheckChangeListener(int type,String pay_type) { //Cash 0, Card 1, Wallet 2
        this.paymentType = type;
        this.pay_type=pay_type;
    }
    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = deg2rad(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0); }
}