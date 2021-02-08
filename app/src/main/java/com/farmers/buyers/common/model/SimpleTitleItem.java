package com.farmers.buyers.common.model;

import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 26-01-2021 at 12:49
 * mohammadsajjad679@gmail.com
 */

public class SimpleTitleItem implements RecyclerViewListItem {
    String title;
    int color = R.color.primaryTextColor;


    public SimpleTitleItem(String title, int color) {
        this.title = title;
        this.color = color;
    }

    public SimpleTitleItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public int getColor() {
        return color;
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
