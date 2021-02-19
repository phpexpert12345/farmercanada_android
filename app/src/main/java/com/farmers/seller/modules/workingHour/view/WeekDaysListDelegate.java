package com.farmers.seller.modules.workingHour.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

public class WeekDaysListDelegate extends BaseDelegate {
    WeekDaysListViewHolder.WeekDayItemClickListener weekDayItemClickListener;

    public WeekDaysListDelegate(WeekDaysListViewHolder.WeekDayItemClickListener weekDayItemClickListener) {
        this.weekDayItemClickListener = weekDayItemClickListener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new WeekDaysListViewHolder(parent, weekDayItemClickListener);
    }
}
