package com.farmers.buyers.modules.chat.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 01-02-2021 at 18:57
 * mohammadsajjad679@gmail.com
 */

public class ChatListItem implements RecyclerViewListItem {
    String receiveMsg;
    String sendMsg;
    String receiveTime;
    String sendTime;


    public ChatListItem(String receiveMsg, String sendMsg, String receiveTime, String sendTime) {
        this.receiveMsg = receiveMsg;
        this.sendMsg = sendMsg;
        this.receiveTime = receiveTime;
        this.sendTime = sendTime;
    }

    public String getReceiveMsg() {
        return receiveMsg;
    }

    public String getSendMsg() {
        return sendMsg;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public String getSendTime() {
        return sendTime;
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
