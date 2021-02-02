package com.farmers.buyers.modules.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.chat.adapter.ChatAdapter;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private ChatAdapter adapter;
    private List<RecyclerViewListItem> items = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_acttivity);
        setupToolbar(new ToolbarConfig("Chat", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        }, true, new ToolbarMenuConfig(R.drawable.ic_notification, new View.OnClickListener(){

            @Override
            public void onClick(View view) {

            }
        })));
    }

    private void init() {
        recyclerView = findViewById(R.id.chat_recyclerView);
        adapter = new ChatAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void prepareChatKItems() {

    }

    @Override
    public Boolean showToolbar() {
        return true;
    }
}