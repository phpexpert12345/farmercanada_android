package com.farmers.buyers.modules.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.farmers.buyers.R;
import com.farmers.buyers.common.SpacesItemDecoration;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.home.models.HomeCategoryListItem;
import com.farmers.buyers.modules.home.models.HomeHeaderListItem;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private List<RecyclerViewListItem> items = new ArrayList<>();
    private RecyclerView recyclerView;
    private HomeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        prepareListItems();
        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.home_recyclerView);
        adapter = new HomeAdapter();
        recyclerView.setAdapter(adapter);

        GridLayoutManager manager = new GridLayoutManager(this, 2);

        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                 if(adapter.getItemAt(position) instanceof HomeHeaderListItem || adapter.getItemAt(position) instanceof HomeCategoryListItem) {
                    return 2;
                }else {
                    return 1;
                }
            }
        });

        recyclerView.setLayoutManager(manager);
        adapter.updateData(items);
    }

    private void prepareListItems() {
        items.add(HomeTransformer.getHeaderItems());
        items.add(HomeTransformer.getCategoryList());
        items.addAll(HomeTransformer.getHomeFarmListItem());
    }


}