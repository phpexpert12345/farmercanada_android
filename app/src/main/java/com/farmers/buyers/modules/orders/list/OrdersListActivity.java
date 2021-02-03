package com.farmers.buyers.modules.orders.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.farmers.buyers.R;
import com.farmers.buyers.common.model.SimpleTitleItem;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.orders.OrdersTransformer;
import com.farmers.buyers.modules.orders.adapter.OrdersAdapter;

import java.util.ArrayList;
import java.util.List;

public class OrdersListActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private OrdersAdapter adapter;
    private List<RecyclerViewListItem> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_list);
        setupToolbar(new ToolbarConfig("Order", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        }, true, new ToolbarMenuConfig(R.drawable.ic_notification, new View.OnClickListener(){

            @Override
            public void onClick(View view) {

            }
        })));

        prepareItems();
        init();

    }

    private void init() {
        recyclerView = findViewById(R.id.order_recyclerView);
        adapter = new OrdersAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.updateData(items);
    }

    private void prepareItems() {
        items.add(new SimpleTitleItem("Today"));
        items.addAll(OrdersTransformer.getOrders());
        items.add(new SimpleTitleItem("Yesterday"));
        items.addAll(OrdersTransformer.getYesterdayOrders());
    }

    @Override
    public Boolean showToolbar() {
        return true;
    }
}