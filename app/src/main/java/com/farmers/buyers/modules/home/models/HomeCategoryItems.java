package com.farmers.buyers.modules.home.models;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

public class HomeCategoryItems implements RecyclerViewListItem {
    private String category;
    private int imgUri;
    private String imgUrl;
    private String catId;

    public HomeCategoryItems(String category, String imgUrl, int imgUri, String catId) {
        this.category = category;
        this.imgUrl = imgUrl;
        this.imgUri = imgUri;
        this.catId = catId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCategory() {
        return category;
    }

    public int getImgUri() {
        return imgUri;
    }

    public String getCatId() {
        return catId;
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
