package com.farmers.buyers.modules.chat.adapter;

import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.modules.chat.view.ChatItemDelegate;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 01-02-2021 at 18:34
 * mohammadsajjad679@gmail.com
 */

public class ChatAdapter extends BaseAdapter {

    public ChatAdapter() {
        super();
        this.initDelegate();
    }

    @Override
    public void initDelegate() {
        delegates.put(CardConstant.CHAT_ITEM_ADAPTER, new ChatItemDelegate());
    }
}
