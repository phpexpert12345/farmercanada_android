package com.farmers.buyers.modules.inbox.adapter;


import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.modules.inbox.view.NotificationListDelegate;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 01-02-2021 at 17:04
 * mohammadsajjad679@gmail.com
 */

public class NotificationListAdapter extends BaseAdapter {

    public NotificationListAdapter() {
        super();
        this.initDelegate();
    }

    @Override
    public void initDelegate() {
        delegates.put(CardConstant.NOTIFICATION_LIST_ADAPTER, new NotificationListDelegate());
    }
}
