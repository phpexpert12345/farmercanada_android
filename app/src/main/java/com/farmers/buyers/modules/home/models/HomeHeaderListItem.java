package com.farmers.buyers.modules.home.models;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 25-01-2021 at 21:03
 * mohammadsajjad679@gmail.com
 */

public class HomeHeaderListItem implements RecyclerViewListItem {
    String userName;
    String address;

    public HomeHeaderListItem(String userName, String address) {
        this.userName = userName;
        this.address = address;
    }


    @Override
    public int getViewType() {
        return CardConstant.HOME_HEADER_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
