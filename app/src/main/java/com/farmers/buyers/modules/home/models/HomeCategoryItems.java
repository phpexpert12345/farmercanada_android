package com.farmers.buyers.modules.home.models;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

public class HomeCategoryItems implements RecyclerViewListItem {
    private String category;
    public HomeCategoryItems(String category) {
        this.category = category;
    }
    @Override
    public int getViewType() {
        return CardConstant.HOME_CATEGORY_LIST_ITEM_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
