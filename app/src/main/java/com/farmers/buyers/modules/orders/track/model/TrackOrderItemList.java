package com.farmers.buyers.modules.orders.track.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 03-02-2021 at 13:30
 * mohammadsajjad679@gmail.com
 */

public class TrackOrderItemList implements RecyclerViewListItem {
    @Override
    public int getViewType() {
        return CardConstant.TRACK_ORDER_ITEM_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
