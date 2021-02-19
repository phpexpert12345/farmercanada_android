package com.farmers.buyers.modules.inbox.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 01-02-2021 at 16:56
 * mohammadsajjad679@gmail.com
 */

public class NotificationListItems implements RecyclerViewListItem {
    String orderNumber;
    String farmName;
    String image;
    int orderStatus;
    String date;
    Boolean isRead;

    public NotificationListItems(String orderNumber, String farmName, String image, int orderStatus, String date, Boolean isRead) {
        this.orderNumber = orderNumber;
        this.farmName = farmName;
        this.image = image;
        this.orderStatus = orderStatus;
        this.date = date;
        this.isRead = isRead;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getFarmName() {
        return farmName;
    }

    public String getImage() {
        return image;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public String getDate() {
        return date;
    }

    public Boolean getRead() {
        return isRead;
    }

    @Override
    public int getViewType() {
        return CardConstant.NOTIFICATION_LIST_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
