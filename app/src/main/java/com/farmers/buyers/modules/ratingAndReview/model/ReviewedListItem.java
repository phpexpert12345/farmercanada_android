package com.farmers.buyers.modules.ratingAndReview.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

public class ReviewedListItem implements RecyclerViewListItem {

    String orderNumber;
    String farmName;
    String image;
    int orderStatus;
    String date;
    Boolean isRead;

    public ReviewedListItem(String orderNumber, String farmName, String image, int orderStatus, String date, Boolean isRead) {
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
        return CardConstant.REVIEWED_ITEMS_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
