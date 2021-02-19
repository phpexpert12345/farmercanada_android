package com.farmers.seller.modules.workingHour.adapter;

import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.storage.CardConstant;
import com.farmers.seller.modules.ourOrders.view.OurOrderListDelegate;
import com.farmers.seller.modules.ourOrders.view.OurOrderListViewHolder;
import com.farmers.seller.modules.workingHour.view.StoreTimeListDelegate;
import com.farmers.seller.modules.workingHour.view.StoreTimeListViewHolder;

public class StoreTimeListAdapter extends BaseAdapter {

    StoreTimeListViewHolder.StoreTimeItemClickListener storeTimeItemClickListener;

    public StoreTimeListAdapter(StoreTimeListViewHolder.StoreTimeItemClickListener storeTimeItemClickListener) {
        super();
        this.storeTimeItemClickListener = storeTimeItemClickListener;
        this.initDelegate();
    }

    @Override
    public void initDelegate() {
        delegates.put(CardConstant.STORE_TIME_ITEM_ADAPTER, new StoreTimeListDelegate(storeTimeItemClickListener));
    }
}
