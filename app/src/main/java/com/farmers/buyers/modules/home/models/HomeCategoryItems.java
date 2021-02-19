package com.farmers.buyers.modules.home.models;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

public class HomeCategoryItems implements RecyclerViewListItem {
    private String category;
    private int imgUri;
    public HomeCategoryItems(String category, int imgUri) {
        this.category = category;
        this.imgUri = imgUri;
    }

    public String getCategory() {
        return category;
    }

    public int getImgUri() {
        return imgUri;
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
