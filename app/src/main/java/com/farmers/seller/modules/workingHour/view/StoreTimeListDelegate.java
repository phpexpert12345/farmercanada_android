package com.farmers.seller.modules.workingHour.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.seller.modules.ourOrders.view.OurOrderListViewHolder;

public class StoreTimeListDelegate extends BaseDelegate {
    StoreTimeListViewHolder.StoreTimeItemClickListener storeTimeItemClickListener;

    public StoreTimeListDelegate(StoreTimeListViewHolder.StoreTimeItemClickListener storeTimeItemClickListener) {
        this.storeTimeItemClickListener = storeTimeItemClickListener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new StoreTimeListViewHolder(parent, storeTimeItemClickListener);
    }
}
