package com.farmers.buyers.modules.farmDetail.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 28-01-2021 at 11:38
 * mohammadsajjad679@gmail.com
 */

public class FarmDetailsHeaderItems implements RecyclerViewListItem {
    public String imageURL;

    public FarmDetailsHeaderItems(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getImageUri() {
        return imageURL;
    }

    @Override
    public int getViewType() {
        return CardConstant.FARM_DETAIL_HEADER_ITEM_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
