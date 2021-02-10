package com.farmers.seller.modules.ourOrders.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

public class PastOrderListDelegate extends BaseDelegate {

    PastOrderListViewHolder.PastOrderItemClickListener pastOrderItemClickListener;

    public PastOrderListDelegate(PastOrderListViewHolder.PastOrderItemClickListener pastOrderItemClickListener) {
        this.pastOrderItemClickListener = pastOrderItemClickListener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new PastOrderListViewHolder(parent, pastOrderItemClickListener);
    }
}
