package com.farmers.buyers.modules.ratingAndReview.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

public class ReviewListItem implements RecyclerViewListItem {
    @Override
    public int getViewType() {
        return CardConstant.REVIEW_ITEMS_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
