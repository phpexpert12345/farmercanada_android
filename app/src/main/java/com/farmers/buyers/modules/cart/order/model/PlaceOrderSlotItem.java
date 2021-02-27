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
    String monthName;

    public PlaceOrderSlotItem(String day, String date,String monthName) {
        this.day = day;
        this.date = date;
        this.monthName=monthName;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
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
