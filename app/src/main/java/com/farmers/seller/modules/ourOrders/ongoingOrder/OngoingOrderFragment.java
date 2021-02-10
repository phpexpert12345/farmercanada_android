package com.farmers.seller.modules.ourOrders.ongoingOrder;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseFragment;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.ratingAndReview.ReviewTransfarmer;
import com.farmers.buyers.modules.ratingAndReview.adapter.ReviewListAdapter;
import com.farmers.seller.modules.ourOrders.OurOrdersTransformer;
import com.farmers.seller.modules.ourOrders.adapter.OngoingOrderListAdapter;
import com.farmers.seller.modules.ourOrders.pastOrder.PastOrderFragment;
import com.farmers.seller.modules.ourOrders.view.OngoingOrderListViewHolder;
import com.farmers.seller.modules.viewOrderDetails.ViewOrderDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class OngoingOrderFragment extends BaseFragment implements OngoingOrderListViewHolder.OngoingOrderItemClickListener {

    private RecyclerView rv_review_list;
    private OngoingOrderListAdapter adapter;
    private List<RecyclerViewListItem> items = new ArrayList<>();

    public OngoingOrderFragment get() {
        return new OngoingOrderFragment();
    }

    public OngoingOrderFragment() {
    }

    @Override
    public String getTitle() {
        return "Ongoing Order";
    }

    @Override
    public int getResourceFile() {
        return R.layout.fragment_ongoing_order;
    }

    @Override
    public void bindView(View view) {

        rv_review_list = view.findViewById(R.id.rv_review_list);
        adapter = new OngoingOrderListAdapter(this);
        rv_review_list.setAdapter(adapter);
        rv_review_list.setLayoutManager(new LinearLayoutManager(baseActivity));
        prepareItems();
        adapter.updateData(items);

    }

    private void prepareItems() {
        items.addAll(OurOrdersTransformer.getOngoingOrderList());
    }

    public void getOngoingOrder() {
        items.addAll(OurOrdersTransformer.getOngoingOrderList());
    }

    @Override
    public void onOngoingOrderItemClicked(int position) {
        startActivity(new Intent(getContext(), ViewOrderDetailsActivity.class).
                putExtra("KEY", "order_preparing"));
    }
}