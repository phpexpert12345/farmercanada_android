package com.farmers.buyers.modules.orders.adapter;


import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.modules.orders.view.SubOrderItemDelegate;
import com.farmers.buyers.modules.orders.view.SubOrderItemViewHolder;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 03-02-2021 at 01:41
 * mohammadsajjad679@gmail.com
 */

public class SubOrderItemAdapter extends BaseAdapter {

    SubOrderItemViewHolder.SubOrderItemClickListener subOrderItemClickListener;

    public SubOrderItemAdapter(SubOrderItemViewHolder.SubOrderItemClickListener subOrderItemClickListener) {
        super();
        this.subOrderItemClickListener = subOrderItemClickListener;
        this.initDelegate();

    }

    @Override
    public void initDelegate() {
     delegates.put(CardConstant.SUB_ORDER_ITEM_ADAPTER, new SubOrderItemDelegate(subOrderItemClickListener));
    }
}
