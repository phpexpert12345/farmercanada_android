package com.farmers.seller.modules.broadcastMessage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.seller.addProduct.AddProductActivity;
import com.farmers.buyers.modules.seller.product.ProductListActivity;
import com.farmers.buyers.modules.support.SupportTransformer;
import com.farmers.buyers.modules.support.adapter.SupportAdapter;
import com.farmers.seller.modules.broadcastMessage.BroadCastMessageTransformer;
import com.farmers.seller.modules.broadcastMessage.adapter.BroadCastMessageListAdapter;
import com.farmers.seller.modules.broadcastMessage.view.BroadcastMessageListViewHolder;
import com.farmers.seller.modules.ourOrders.adapter.OurOrderListAdapter;

import java.util.ArrayList;
import java.util.List;

public class BroadcastMessageActivity extends BaseActivity implements BroadcastMessageListViewHolder.BroadcastItemClickListener,
        View.OnClickListener {

    private RecyclerView rv_broadcast_list;
    private BroadCastMessageListAdapter adapter;
    private List<RecyclerViewListItem> items = new ArrayList<>();

    public LinearLayout ll_create_message;

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
        prepareItems();
        adapter.updateData(items);

        ll_create_message.setOnClickListener(this);
    }

    private void prepareItems() {
        items.addAll(BroadCastMessageTransformer.getBroadcastList());
    }

    @Override
    public void onBroadcastItemClicked(int position) {

    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(BroadcastMessageActivity.this, CreateMessageActivity.class));
    }

}