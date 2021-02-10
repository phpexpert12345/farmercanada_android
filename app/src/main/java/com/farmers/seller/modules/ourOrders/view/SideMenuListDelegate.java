package com.farmers.seller.modules.ourOrders.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

public class SideMenuListDelegate extends BaseDelegate {

    SideMenuListViewHolder.SideMenuItemClickListener sideMenuItemClickListener;

    public SideMenuListDelegate(SideMenuListViewHolder.SideMenuItemClickListener sideMenuItemClickListener) {
        this.sideMenuItemClickListener = sideMenuItemClickListener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new SideMenuListViewHolder(parent, sideMenuItemClickListener);
    }
}
