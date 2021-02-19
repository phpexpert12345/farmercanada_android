package com.farmers.buyers.modules.home.models;

import android.os.Parcelable;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 27-01-2021 at 13:01
 * mohammadsajjad679@gmail.com
 */

public class DeliveryTypeItems implements RecyclerViewListItem {


    @Override
    public int getViewType() {
        return CardConstant.DELIVERY_TYPE_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
