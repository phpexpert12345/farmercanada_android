package com.farmers.buyers.modules.wallet;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.model.SimpleTitleItem;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.wallet.adapter.WalletHistoryAdapter;
import com.farmers.buyers.modules.wallet.model.WalletHeaderItems;
import com.farmers.buyers.modules.wallet.view.WalletHeaderViewHolder;

import java.util.ArrayList;
import java.util.List;

public class WalletActivity extends BaseActivity implements WalletHeaderViewHolder.WalletHeaderClickListener {
    private RecyclerView recyclerView;
    private WalletHistoryAdapter adapter;
    private List<RecyclerViewListItem> items = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        prepareItems();
        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.wallet_recyclerView);
        adapter = new WalletHistoryAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.updateData(items);
    }

    private void prepareItems() {
        items.add(new WalletHeaderItems());
        items.add(new SimpleTitleItem("Today"));
        items.addAll(WalletTransformer.getWalletHistory());
        items.add(new SimpleTitleItem("Yesterday"));
        items.addAll(WalletTransformer.getYesterdayHistory());
    }

    @Override
    public Boolean showToolbar() {
        return false;
    }

    @Override
    public void onBackClicked() {
        onBackPressed();
    }

    @Override
    public void onWithdrawClicked() {
    }
}