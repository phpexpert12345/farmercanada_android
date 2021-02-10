package com.farmers.buyers.modules.seller.coupon.list;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.seller.coupon.list.ManageCouponTransformer;
import com.farmers.buyers.modules.seller.coupon.list.adapter.ManageCouponAdapter;

import java.util.ArrayList;
import java.util.List;

public class ManageCouponActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private ManageCouponAdapter adapter;
    private List<RecyclerViewListItem> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_coupon);
        setupToolbar(new ToolbarConfig("Manage Coupon", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        }, false, null));

        prepareItems();
        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.manage_coupon_recyclerView);
        adapter = new ManageCouponAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.updateData(items);
    }

    private void prepareItems() {
        items.addAll(ManageCouponTransformer.getCoupons());
    }

    @Override
    public Boolean showToolbar() {
        return true;
    }
}