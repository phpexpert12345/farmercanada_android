package com.farmers.buyers.modules.followers.view;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;

/**
 * created by Mohammad Sajjad
 * on 31-01-2021 at 18:54
 * mohammadsajjad679@gmail.com
 */

public class FollowersViewHolder extends BaseViewHolder {

    public FollowersViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.followers_item_layout));
    }

    @Override
    public void bindView(RecyclerViewListItem items) {

    }
}
