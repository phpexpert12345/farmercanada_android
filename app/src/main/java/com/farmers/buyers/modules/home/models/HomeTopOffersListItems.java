package com.farmers.buyers.modules.home.models;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 26-01-2021 at 13:12
 * mohammadsajjad679@gmail.com
 */

public class HomeTopOffersListItems implements RecyclerViewListItem {
    private List<HomeTopOffersItem> offersItems;

    public HomeTopOffersListItems(List<HomeTopOffersItem> offersItems) {
        this.offersItems = offersItems;
    }

    public List<HomeTopOffersItem> getOffersItems() {
        return offersItems;
    }

    @Override
    public int getViewType() {
        return CardConstant.HOME_TOP_OFFER_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
