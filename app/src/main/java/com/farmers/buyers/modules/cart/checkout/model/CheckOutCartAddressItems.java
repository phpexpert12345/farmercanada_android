package com.farmers.buyers.modules.cart.checkout.model;

import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 30-01-2021 at 11:58
 * mohammadsajjad679@gmail.com
 */

public class CheckOutCartAddressItems implements RecyclerViewListItem {
    @Override
    public int getViewType() {
        return CardConstant.MY_CART_ADDRESS_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
