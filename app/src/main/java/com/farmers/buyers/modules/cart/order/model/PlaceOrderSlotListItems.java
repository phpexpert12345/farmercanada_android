package com.farmers.buyers.modules.cart.order.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 31-01-2021 at 00:26
 * mohammadsajjad679@gmail.com
 */

public class PlaceOrderSlotListItems implements RecyclerViewListItem {
    public List<RecyclerViewListItem> item;

    public PlaceOrderSlotListItems(List<RecyclerViewListItem> item) {
        this.item = item;
    }

    public List<RecyclerViewListItem> getItem() {
        return item;
    }

    @Override
    public int getViewType() {
        return CardConstant.PLACE_ORDER_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
