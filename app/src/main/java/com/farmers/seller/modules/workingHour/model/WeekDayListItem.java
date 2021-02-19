package com.farmers.seller.modules.workingHour.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

import java.util.List;

public class WeekDayListItem implements RecyclerViewListItem {

    int id;
    String interval;
    List<DropDownData> dropDownDataList;

    public WeekDayListItem(int id, String interval, List<DropDownData> dropDownDataList) {
        this.id = id;
        this.interval = interval;
        this.dropDownDataList = dropDownDataList;
    }

    public List<DropDownData> getDropDownDataList() {
        return dropDownDataList;
    }

    public void setDropDownDataList(List<DropDownData> dropDownDataList) {
        this.dropDownDataList = dropDownDataList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    @Override
    public int getViewType() {
        return CardConstant.WEEK_DAYS_ITEM_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
