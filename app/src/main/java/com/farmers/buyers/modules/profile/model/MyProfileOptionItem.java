package com.farmers.buyers.modules.profile.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 31-01-2021 at 04:34
 * mohammadsajjad679@gmail.com
 */

public class MyProfileOptionItem implements RecyclerViewListItem {
    private List<RecyclerViewListItem> item;

    public MyProfileOptionItem(List<RecyclerViewListItem> item) {
        this.item = item;
    }

    public List<RecyclerViewListItem> getItem() {
        return item;
    }

    @Override
    public int getViewType() {
        return CardConstant.PROFILE_OPTION_MENU_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
