package com.farmers.buyers.modules.followers.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

/**
 * created by Mohammad Sajjad
 * on 31-01-2021 at 18:54
 * mohammadsajjad679@gmail.com
 */

public class FollowersDelegate extends BaseDelegate {

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new FollowersViewHolder(parent);
    }
}
