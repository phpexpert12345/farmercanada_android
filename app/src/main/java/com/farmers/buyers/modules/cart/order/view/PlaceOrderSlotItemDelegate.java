package com.farmers.buyers.modules.cart.order.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

/**
 * created by Mohammad Sajjad
 * on 31-01-2021 at 00:27
 * mohammadsajjad679@gmail.com
 */

public class PlaceOrderSlotItemDelegate extends BaseDelegate {

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new PlaceOrderSlotItemViewHolder(parent);
    }
}
