package com.farmers.buyers.modules.inbox.notification;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseFragment;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.inbox.InboxTransformer;
import com.farmers.buyers.modules.inbox.adapter.NotificationListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 01-02-2021 at 16:03
 * mohammadsajjad679@gmail.com
 */

public class NotificationFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private NotificationListAdapter adapter;
    private List<RecyclerViewListItem> items = new ArrayList<>();

    public NotificationFragment get() {
        return new NotificationFragment();
    }

    @Override
    public String getTitle() {
        return "Notifications";
    }

    @Override
    public int getResourceFile() {
        return R.layout.notification_fragment_layout;
    }

    @Override
    public void bindView(View view) {
        recyclerView = view.findViewById(R.id.notification_fragment_recyclerView);
        adapter = new NotificationListAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(baseActivity));
        getNotification();
        adapter.updateData(items);
    }


    public void getNotification() {
        items.addAll(InboxTransformer.getNotificationItems());
    }
}
