package com.farmers.buyers.modules.orders.track.view;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;

/**
 * created by Mohammad Sajjad
 * on 09-02-2021 at 12:39
 * mohammadsajjad679@gmail.com
 */

public class TrackOrderCountViewHolder extends BaseViewHolder {

    public TrackOrderCountViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.track_order_count_holder_layout));
    }

    @Override
    public void bindView(RecyclerViewListItem items) {

    }
}
