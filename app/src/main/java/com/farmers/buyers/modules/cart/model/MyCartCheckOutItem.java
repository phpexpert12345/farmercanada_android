package com.farmers.buyers.modules.cart.model;

import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 29-01-2021 at 18:33
 * mohammadsajjad679@gmail.com
 */

public class MyCartCheckOutItem implements RecyclerViewListItem {

    @Override
    public int getViewType() {
        return CardConstant.MY_CART_CHECKOUT_ITEM_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
