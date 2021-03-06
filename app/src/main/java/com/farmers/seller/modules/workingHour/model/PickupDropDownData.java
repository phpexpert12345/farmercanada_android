package com.farmers.seller.modules.workingHour.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

public class PickupDropDownData implements RecyclerViewListItem {

    int id;
    String name;

    public PickupDropDownData(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getViewType() {
        return CardConstant.PICKUP_ORDER_LIMIT_ITEM_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
