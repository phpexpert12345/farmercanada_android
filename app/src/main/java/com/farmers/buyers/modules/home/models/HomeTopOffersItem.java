package com.farmers.buyers.modules.home.models;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 26-01-2021 at 13:12
 * mohammadsajjad679@gmail.com
 */

public class HomeTopOffersItem implements RecyclerViewListItem {
    private String offerName;
    private String offerImageUri;

    public HomeTopOffersItem(String offerName, String offerImageUri) {
        this.offerName = offerName;
        this.offerImageUri = offerImageUri;
    }

    public String getOfferName() {
        return offerName;
    }

    public String getOfferImageUri() {
        return offerImageUri;
    }

    @Override
    public int getViewType() {
        return CardConstant.HOME_TOP_OFFER_ITEM_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
