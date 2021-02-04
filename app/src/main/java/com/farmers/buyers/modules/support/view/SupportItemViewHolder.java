package com.farmers.buyers.modules.support.view;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;

/**
 * created by Mohammad Sajjad
 * on 04-02-2021 at 11:30
 * mohammadsajjad679@gmail.com
 */

public class SupportItemViewHolder extends BaseViewHolder {

    public SupportItemViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.support_item_layout));
    }

    @Override
    public void bindView(RecyclerViewListItem items) {

    }
}
