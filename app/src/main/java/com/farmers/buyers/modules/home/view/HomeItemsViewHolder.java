package com.farmers.buyers.modules.home.view;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;

/**
 * created by Mohammad Sajjad
 * on 25-01-2021 at 21:53
 * mohammadsajjad679@gmail.com
 */

public class HomeItemsViewHolder extends BaseViewHolder {

    public HomeItemsViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.home_list_item_layout));
    }

    @Override
    public void bindView(RecyclerViewListItem items) {

    }
}
