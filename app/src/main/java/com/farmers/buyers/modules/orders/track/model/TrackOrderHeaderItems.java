package com.farmers.buyers.modules.orders.track.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 03-02-2021 at 13:30
 * mohammadsajjad679@gmail.com
 */

public class TrackOrderHeaderItems implements RecyclerViewListItem {
    String logo;
    String name;
    String date;
    String OrderId;
    String deliveryTime;
    String totalItems;
    String totalAmount;
    Boolean canChange;
    int orderState;
    public String order_type;
    public String order_status_msg;

    public String getFarm_address() {
        return farm_address;
    }

    public void setFarm_address(String farm_address) {
        this.farm_address = farm_address;
    }

    String farm_address;

    public TrackOrderHeaderItems(String logo, String name, String date, String orderId, String deliveryTime, String totalItems,
                                 String totalAmount, Boolean canChange, int orderState, String order_type, String order_status_msg,String farm_address) {
        this.logo = logo;
        this.name = name;
        this.date = date;
        OrderId = orderId;
        this.deliveryTime = deliveryTime;
        this.totalItems = totalItems;
        this.totalAmount = totalAmount;
        this.canChange = canChange;
        this.orderState = orderState;
        this.order_type = order_type;
        this.order_status_msg = order_status_msg;
        this.farm_address=farm_address;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getOrder_status_msg() {
        return order_status_msg;
    }

    public void setOrder_status_msg(String order_status_msg) {
        this.order_status_msg = order_status_msg;
    }

    public String getLogo() {
        return logo;
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
