package com.farmers.buyers.modules.inbox.adapter;

import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.modules.inbox.view.MessageListDelegate;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 01-02-2021 at 17:02
 * mohammadsajjad679@gmail.com
 */

public class MessageListAdapter extends BaseAdapter {

    public MessageListAdapter() {
        super();
        this.initDelegate();
    }

    @Override
    public void initDelegate() {
        delegates.put(CardConstant.MESSAGE_LIST_ADAPTER, new MessageListDelegate());
    }
}
