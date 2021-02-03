package com.farmers.buyers.modules.support.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.modules.orders.view.OrdersItemViewHolder;

public class SupportItemDelegate extends BaseDelegate {

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new SupportItemViewHolder(parent);
    }
}
