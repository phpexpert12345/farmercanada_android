package com.farmers.buyers.modules.cart.order.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

/**
 * created by Mohammad Sajjad
 * on 31-01-2021 at 00:27
 * mohammadsajjad679@gmail.com
 */

public class PlaceOrderSlotDelegate extends BaseDelegate {
    private PlaceOrderSlotItemViewHolder.SlotCheckedListener listener;

    public PlaceOrderSlotDelegate(PlaceOrderSlotItemViewHolder.SlotCheckedListener listener) {
        this.listener = listener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new PlaceOrderSlotViewHolder(parent, listener);
    }
}
