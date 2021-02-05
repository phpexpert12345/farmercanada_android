package com.farmers.buyers.modules.farmDetail;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.model.SimpleTitleItem;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.farmDetail.adapter.FarmDetailsAdapter;
import com.farmers.buyers.modules.farmDetail.view.FarmDetailHeaderViewHolder;
import com.farmers.buyers.modules.home.view.HomeHeaderViewHolder;

import java.util.ArrayList;
import java.util.List;

public class FarmDetailActivity extends BaseActivity implements HomeHeaderViewHolder.HeaderItemClickListener, FarmDetailHeaderViewHolder.FarmHeaderClickListener {
    private RecyclerView recyclerView;
    private FarmDetailsAdapter adapter;
    private List<RecyclerViewListItem> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_detail);
        prepareItems();
        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.farmers_detail_recyclerView);
        adapter = new FarmDetailsAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.updateData(items);
    }

    private void prepareItems() {
        items.add(FarmDetailTransformer.getHeaderItems());
        items.add(FarmDetailTransformer.getFarmDetailItems());
        items.add(new SimpleTitleItem("Vegetables"));
        items.add(FarmDetailTransformer.getFarmDetailVegList());
        items.add(new SimpleTitleItem("Fruits"));
        items.add(FarmDetailTransformer.getFarmDetailFruitList());
    }

    @Override
    public Boolean showToolbar() {
        return false;
    }

    @Override
    public void onEditAddressClickListener(int position) {
    }

    @Override
    public void onBecomeSellerClicked() {

    }

    @Override
    public void onOnBackClickListener() {
        onBackPressed();
    }
}