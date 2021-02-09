package com.farmers.seller.modules.ourOrders.adapter;

import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.modules.ratingAndReview.view.ReviewListDelegate;
import com.farmers.buyers.modules.ratingAndReview.view.ReviewListViewHolder;
import com.farmers.buyers.storage.CardConstant;
import com.farmers.seller.modules.ourOrders.view.OurOrderListDelegate;
import com.farmers.seller.modules.ourOrders.view.OurOrderListViewHolder;

public class OurOrderListAdapter extends BaseAdapter {

    OurOrderListViewHolder.OurOrderItemClickListener ourOrderItemClickListener;

    public OurOrderListAdapter(OurOrderListViewHolder.OurOrderItemClickListener ourOrderItemClickListener) {
        super();
        this.ourOrderItemClickListener = ourOrderItemClickListener;
        this.initDelegate();
    }

    @Override
    public void initDelegate() {
        delegates.put(CardConstant.OUR_ORDER_LIST_ADAPTER, new OurOrderListDelegate(ourOrderItemClickListener));
    }
}
