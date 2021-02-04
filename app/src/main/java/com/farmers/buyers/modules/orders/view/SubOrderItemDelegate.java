package com.farmers.buyers.modules.orders.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

/**
 * created by Mohammad Sajjad
 * on 03-02-2021 at 01:40
 * mohammadsajjad679@gmail.com
 */

public class SubOrderItemDelegate extends BaseDelegate {
    SubOrderItemViewHolder.SubOrderItemClickListener subOrderItemClickListener;

    public SubOrderItemDelegate(SubOrderItemViewHolder.SubOrderItemClickListener subOrderItemClickListener) {
        this.subOrderItemClickListener = subOrderItemClickListener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new SubOrderItemViewHolder(parent, subOrderItemClickListener);
    }
}
