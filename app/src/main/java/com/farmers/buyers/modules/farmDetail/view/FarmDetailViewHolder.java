package com.farmers.buyers.modules.farmDetail.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.farmDetail.model.FarmDetailItems;

/**
 * created by Mohammad Sajjad
 * on 28-01-2021 at 11:58
 * mohammadsajjad679@gmail.com
 */

public class FarmDetailViewHolder extends BaseViewHolder {
    TextView farm_detail_item_farm_name_tv, tv_distance, tv_opening_time, tv_rating, tv_open_status, tv_hosted_by, tv_hosted_by_name;

    public FarmDetailViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.farm_detail_item_layout));

        farm_detail_item_farm_name_tv = itemView.findViewById(R.id.farm_detail_item_farm_name_tv);
        tv_distance = itemView.findViewById(R.id.tv_distance);
        tv_opening_time = itemView.findViewById(R.id.tv_opening_time);
        tv_rating = itemView.findViewById(R.id.tv_rating);
        tv_open_status = itemView.findViewById(R.id.tv_open_status);
        tv_hosted_by = itemView.findViewById(R.id.tv_hosted_by);
        tv_hosted_by_name = itemView.findViewById(R.id.tv_hosted_by_name);
    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        FarmDetailItems item = (FarmDetailItems) items;

        farm_detail_item_farm_name_tv.setText(item.getFarmName());
        tv_distance.setText(item.getFarm_delivery_radius_text());
        tv_opening_time.setText(item.getFarm_estimate_delivery_time());
        tv_rating.setText(item.getRating());
        //  tv_open_status.setText(item.getFarm_followed_status());
        tv_hosted_by.setText(item.getFarm_opening_hours());
        tv_hosted_by_name.setText("Hosted By " + item.getHostedBy());
    }
}
