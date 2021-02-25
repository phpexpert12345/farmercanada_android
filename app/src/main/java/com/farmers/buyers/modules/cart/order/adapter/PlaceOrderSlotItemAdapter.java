package com.farmers.buyers.modules.cart.order.adapter;

import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.modules.cart.order.view.PlaceOrderSlotItemDelegate;
import com.farmers.buyers.modules.cart.order.view.PlaceOrderSlotItemViewHolder;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 31-01-2021 at 01:03
 * mohammadsajjad679@gmail.com
 */

public class PlaceOrderSlotItemAdapter extends BaseAdapter {

    private PlaceOrderSlotItemViewHolder.SlotCheckedListener listener;


    public PlaceOrderSlotItemAdapter(PlaceOrderSlotItemViewHolder.SlotCheckedListener listener) {
        super();
        this.listener  = listener;
        initDelegate();
    }

    @Override
    public void initDelegate() {
        delegates.put(CardConstant.PLACE_ORDER_ITEM_ADAPTER, new PlaceOrderSlotItemDelegate(listener));
    }
}
