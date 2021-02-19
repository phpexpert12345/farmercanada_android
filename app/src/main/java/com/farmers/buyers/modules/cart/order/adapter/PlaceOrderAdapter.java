package com.farmers.buyers.modules.cart.order.adapter;

import com.farmers.buyers.common.view.SimpleTitleDelegate;
import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.modules.cart.order.view.PlaceOrderSlotDelegate;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 31-01-2021 at 00:26
 * mohammadsajjad679@gmail.com
 */

public class PlaceOrderAdapter extends BaseAdapter {


    public PlaceOrderAdapter() {
        super();
        initDelegate();
    }
    @Override
    public void initDelegate() {
        delegates.put(CardConstant.SIMPLE_TITLE_ITEM_ADAPTER, new SimpleTitleDelegate());
        delegates.put(CardConstant.PLACE_ORDER_ADAPTER, new PlaceOrderSlotDelegate());

    }
}
