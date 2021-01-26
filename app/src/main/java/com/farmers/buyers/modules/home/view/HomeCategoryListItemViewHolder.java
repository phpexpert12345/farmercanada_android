package com.farmers.buyers.modules.home.view;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;

/**
 * created by Mohammad Sajjad
 * on 26-01-2021 at 01:54
 * mohammadsajjad679@gmail.com
 */

public class HomeCategoryListItemViewHolder extends BaseViewHolder {
    public HomeCategoryListItemViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.home_categories_view_holder_layout));
    }

    @Override
    public void bindView(RecyclerViewListItem items) {

    }
}
