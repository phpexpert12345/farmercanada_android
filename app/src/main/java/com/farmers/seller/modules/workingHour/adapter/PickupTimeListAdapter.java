package com.farmers.seller.modules.workingHour.adapter;

import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.storage.CardConstant;
import com.farmers.seller.modules.workingHour.view.PickupTimeListDelegate;
import com.farmers.seller.modules.workingHour.view.PickupTimeListViewHolder;
import com.farmers.seller.modules.workingHour.view.StoreTimeListDelegate;
import com.farmers.seller.modules.workingHour.view.StoreTimeListViewHolder;

public class PickupTimeListAdapter extends BaseAdapter {

    PickupTimeListViewHolder.PickupTimeItemClickListener pickupTimeItemClickListener;

    public PickupTimeListAdapter(PickupTimeListViewHolder.PickupTimeItemClickListener pickupTimeItemClickListener) {
        super();
        this.pickupTimeItemClickListener = pickupTimeItemClickListener;
        this.initDelegate();
    }

    @Override
    public void initDelegate() {
        delegates.put(CardConstant.PICKUP_TIME_ITEM_ADAPTER, new PickupTimeListDelegate(pickupTimeItemClickListener));
    }
}
