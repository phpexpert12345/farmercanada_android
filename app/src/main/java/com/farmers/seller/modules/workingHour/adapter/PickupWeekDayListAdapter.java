package com.farmers.seller.modules.workingHour.adapter;

import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.storage.CardConstant;
import com.farmers.seller.modules.workingHour.view.PickupWeekDaysListDelegate;
import com.farmers.seller.modules.workingHour.view.PickupWeekDaysListViewHolder;
import com.farmers.seller.modules.workingHour.view.WeekDaysListDelegate;
import com.farmers.seller.modules.workingHour.view.WeekDaysListViewHolder;

public class PickupWeekDayListAdapter extends BaseAdapter {

    PickupWeekDaysListViewHolder.PickupWeekDayItemClickListener pickupWeekDayItemClickListener;

    public PickupWeekDayListAdapter(PickupWeekDaysListViewHolder.PickupWeekDayItemClickListener pickupWeekDayItemClickListener) {
        super();
        this.pickupWeekDayItemClickListener = pickupWeekDayItemClickListener;
        this.initDelegate();
    }

    @Override
    public void initDelegate() {
        delegates.put(CardConstant.PICKUP_WEEK_DAYS_ITEM_ADAPTER, new PickupWeekDaysListDelegate(pickupWeekDayItemClickListener));
    }
}
