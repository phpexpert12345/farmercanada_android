package com.farmers.buyers.modules.chat.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 01-02-2021 at 18:57
 * mohammadsajjad679@gmail.com
 */

public class ChatListItem implements RecyclerViewListItem {
    String myMessage;
    String inComingMessage;

    public ChatListItem(String myMessage, String inComingMessage) {
        this.myMessage = myMessage;
        this.inComingMessage = inComingMessage;
    }

    public String getMyMessage() {
        return myMessage;
    }

    public String getInComingMessage() {
        return inComingMessage;
    }

    @Override
    public int getViewType() {
        return CardConstant.CHAT_ITEM_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
