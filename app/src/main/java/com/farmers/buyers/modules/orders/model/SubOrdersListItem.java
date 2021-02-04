package com.farmers.buyers.modules.orders.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 02-02-2021 at 18:53
 * mohammadsajjad679@gmail.com
 */

public class SubOrdersListItem implements RecyclerViewListItem {
    String title;
    String orderId;
    String time;
    String amount;
    int status;
    int imageUri;

    public SubOrdersListItem(String title, String orderId, String time, String amount, int status, int imageUri) {
        this.title = title;
        this.orderId = orderId;
        this.time = time;
        this.amount = amount;
        this.status = status;
        this.imageUri = imageUri;
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

    public String getAmount() {
        return amount;
    }

    public int getStatus() {
        return status;
    }
    public int getImageUri() {
        return imageUri;
    }

    @Override
    public int getViewType() {
        return CardConstant.SUB_ORDER_ITEM_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
