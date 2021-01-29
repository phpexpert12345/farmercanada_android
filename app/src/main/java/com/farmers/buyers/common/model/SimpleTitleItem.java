package com.farmers.buyers.common.model;

import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 26-01-2021 at 12:49
 * mohammadsajjad679@gmail.com
 */

public class SimpleTitleItem implements RecyclerViewListItem {
    String title;

    public SimpleTitleItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int getViewType() {
        return CardConstant.SIMPLE_TITLE_ITEM_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
