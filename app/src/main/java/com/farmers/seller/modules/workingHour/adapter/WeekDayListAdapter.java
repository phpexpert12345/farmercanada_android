package com.farmers.seller.modules.workingHour.adapter;

import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.storage.CardConstant;
import com.farmers.seller.modules.workingHour.view.StoreTimeListDelegate;
import com.farmers.seller.modules.workingHour.view.StoreTimeListViewHolder;
import com.farmers.seller.modules.workingHour.view.WeekDaysListDelegate;
import com.farmers.seller.modules.workingHour.view.WeekDaysListViewHolder;

public class WeekDayListAdapter extends BaseAdapter {

    WeekDaysListViewHolder.WeekDayItemClickListener weekDayItemClickListener;

    public WeekDayListAdapter(WeekDaysListViewHolder.WeekDayItemClickListener weekDayItemClickListener) {
        super();
        this.weekDayItemClickListener = weekDayItemClickListener;
        this.initDelegate();
    }

    @Override
    public void initDelegate() {
        delegates.put(CardConstant.WEEK_DAYS_ITEM_ADAPTER, new WeekDaysListDelegate(weekDayItemClickListener));
    }
}
