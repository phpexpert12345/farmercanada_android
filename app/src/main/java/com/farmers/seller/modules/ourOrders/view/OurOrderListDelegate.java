package com.farmers.seller.modules.ourOrders.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.modules.ratingAndReview.view.ReviewListViewHolder;

public class OurOrderListDelegate extends BaseDelegate {

    OurOrderListViewHolder.OurOrderItemClickListener ourOrderItemClickListener;

    public OurOrderListDelegate(OurOrderListViewHolder.OurOrderItemClickListener ourOrderItemClickListener) {
        this.ourOrderItemClickListener = ourOrderItemClickListener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new OurOrderListViewHolder(parent, ourOrderItemClickListener);
    }
}
