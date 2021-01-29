package com.farmers.buyers.modules.home.view;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;

/**
 * created by Mohammad Sajjad
 * on 25-01-2021 at 20:35
 * mohammadsajjad679@gmail.com
 */

public class HomeSearchItemViewHolder extends BaseViewHolder {

    public HomeSearchItemViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.home_search_item_view_holder_layout));
    }

    @Override
    public void bindView(RecyclerViewListItem items) {

    }
}
