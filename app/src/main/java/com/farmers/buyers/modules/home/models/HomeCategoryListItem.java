package com.farmers.buyers.modules.home.models;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 26-01-2021 at 01:52
 * mohammadsajjad679@gmail.com
 */

public class HomeCategoryListItem implements RecyclerViewListItem {
    public List<RecyclerViewListItem> homeCategoryItems;
    public HomeCategoryListItem(List<RecyclerViewListItem> categoryItems) {
        this.homeCategoryItems = categoryItems;
    }
    @Override
    public int getViewType() {
        return CardConstant.HOME_CATEGORY_ITEM_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}


