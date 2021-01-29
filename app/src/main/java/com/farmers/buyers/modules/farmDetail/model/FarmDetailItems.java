package com.farmers.buyers.modules.farmDetail.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 28-01-2021 at 11:44
 * mohammadsajjad679@gmail.com
 */

public class FarmDetailItems implements RecyclerViewListItem {
    String title;
    String address;
    String timing;
    String hostedBy;
    String rating;

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }

    public String getTiming() {
        return timing;
    }

    public String getHostedBy() {
        return hostedBy;
    }

    public String getRating() {
        return rating;
    }

    @Override
    public int getViewType() {
        return CardConstant.FARM_DETAIL_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
