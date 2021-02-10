package com.farmers.seller.modules.broadcastMessage.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

public class BroadcastMessageListItem implements RecyclerViewListItem {
    @Override
    public int getViewType() {
        return CardConstant.BROAD_CAST_MESSAGE_LIST_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
