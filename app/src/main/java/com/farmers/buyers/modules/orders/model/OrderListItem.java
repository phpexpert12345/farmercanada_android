package com.farmers.buyers.modules.orders.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 02-02-2021 at 17:53
 * mohammadsajjad679@gmail.com
 */

public class OrderListItem implements RecyclerViewListItem {
    String title;
    String orderId;
    String time;

    public OrderListItem(String title, String orderId, String time) {
        this.title = title;
        this.orderId = orderId;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getTime() {
        return time;
    }

    @Override
    public int getViewType() {
        return CardConstant.ORDER_ITEMS_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
