package com.farmers.buyers.modules.inbox.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 01-02-2021 at 16:57
 * mohammadsajjad679@gmail.com
 */

public class MessageListItems implements RecyclerViewListItem {
    @Override
    public int getViewType() {
        return CardConstant.MESSAGE_LIST_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
