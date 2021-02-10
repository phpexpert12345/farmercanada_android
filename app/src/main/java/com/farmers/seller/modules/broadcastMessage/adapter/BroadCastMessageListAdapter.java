package com.farmers.seller.modules.broadcastMessage.adapter;

import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.storage.CardConstant;
import com.farmers.seller.modules.broadcastMessage.view.BroadcastListDelegate;
import com.farmers.seller.modules.broadcastMessage.view.BroadcastMessageListViewHolder;
import com.farmers.seller.modules.ourOrders.view.OurOrderListDelegate;
import com.farmers.seller.modules.ourOrders.view.OurOrderListViewHolder;

public class BroadCastMessageListAdapter extends BaseAdapter {

    BroadcastMessageListViewHolder.BroadcastItemClickListener broadcastItemClickListener;

    public BroadCastMessageListAdapter(BroadcastMessageListViewHolder.BroadcastItemClickListener broadcastItemClickListener) {
        super();
        this.broadcastItemClickListener = broadcastItemClickListener;
        this.initDelegate();
    }

    @Override
    public void initDelegate() {
        delegates.put(CardConstant.BROAD_CAST_MESSAGE_LIST_ADAPTER, new BroadcastListDelegate(broadcastItemClickListener));
    }
}
