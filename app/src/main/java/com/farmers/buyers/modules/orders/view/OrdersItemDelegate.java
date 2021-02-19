package com.farmers.buyers.modules.orders.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

/**
 * created by Mohammad Sajjad
 * on 02-02-2021 at 17:56
 * mohammadsajjad679@gmail.com
 */

public class OrdersItemDelegate extends BaseDelegate {
    OrdersItemViewHolder.OrdersItemClickListener ordersItemClickListener;

    public OrdersItemDelegate(OrdersItemViewHolder.OrdersItemClickListener ordersItemClickListener) {
        this.ordersItemClickListener = ordersItemClickListener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new OrdersItemViewHolder(parent, ordersItemClickListener);
    }
}
