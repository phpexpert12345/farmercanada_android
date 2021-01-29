package com.farmers.buyers.common.model;

import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 27-01-2021 at 11:24
 * mohammadsajjad679@gmail.com
 */

public class MultipleTextItems implements RecyclerViewListItem {

    private String title;
    private Boolean clickable = false;

    public MultipleTextItems(String title, Boolean clickable) {
        this.title = title;
        this.clickable = clickable;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getClickable() {
        return clickable;
    }

    @Override
    public int getViewType() {
        return CardConstant.MULTIPLE_ITEM_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
