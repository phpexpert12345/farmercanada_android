package com.farmers.seller.modules.workingHour.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

public class PickupTimeListItem implements RecyclerViewListItem {
    int id;
    String interval;

    public PickupTimeListItem(int id, String interval) {
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
        return CardConstant.PICKUP_TIME_ITEM_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
