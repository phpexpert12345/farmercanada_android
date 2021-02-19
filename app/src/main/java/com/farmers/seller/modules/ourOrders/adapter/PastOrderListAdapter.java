package com.farmers.seller.modules.ourOrders.adapter;

import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.storage.CardConstant;
import com.farmers.seller.modules.ourOrders.view.OurOrderListDelegate;
import com.farmers.seller.modules.ourOrders.view.OurOrderListViewHolder;
import com.farmers.seller.modules.ourOrders.view.PastOrderListDelegate;
import com.farmers.seller.modules.ourOrders.view.PastOrderListViewHolder;

public class PastOrderListAdapter extends BaseAdapter {

    PastOrderListViewHolder.PastOrderItemClickListener pastOrderItemClickListener;

    public PastOrderListAdapter(PastOrderListViewHolder.PastOrderItemClickListener pastOrderItemClickListener) {
        super();
        this.pastOrderItemClickListener = pastOrderItemClickListener;
        this.initDelegate();
    }

    @Override
    public void initDelegate() {
        delegates.put(CardConstant.PAST_ORDER_LIST_ADAPTER, new PastOrderListDelegate(pastOrderItemClickListener));
    }
}
