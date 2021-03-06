package com.farmers.buyers.modules.farmDetail.view;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;

/**
 * created by Mohammad Sajjad
 * on 28-01-2021 at 11:58
 * mohammadsajjad679@gmail.com
 */

public class FarmDetailViewHolder extends BaseViewHolder {

    public FarmDetailViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.farm_detail_item_layout));
    }

    @Override
    public void bindView(RecyclerViewListItem items) {

    }
}
