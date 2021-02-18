package com.farmers.buyers.modules.cart.checkout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.model.SimpleTitleItem;
import com.farmers.buyers.common.utils.EqualSpacingItemDecoration;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.cart.checkout.adapter.CheckOutCartItemAdapter;
import com.farmers.buyers.modules.cart.checkout.model.CheckOutCartAddressItems;
import com.farmers.buyers.modules.cart.checkout.model.PaymentMethodsItems;
import com.farmers.buyers.modules.cart.myCart.model.MyCartCheckOutItem;
import com.farmers.buyers.modules.cart.myCart.view.MyCartCheckoutViewHolder;
import com.farmers.buyers.modules.cart.order.PlaceOrderActivity;

import java.util.ArrayList;
import java.util.List;

public class CheckOutFromCartActivity extends BaseActivity implements MyCartCheckoutViewHolder.MyCartCheckOutClickListeners, MyCartCheckoutViewHolder.MyCoupounClickListeners {

    private RecyclerView recyclerView;
    private CheckOutCartItemAdapter adapter;
    private List<RecyclerViewListItem> items = new ArrayList<>();

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

        prepareItem();
        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.check_out_from_cart_recyclerView);
        adapter = new CheckOutCartItemAdapter(this,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new EqualSpacingItemDecoration(50, EqualSpacingItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
        adapter.updateData(items);
    }

    private void prepareItem() {
        items.add(new SimpleTitleItem("Delivery Address"));
        items.add(new CheckOutCartAddressItems("My Home Addres", "4623 William Head Rd", "Victoria, BC V9C 3Y7, Canada", true,true));
        items.add(new SimpleTitleItem("Payment Methods"));
        items.add(new PaymentMethodsItems());
        items.add(new MyCartCheckOutItem());
    }

    @Override
    public Boolean showToolbar() {
        return true;
    }

    @Override
    public void onCheckOutClicked() {
        startActivity(new Intent(this, PlaceOrderActivity.class));
    }

    @Override
    public void onCouponClicked(String couponCode) {

    }
}