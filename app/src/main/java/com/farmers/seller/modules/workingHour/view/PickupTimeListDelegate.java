package com.farmers.seller.modules.workingHour.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

public class PickupTimeListDelegate extends BaseDelegate {
    PickupTimeListViewHolder.PickupTimeItemClickListener pickupTimeItemClickListener;

    public PickupTimeListDelegate(PickupTimeListViewHolder.PickupTimeItemClickListener pickupTimeItemClickListener) {
        this.pickupTimeItemClickListener = pickupTimeItemClickListener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new PickupTimeListViewHolder(parent, pickupTimeItemClickListener);
    }
}
