package com.farmers.buyers.modules.orders.track.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 03-02-2021 at 13:30
 * mohammadsajjad679@gmail.com
 */

public class TrackOrderHeaderItems implements RecyclerViewListItem {
    String name;
    String date;
    String OrderId;
    String deliveryTime;
    String totalItems;
    String totalAmount;
    Boolean canChange;
    int orderState;

    public TrackOrderHeaderItems(String name, String date, String orderId, String deliveryTime, String totalItems, String totalAmount, Boolean canChange, int orderState) {
        this.name = name;
        this.date = date;
        OrderId = orderId;
        this.deliveryTime = deliveryTime;
        this.totalItems = totalItems;
        this.totalAmount = totalAmount;
        this.canChange = canChange;
        this.orderState = orderState;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getOrderId() {
        return OrderId;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public String getTotalItems() {
        return totalItems;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public Boolean getCanChange() {
        return canChange;
    }

    public int getOrderState() {
        return orderState;
    }

    @Override
    public int getViewType() {
        return CardConstant.TRACK_ORDER_HEADER_ITEM;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
