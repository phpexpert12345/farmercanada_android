package com.farmers.buyers.modules.followers.adapter;

import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.modules.followers.view.FollowersDelegate;
import com.farmers.buyers.modules.followers.view.FollowersViewHolder;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 31-01-2021 at 18:54
 * mohammadsajjad679@gmail.com
 */

public class FollowersAdapter extends BaseAdapter {
    private FollowersViewHolder.FollowerListener listener;


    public FollowersAdapter(FollowersViewHolder.FollowerListener listener) {
        super();
        this.listener = listener;
        initDelegate();
    }
    @Override
    public void initDelegate() {
        delegates.put(CardConstant.FOLLOWERS_ITEM_ADAPTER, new FollowersDelegate(listener));
    }
}
