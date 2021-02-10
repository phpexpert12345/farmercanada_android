package com.farmers.buyers.modules.seller.manageCalender.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 10-02-2021 at 22:08
 * mohammadsajjad679@gmail.com
 */

public class CalenderItems implements RecyclerViewListItem {
    String title;

    public CalenderItems(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int getViewType() {
        return CardConstant.CALENDER_ITEM_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
