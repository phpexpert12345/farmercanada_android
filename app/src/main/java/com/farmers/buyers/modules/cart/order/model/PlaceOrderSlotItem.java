package com.farmers.buyers.modules.cart.order.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 31-01-2021 at 00:27
 * mohammadsajjad679@gmail.com
 */

public class PlaceOrderSlotItem implements RecyclerViewListItem {
    String day;
    String date;

    public PlaceOrderSlotItem(String day, String date) {
        this.day = day;
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public String getDate() {
        return date;
    }

    @Override
    public int getViewType() {
        return CardConstant.PLACE_ORDER_ITEM_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
