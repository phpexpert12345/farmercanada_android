package com.farmers.seller.modules.ourOrders.adapter;

import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.storage.CardConstant;
import com.farmers.seller.modules.broadcastMessage.view.BroadcastListDelegate;
import com.farmers.seller.modules.broadcastMessage.view.BroadcastMessageListViewHolder;
import com.farmers.seller.modules.ourOrders.view.SideMenuListDelegate;
import com.farmers.seller.modules.ourOrders.view.SideMenuListViewHolder;

public class SideMenuListAdapter extends BaseAdapter {

    SideMenuListViewHolder.SideMenuItemClickListener sideMenuItemClickListener;

    public SideMenuListAdapter(SideMenuListViewHolder.SideMenuItemClickListener sideMenuItemClickListener) {
        super();
        this.sideMenuItemClickListener = sideMenuItemClickListener;
        this.initDelegate();
    }

    @Override
    public void initDelegate() {
        delegates.put(CardConstant.SIDE_MENU_LIST_ADAPTER, new SideMenuListDelegate(sideMenuItemClickListener));
    }
}
