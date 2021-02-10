package com.farmers.seller.modules.ourOrders.pastOrder;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseFragment;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.ratingAndReview.ReviewTransfarmer;
import com.farmers.buyers.modules.ratingAndReview.adapter.ReviewListAdapter;
import com.farmers.buyers.modules.ratingAndReview.review.ReviewFragment;
import com.farmers.seller.modules.ourOrders.OurOrdersTransformer;
import com.farmers.seller.modules.ourOrders.adapter.PastOrderListAdapter;
import com.farmers.seller.modules.ourOrders.view.PastOrderListViewHolder;
import com.farmers.seller.modules.viewOrderDetails.ViewOrderDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class PastOrderFragment extends BaseFragment implements PastOrderListViewHolder.PastOrderItemClickListener {

    private RecyclerView rv_review_list;
    private PastOrderListAdapter adapter;
    private List<RecyclerViewListItem> items = new ArrayList<>();

    public PastOrderFragment get() {
        return new PastOrderFragment();
    }

    public PastOrderFragment() {
    }

    @Override
    public String getTitle() {
        return "Past Order";
    }

    @Override
    public int getResourceFile() {
        return R.layout.fragment_past_order;
    }

    @Override
    public void bindView(View view) {

        rv_review_list = view.findViewById(R.id.rv_review_list);
        adapter = new PastOrderListAdapter(this);
        rv_review_list.setAdapter(adapter);
        rv_review_list.setLayoutManager(new LinearLayoutManager(baseActivity));
        prepareItems();
        adapter.updateData(items);
    }

    private void prepareItems() {
        items.addAll(OurOrdersTransformer.getPastOrderList());
    }

    public void getPastOrder() {
        items.addAll(OurOrdersTransformer.getPastOrderList());
    }

    @Override
    public void onPastOrderItemClicked(int position) {
        startActivity(new Intent(getContext(), ViewOrderDetailsActivity.class).
                putExtra("KEY", "order_delivered"));
    }
}