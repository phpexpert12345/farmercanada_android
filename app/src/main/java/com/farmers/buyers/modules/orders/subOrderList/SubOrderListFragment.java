package com.farmers.buyers.modules.orders.subOrderList;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseFragment;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.orders.OrdersTransformer;
import com.farmers.buyers.modules.orders.SubOrderExtra;
import com.farmers.buyers.modules.orders.adapter.SubOrderItemAdapter;
import com.farmers.buyers.modules.orders.model.SubOrdersListItem;
import com.farmers.buyers.modules.orders.track.TrackOrderActivity;
import com.farmers.buyers.modules.orders.view.SubOrderItemViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 03-02-2021 at 00:32
 * mohammadsajjad679@gmail.com
 */

public class SubOrderListFragment extends BaseFragment implements SubOrderItemViewHolder.SubOrderItemClickListener {
    String title;
    SubOrderExtra extra;
    private List<RecyclerViewListItem> items = new ArrayList<>();
    private RecyclerView recyclerView;
    private SubOrderItemAdapter adapter;

    public SubOrderListFragment(String title, SubOrderExtra extra) {
        this.title = title;
        this.extra = extra;
    }

    public SubOrderListFragment get() {
        return new SubOrderListFragment(title, extra);
    }


    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getResourceFile() {
        return R.layout.sub_order_list_fragment;
    }

    @Override
    public void bindView(View view) {

        recyclerView = view.findViewById(R.id.sub_order_recyclerView);
        adapter = new SubOrderItemAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(baseActivity));

        switch (extra) {
            case PENDING: {
                items.addAll(OrdersTransformer.getPendingItems());
                break;
            }

            case ACCEPTED: {
                items.addAll(OrdersTransformer.getAcceptedItems());

                break;

            }

            case REJECTED: {
                items.addAll(OrdersTransformer.getRejectedItems());

                break;

            }
        }

        adapter.updateData(items);

    }

    @Override
    public void onSubOrderItemClicked(int position) {
        startActivity(new Intent(baseActivity, TrackOrderActivity.class));
    }
}
