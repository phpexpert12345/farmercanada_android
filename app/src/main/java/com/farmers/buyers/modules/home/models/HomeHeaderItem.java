package com.farmers.buyers.modules.home.models;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 27-01-2021 at 13:23
 * mohammadsajjad679@gmail.com
 */

public class HomeHeaderItem implements RecyclerViewListItem {
    String userName, address, user_type, account_type;

    public HomeHeaderItem(String name, String address, String user_type, String account_type) {
        this.userName = name;
        this.address = address;
        this.user_type = user_type;
        this.account_type = account_type;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public String getUserName() {
        return userName;
    }

    public String getAddress() {
        return address;
    }

    public String getUser_type() {
        return user_type;
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
