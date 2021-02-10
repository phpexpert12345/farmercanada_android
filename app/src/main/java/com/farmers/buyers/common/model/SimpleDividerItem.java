package com.farmers.buyers.common.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 10-02-2021 at 15:06
 * mohammadsajjad679@gmail.com
 */

public class SimpleDividerItem implements RecyclerViewListItem {

    @Override
    public int getViewType() {
        return CardConstant.SIMPLE_DIVIDER_ITEM_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
