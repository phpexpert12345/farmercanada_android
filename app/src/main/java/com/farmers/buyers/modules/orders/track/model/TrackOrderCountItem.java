package com.farmers.buyers.modules.orders.track.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 09-02-2021 at 12:37
 * mohammadsajjad679@gmail.com
 */

public class TrackOrderCountItem implements RecyclerViewListItem {
    @Override
    public int getViewType() {
        return CardConstant.TRACK_ORDER_COUNT_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
