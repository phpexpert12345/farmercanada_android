package com.farmers.seller.modules.workingHour.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

public class PickupWeekDaysListDelegate extends BaseDelegate {
    PickupWeekDaysListViewHolder.PickupWeekDayItemClickListener pickupWeekDayItemClickListener;

    public PickupWeekDaysListDelegate(PickupWeekDaysListViewHolder.PickupWeekDayItemClickListener pickupWeekDayItemClickListener) {
        this.pickupWeekDayItemClickListener = pickupWeekDayItemClickListener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new PickupWeekDaysListViewHolder(parent, pickupWeekDayItemClickListener);
    }
}
