package com.farmers.buyers.modules.inbox.message;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseFragment;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.chat.ChatActivity;
import com.farmers.buyers.modules.inbox.InboxTransformer;
import com.farmers.buyers.modules.inbox.adapter.MessageListAdapter;
import com.farmers.buyers.modules.inbox.view.MessageListViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 01-02-2021 at 16:12
 * mohammadsajjad679@gmail.com
 */

public class MessageFragment extends BaseFragment implements MessageListViewHolder.MessageItemClickListener {

    private RecyclerView recyclerView;
    private MessageListAdapter adapter;
    private List<RecyclerViewListItem> items = new ArrayList<>();

    public MessageFragment get() {
        return new MessageFragment();
    }

    @Override
    public String getTitle() {
        return "Message";
    }

    @Override
    public int getResourceFile() {
        return R.layout.message_fragment_layout;
    }

    @Override
    public void bindView(View view) {
        recyclerView = view.findViewById(R.id.message_fragment_recyclerView);
        adapter = new MessageListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(baseActivity));
        prepareItems();
        adapter.updateData(items);
    }

    private void prepareItems() {
        items.addAll(InboxTransformer.getMessageList());
    }

    public void getMessages() {
        items.addAll(InboxTransformer.getMessageList());
        Log.e("message", "message");
    }

    @Override
    public void onMessageItemClicked(int position) {
        startActivity(new Intent(baseActivity, ChatActivity.class));
    }
}
