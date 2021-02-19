package com.farmers.buyers.modules.seller.coupon.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.seller.coupon.addCoupon.AddNewCouponActivity;
import com.farmers.buyers.modules.seller.coupon.list.ManageCouponTransformer;
import com.farmers.buyers.modules.seller.coupon.list.adapter.ManageCouponAdapter;

import java.util.ArrayList;
import java.util.List;

public class ManageCouponActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private ManageCouponAdapter adapter;
    private List<RecyclerViewListItem> items = new ArrayList<>();
    private LinearLayout ll_add_coupon;

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
        ll_add_coupon = findViewById(R.id.ll_add_coupon);

        ll_add_coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManageCouponActivity.this, AddNewCouponActivity.class));
            }
        });
    }

    private void prepareItems() {
        items.addAll(ManageCouponTransformer.getCoupons());
    }

    @Override
    public Boolean showToolbar() {
        return true;
    }
}