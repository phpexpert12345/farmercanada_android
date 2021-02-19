package com.farmers.buyers.modules.farmDetail.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 28-01-2021 at 11:50
 * mohammadsajjad679@gmail.com
 */

public class FarmDetailsVegetablesListItem implements RecyclerViewListItem {

    private List<RecyclerViewListItem> item;

    public FarmDetailsVegetablesListItem(List<RecyclerViewListItem> item) {
        this.item = item;
    }

    public List<RecyclerViewListItem> getItem() {
        return item;
    }

    @Override
    public int getViewType() {
        return CardConstant.FARM_DETAIL_VEGETABLE_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
