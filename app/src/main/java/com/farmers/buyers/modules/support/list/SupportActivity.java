package com.farmers.buyers.modules.support.list;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.support.SupportTransformer;
import com.farmers.buyers.modules.support.adapter.SupportAdapter;

import java.util.ArrayList;
import java.util.List;

public class SupportActivity extends BaseActivity {

    private RecyclerView rv_support_list;
    private SupportAdapter adapter;
    private List<RecyclerViewListItem> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        setupToolbar(new ToolbarConfig("24*7 Customer Support", true, new View.OnClickListener() {
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

    private void init() {
        rv_support_list = findViewById(R.id.rv_support_list);
        adapter = new SupportAdapter();
        rv_support_list.setAdapter(adapter);
        rv_support_list.setLayoutManager(new LinearLayoutManager(this));
        prepareItems();
        adapter.updateData(items);
    }

    private void prepareItems() {
        items.addAll(SupportTransformer.getSupportList());
    }

    @Override
    public Boolean showToolbar() {
        return true;
    }
}