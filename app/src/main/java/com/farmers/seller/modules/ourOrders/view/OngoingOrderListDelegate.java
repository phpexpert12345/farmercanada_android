package com.farmers.seller.modules.ourOrders.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

public class OngoingOrderListDelegate extends BaseDelegate {

    OngoingOrderListViewHolder.OngoingOrderItemClickListener ongoingOrderItemClickListener;

    public OngoingOrderListDelegate(OngoingOrderListViewHolder.OngoingOrderItemClickListener ongoingOrderItemClickListener) {
        this.ongoingOrderItemClickListener = ongoingOrderItemClickListener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new OngoingOrderListViewHolder(parent, ongoingOrderItemClickListener);
    }
}
