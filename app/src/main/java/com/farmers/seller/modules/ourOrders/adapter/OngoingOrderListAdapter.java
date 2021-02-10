package com.farmers.seller.modules.ourOrders.adapter;

import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.storage.CardConstant;
import com.farmers.seller.modules.ourOrders.view.OngoingOrderListDelegate;
import com.farmers.seller.modules.ourOrders.view.OngoingOrderListViewHolder;
import com.farmers.seller.modules.ourOrders.view.OurOrderListDelegate;
import com.farmers.seller.modules.ourOrders.view.OurOrderListViewHolder;

public class OngoingOrderListAdapter extends BaseAdapter {

    OngoingOrderListViewHolder.OngoingOrderItemClickListener ongoingOrderItemClickListener;

    public OngoingOrderListAdapter(OngoingOrderListViewHolder.OngoingOrderItemClickListener ongoingOrderItemClickListener) {
        super();
        this.ongoingOrderItemClickListener = ongoingOrderItemClickListener;
        this.initDelegate();
    }

    @Override
    public void initDelegate() {
        delegates.put(CardConstant.ONGOING_ORDER_LIST_ADAPTER, new OngoingOrderListDelegate(ongoingOrderItemClickListener));
    }
}
