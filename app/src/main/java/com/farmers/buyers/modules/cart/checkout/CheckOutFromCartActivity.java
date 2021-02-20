package com.farmers.buyers.modules.cart.checkout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.common.model.SimpleTitleItem;
import com.farmers.buyers.common.utils.EqualSpacingItemDecoration;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.address.MyAddressActivity;
import com.farmers.buyers.modules.address.view.MyAddressListViewHolder;
import com.farmers.buyers.modules.cart.MyCartTransformer;
import com.farmers.buyers.modules.cart.checkout.adapter.CheckOutCartItemAdapter;
import com.farmers.buyers.modules.cart.checkout.model.CheckOutCartAddressItems;
import com.farmers.buyers.modules.cart.checkout.model.PaymentMethodsItems;
import com.farmers.buyers.modules.cart.checkout.view.CheckOutFromCartAddressViewHolder;
import com.farmers.buyers.modules.cart.myCart.MyCartViewModel;
import com.farmers.buyers.modules.cart.myCart.model.MyCartCheckOutItem;
import com.farmers.buyers.modules.cart.myCart.model.applyCoupon.ApplyCouponResponse;
import com.farmers.buyers.modules.cart.myCart.model.chargeTax.TaxData;
import com.farmers.buyers.modules.cart.myCart.model.chargeTax.TaxResponse;
import com.farmers.buyers.modules.cart.myCart.view.MyCartCheckoutViewHolder;
import com.farmers.buyers.modules.cart.order.PlaceOrderActivity;
import com.farmers.buyers.modules.orders.OrderSingleton;
import com.farmers.buyers.storage.Constant;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CheckOutFromCartActivity extends BaseActivity implements MyCartCheckoutViewHolder.MyCartCheckOutClickListeners, MyCartCheckoutViewHolder.MyCoupounClickListeners, CheckOutFromCartAddressViewHolder.ChangeAddressCallback {

    private RecyclerView recyclerView;
    TaxData taxData;
    private CheckOutCartItemAdapter adapter;
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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out_from_cart);
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

        Intent intent=getIntent();
        taxData=(TaxData)intent.getSerializableExtra(Constant.DATA_INTENT);
        taxData.setApplyCouponButton(false);
        taxData.setRemoveDiscountButton(false);
        if (taxData.getDiscountAmount()>1){
            taxData.setDiscountTextView(true);
        } else{
            taxData.setDiscountTextView(false);
        }
        taxData.setDiscountAmount(OrderSingleton.getInstance().getCoupon_discount_amount());

        CheckOutCartAddressItems addressItems=new CheckOutCartAddressItems("","My Home Addres", "4623 William Head Rd", "Victoria, BC V9C 3Y7, Canada", true, true);
        prepareItem(taxData,addressItems);
        init();

    }

    private void init() {
        recyclerView = findViewById(R.id.check_out_from_cart_recyclerView);
        adapter = new CheckOutCartItemAdapter(this,this,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new EqualSpacingItemDecoration(50, EqualSpacingItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
        adapter.updateData(items);
    }

    private void prepareItem(TaxData taxData,CheckOutCartAddressItems addressItems) {
        items.clear();
        items.add(new SimpleTitleItem("Delivery Address"));
       // items.add(new CheckOutCartAddressItems("","My Home Addres", "4623 William Head Rd", "Victoria, BC V9C 3Y7, Canada", true, true));
        items.add(addressItems);
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
        Intent intent=new Intent(CheckOutFromCartActivity.this,PlaceOrderActivity.class);
        intent.putExtra(Constant.DATA_INTENT,taxData);
        startActivity(intent);

    }


    @Override
    public void onCouponClicked(String couponCode) {

    }



    @Override
    public void onEditAddressClicked(CheckOutCartAddressItems addressDetail) {

        Intent intent=new Intent(CheckOutFromCartActivity.this, MyAddressActivity.class);
        startActivityForResult(intent, 1254);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1254)
        {
            CheckOutCartAddressItems address=(CheckOutCartAddressItems)data.getSerializableExtra(Constant.DATA_INTENT);
            prepareItem(taxData,address);
            adapter.updateData(items);
        }
    }
}