package com.farmers.buyers.modules.home.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;

/**
 * created by Mohammad Sajjad
 * on 27-01-2021 at 16:09
 * mohammadsajjad679@gmail.com
 */

public class HomeFarmTypeViewHolder extends BaseViewHolder {
    private TextView localFarmTv, homeMadeTv;

    public HomeFarmTypeViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.home_farm_type_item_layout));
        localFarmTv = itemView.findViewById(R.id.home_farm_type_local_farm_tv);
        homeMadeTv = itemView.findViewById(R.id.home_farm_type_home_made_tv);
    }

    @Override
    public void bindView(RecyclerViewListItem items) {

        localFarmTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                localFarmTv.setBackground(itemView.getContext().getResources().getDrawable(R.mipmap.farm_type_bg));
                homeMadeTv.setBackground(itemView.getContext().getResources().getDrawable(R.drawable.light_gray_round_border_bg));
                localFarmTv.setElevation(2f);
                homeMadeTv.setElevation(0f);
                localFarmTv.setTextColor(itemView.getContext().getResources().getColor(R.color.secondaryTextColor));
                homeMadeTv.setTextColor(itemView.getContext().getResources().getColor(R.color.primaryTextColor));
            }
        });

        homeMadeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeMadeTv.setBackground(itemView.getContext().getResources().getDrawable(R.mipmap.farm_type_bg));
                localFarmTv.setBackground(itemView.getContext().getResources().getDrawable(R.drawable.light_gray_round_border_bg));
                homeMadeTv.setElevation(2f);
                localFarmTv.setElevation(0f);
                localFarmTv.setTextColor(itemView.getContext().getResources().getColor(R.color.primaryTextColor));
                homeMadeTv.setTextColor(itemView.getContext().getResources().getColor(R.color.secondaryTextColor));
            }
        });
    }
}
