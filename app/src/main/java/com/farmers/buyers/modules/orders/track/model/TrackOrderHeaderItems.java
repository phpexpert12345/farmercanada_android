package com.farmers.buyers.modules.orders.track.model;

import androidx.cardview.widget.CardView;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 03-02-2021 at 13:30
 * mohammadsajjad679@gmail.com
 */

public class TrackOrderHeaderItems implements RecyclerViewListItem {
    @Override
    public int getViewType() {
        return CardConstant.TRACK_ORDER_HEADER_ITEM;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
