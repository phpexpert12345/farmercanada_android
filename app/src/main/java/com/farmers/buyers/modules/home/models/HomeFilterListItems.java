package com.farmers.buyers.modules.home.models;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 27-01-2021 at 11:24
 * mohammadsajjad679@gmail.com
 */

public class HomeFilterListItems implements RecyclerViewListItem {

    private List<RecyclerViewListItem> filterItems;

    public HomeFilterListItems(List<RecyclerViewListItem> item) {
        this.filterItems = item;
    }

    public List<RecyclerViewListItem> getItem() {
        return filterItems;
    }

    @Override
    public int getViewType() {
        return CardConstant.MULTIPLE_ITEM_TYPE_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}

