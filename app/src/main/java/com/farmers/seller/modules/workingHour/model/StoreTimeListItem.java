package com.farmers.seller.modules.workingHour.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

public class StoreTimeListItem implements RecyclerViewListItem {
    int id;
    String interval;

    public StoreTimeListItem(int id, String interval) {
        this.id = id;
        this.interval = interval;
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
        return CardConstant.STORE_TIME_ITEM_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
