package com.farmers.seller.modules.ourOrders.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

public class SideMenuListItem implements RecyclerViewListItem {

    String name;
    int icon;
    int id;

    public SideMenuListItem(String name, int icon, int id) {
        this.name = name;
        this.icon = icon;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    @Override
    public int getViewType() {
        return CardConstant.SIDE_MENU_LIST_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
