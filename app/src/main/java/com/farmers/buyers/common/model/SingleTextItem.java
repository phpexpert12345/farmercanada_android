package com.farmers.buyers.common.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 11-02-2021 at 11:02
 * mohammadsajjad679@gmail.com
 */

public class SingleTextItem implements RecyclerViewListItem {
    String title;

    public SingleTextItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int getViewType() {
        return CardConstant.SINGLE_TEXT_ITEM_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
