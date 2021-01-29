package com.farmers.buyers.modules.home.models;

import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 27-01-2021 at 16:11
 * mohammadsajjad679@gmail.com
 */

public class HomeFarmTypeItem implements RecyclerViewListItem {


    public HomeFarmTypeItem() {

    }
    @Override
    public int getViewType() {
        return CardConstant.HOME_FARM_TYPE_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
