package com.farmers.buyers.modules.farmDetail.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 28-01-2021 at 11:38
 * mohammadsajjad679@gmail.com
 */

public class FarmDetailsHeaderItems implements RecyclerViewListItem {
    public int imageUri;

    public FarmDetailsHeaderItems(int imageUri) {
        this.imageUri = imageUri;
    }

    public int getImageUri() {
        return imageUri;
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
