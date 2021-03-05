package com.farmers.seller.modules.setupSellerAccount.serviceDetails.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;
import com.stripe.android.model.Card;

/**
 * Created by Mohammad sajjad on 04-03-2021.
 * mohammadsajjad679@gmail.com
 */
public class StoreDeliveryRangeItems implements RecyclerViewListItem {
    private String title;

    public StoreDeliveryRangeItems(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int getViewType() {
        return CardConstant.STORE_SETUP_DELIVERY_RANGE_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
