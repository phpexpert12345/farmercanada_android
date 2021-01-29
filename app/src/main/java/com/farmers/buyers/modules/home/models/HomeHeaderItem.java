package com.farmers.buyers.modules.home.models;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 27-01-2021 at 13:23
 * mohammadsajjad679@gmail.com
 */

public class HomeHeaderItem implements RecyclerViewListItem {
    String userName, address;

    public HomeHeaderItem(String name, String address) {
        this.userName = name;
        this.address = address;

    }

    public String getUserName() {
        return userName;
    }

    public String getAddress() {
        return address;
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
