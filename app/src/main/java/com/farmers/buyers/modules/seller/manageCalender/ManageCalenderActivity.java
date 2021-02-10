package com.farmers.buyers.modules.seller.manageCalender;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.utils.EqualSpacingItemDecoration;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.seller.manageCalender.adapter.ManageCalenderAdapter;

import java.util.ArrayList;
import java.util.List;

public class ManageCalenderActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private ManageCalenderAdapter adapter;
    private List<RecyclerViewListItem> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_calender);

        setupToolbar(new BaseActivity.ToolbarConfig("Manage Calender", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        }, false, null));

        prepareItems();
        init();
    }

    private void prepareItems() {
        items.addAll(CalenderTransformer.getCalenderItems());
    }


    private void init() {
        recyclerView = findViewById(R.id.manage_calender_days_recyclerView);
        adapter = new ManageCalenderAdapter();
        recyclerView.addItemDecoration(new EqualSpacingItemDecoration(30, EqualSpacingItemDecoration.HORIZONTAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
        adapter.updateData(items);
    }

    @Override
    public Boolean showToolbar() {
        return true;
    }
}