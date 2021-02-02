package com.farmers.buyers.modules.inbox.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

/**
 * created by Mohammad Sajjad
 * on 01-02-2021 at 17:33
 * mohammadsajjad679@gmail.com
 */

public class NotificationListDelegate extends BaseDelegate {

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new NotificationListViewHolder(parent);
    }
}
