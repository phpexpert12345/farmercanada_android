package com.farmers.seller.modules.ourOrders.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

public class OngoingOrderListItem implements RecyclerViewListItem {
    @Override
    public int getViewType() {
        return CardConstant.ONGOING_ORDER_LIST_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
