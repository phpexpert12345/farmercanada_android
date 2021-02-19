package com.farmers.seller.modules.broadcastMessage.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.seller.modules.ourOrders.view.OurOrderListViewHolder;

public class BroadcastListDelegate extends BaseDelegate {
    BroadcastMessageListViewHolder.BroadcastItemClickListener broadcastItemClickListener;

    public BroadcastListDelegate(BroadcastMessageListViewHolder.BroadcastItemClickListener broadcastItemClickListener) {
        this.broadcastItemClickListener = broadcastItemClickListener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new BroadcastMessageListViewHolder(parent, broadcastItemClickListener);
    }
}
