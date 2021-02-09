package com.farmers.seller.modules.ourOrders.ourOrder;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.View;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseFragment;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.ratingAndReview.ReviewTransfarmer;
import com.farmers.seller.modules.ourOrders.OurOrdersTransformer;
import com.farmers.seller.modules.ourOrders.adapter.OurOrderListAdapter;
import com.farmers.seller.modules.ourOrders.view.OurOrderListViewHolder;
import com.farmers.seller.modules.viewOrderDetails.ViewOrderDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class OurOrdersFragment extends BaseFragment implements OurOrderListViewHolder.OurOrderItemClickListener {

    private RecyclerView rv_review_list;
    private OurOrderListAdapter adapter;
    private List<RecyclerViewListItem> items = new ArrayList<>();

    public OurOrdersFragment get() {
        return new OurOrdersFragment();
    }

    public OurOrdersFragment() {
    }

    @Override
    public String getTitle() {
        return "Our Order's";
    }

    @Override
    public int getResourceFile() {
        return R.layout.fragment_our_orders;
    }

    @Override
    public void bindView(View view) {

        rv_review_list = view.findViewById(R.id.rv_review_list);
        adapter = new OurOrderListAdapter(this);
        rv_review_list.setAdapter(adapter);
        rv_review_list.setLayoutManager(new LinearLayoutManager(baseActivity));
        prepareItems();
        adapter.updateData(items);

    }

    private void prepareItems() {
        items.addAll(OurOrdersTransformer.getOurOrderList());
    }

    public void getOurOrder() {
        items.addAll(OurOrdersTransformer.getOurOrderList());
    }

    @Override
    public void onOurOrderItemClicked(int position) {
        startActivity(new Intent(getContext(), ViewOrderDetailsActivity.class).
                putExtra("KEY", "reject_accept"));
    }
}