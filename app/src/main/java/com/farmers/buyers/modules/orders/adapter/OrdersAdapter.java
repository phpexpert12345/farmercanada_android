package com.farmers.buyers.modules.orders.adapter;

import com.farmers.buyers.common.view.SimpleTitleDelegate;
import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.modules.orders.view.OrdersItemDelegate;
import com.farmers.buyers.modules.orders.view.OrdersItemViewHolder;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 02-02-2021 at 17:58
 * mohammadsajjad679@gmail.com
 */

public class OrdersAdapter extends BaseAdapter {

    OrdersItemViewHolder.OrdersItemClickListener ordersItemClickListener;

    public OrdersAdapter(OrdersItemViewHolder.OrdersItemClickListener ordersItemClickListener) {
        super();
        this.ordersItemClickListener = ordersItemClickListener;
        this.initDelegate();
    }

    @Override
    public void initDelegate() {
        delegates.put(CardConstant.SIMPLE_TITLE_ITEM_ADAPTER, new SimpleTitleDelegate());
        delegates.put(CardConstant.ORDER_ITEMS_ADAPTER, new OrdersItemDelegate(ordersItemClickListener));
    }
}
