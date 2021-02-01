package com.farmers.buyers.modules.cart.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.model.SimpleTitleItem;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.cart.MyCartTransformer;
import com.farmers.buyers.modules.cart.OrderSuccessDialog;
import com.farmers.buyers.modules.cart.order.adapter.PlaceOrderAdapter;

import java.util.ArrayList;
import java.util.List;

public class PlaceOrderActivity extends BaseActivity implements OrderSuccessDialog.OnDialogClickListeners {
    private RecyclerView recyclerView;
    private Button paymentButton;
    private PlaceOrderAdapter adapter;
    private List<RecyclerViewListItem> items = new ArrayList<>();
    private OrderSuccessDialog dialog ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        ToolbarConfig config = new ToolbarConfig("And Date & Time", true, new View.OnClickListener() {
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
    }

    private void init() {
        recyclerView = findViewById(R.id.place_order_recyclerView);
        paymentButton = findViewById(R.id.place_order_btn);
        adapter = new PlaceOrderAdapter();
        dialog = new OrderSuccessDialog(this, this, false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.updateData(items);
    }

    private void listener(){

        paymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.showDialog();
            }
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
}