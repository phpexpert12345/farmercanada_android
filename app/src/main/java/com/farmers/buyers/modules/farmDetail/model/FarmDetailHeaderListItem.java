package com.farmers.buyers.modules.farmDetail.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 28-01-2021 at 11:37
 * mohammadsajjad679@gmail.com
 */

public class FarmDetailHeaderListItem implements RecyclerViewListItem {
    public List<RecyclerViewListItem> item;

    public FarmDetailHeaderListItem(List<RecyclerViewListItem> item) {
        this.item = item;
    }

    public List<RecyclerViewListItem> getItem() {
        return item;
    }


    @Override
    public int getViewType() {
        return CardConstant.FARM_DETAIL_HEADER_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
