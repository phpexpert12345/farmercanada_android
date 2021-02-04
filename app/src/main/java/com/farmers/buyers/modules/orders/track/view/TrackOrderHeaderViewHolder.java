package com.farmers.buyers.modules.orders.track.view;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.kofigyan.stateprogressbar.StateProgressBar;

import java.util.ArrayList;


/**
 * created by Mohammad Sajjad
 * on 03-02-2021 at 13:32
 * mohammadsajjad679@gmail.com
 */

public class TrackOrderHeaderViewHolder extends BaseViewHolder {

    StateProgressBar stateProgressBar;

    public TrackOrderHeaderViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.track_order_header_item_layout));

        stateProgressBar = itemView.findViewById(R.id.track_order_state_progress);
        String [] states = {"Placed", "Packed", "On the way", "Delivered"};
        stateProgressBar.setStateDescriptionData(states);
        stateProgressBar.setStateDescriptionTypeface("fonts/sf_ui_regular.otf");

    }

    @Override
    public void bindView(RecyclerViewListItem items) {

    }
}
