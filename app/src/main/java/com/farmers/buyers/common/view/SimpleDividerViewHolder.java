package com.farmers.buyers.common.view;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;

/**
 * created by Mohammad Sajjad
 * on 10-02-2021 at 15:04
 * mohammadsajjad679@gmail.com
 */

public class SimpleDividerViewHolder extends BaseViewHolder {

    public SimpleDividerViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.simple_space_item_layout));
    }

    @Override
    public void bindView(RecyclerViewListItem items) {

    }
}
