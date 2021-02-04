package com.farmers.buyers.modules.support.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

public class SupportListItem implements RecyclerViewListItem {
    @Override
    public int getViewType() {
        return CardConstant.SUPPORT_ITEMS_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
