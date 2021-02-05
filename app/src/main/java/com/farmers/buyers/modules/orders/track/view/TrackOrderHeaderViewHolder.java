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

        HorizontalStepView setpview5 = (HorizontalStepView) itemView.findViewById(R.id.step_view5);
        List<StepBean> stepsBeanList = new ArrayList<>();
        StepBean stepBean0 = new StepBean("Placed",1);
        StepBean stepBean1 = new StepBean("Packed",1);
        StepBean stepBean2 = new StepBean("On the way",1);
        StepBean stepBean3 = new StepBean("Delivered",-1);
        stepsBeanList.add(stepBean0);
        stepsBeanList.add(stepBean1);
        stepsBeanList.add(stepBean2);
        stepsBeanList.add(stepBean3);


        setpview5
                .setStepViewTexts(stepsBeanList)
                .setTextSize(12)//set textSize
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(itemView.getContext(), android.R.color.holo_green_dark))
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(itemView.getContext(), R.color.light_gray))
                .setStepViewComplectedTextColor(ContextCompat.getColor(itemView.getContext(), android.R.color.holo_green_dark))
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(itemView.getContext(), R.color.light_gray))
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_checked_green))
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_unchecked_gray));

    }

    @Override
    public void bindView(RecyclerViewListItem items) {

    }
}
