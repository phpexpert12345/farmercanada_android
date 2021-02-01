package com.farmers.buyers.modules.followers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.followers.adapter.FollowersAdapter;

import java.util.ArrayList;
import java.util.List;

public class FollowersActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private FollowersAdapter adapter;
    private List<RecyclerViewListItem> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);

        ToolbarConfig config = new ToolbarConfig("Followers", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        }, true, new ToolbarMenuConfig(R.drawable.ic_notification, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }));

        setupToolbar(config);
        prepareItems();
        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.followers_transformer);
        adapter = new FollowersAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.updateData(items);
    }

    private void prepareItems() {
        items.addAll(FollowersTransformer.getFollowersItems());
    }

    @Override
    public Boolean showToolbar() {
        return true;
    }
}