package com.farmers.buyers.modules.orders.track;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.orders.track.adapter.TrackOrderAdapter;
import com.farmers.buyers.modules.orders.track.model.TrackOrderHeaderItems;

import java.util.ArrayList;
import java.util.List;

public class TrackOrderActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private List<RecyclerViewListItem> items = new ArrayList<>();
    private TrackOrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_order);

        setupToolbar(new ToolbarConfig("Order", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        }, true, new ToolbarMenuConfig(R.drawable.ic_notification, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        })));

        prepareItems();
        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.track_order_recyclerView);
        adapter = new TrackOrderAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.updateData(items);
    }

    private void prepareItems() {
        items.add(TrackOrderTransformer.getTackOrderHeader());
        items.addAll(TrackOrderTransformer.getTrackOrder());
    }

    @Override
    public Boolean showToolbar() {
        return true;
    }
}