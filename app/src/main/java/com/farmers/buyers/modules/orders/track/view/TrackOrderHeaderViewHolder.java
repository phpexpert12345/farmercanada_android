package com.farmers.buyers.modules.orders.track.view;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.baoyachi.stepview.HorizontalStepView;
import com.baoyachi.stepview.bean.StepBean;
import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;

import java.util.ArrayList;
import java.util.List;


/**
 * created by Mohammad Sajjad
 * on 03-02-2021 at 13:32
 * mohammadsajjad679@gmail.com
 */

public class TrackOrderHeaderViewHolder extends BaseViewHolder {


    public TrackOrderHeaderViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.track_order_header_item_layout));

    }

    @Override
    public void bindView(RecyclerViewListItem items) {

    }
}
