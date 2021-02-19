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
 * on 27-01-2021 at 00:17
 * mohammadsajjad679@gmail.com
 */

public class HomeTopOffersItemViewHolder extends BaseViewHolder {

    public HomeTopOffersItemViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.home_top_offers_item_layout));
    }

    @Override
    public void bindView(RecyclerViewListItem items) {

    }
}
