package com.farmers.buyers.modules.farmDetail.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;

/**
 * created by Mohammad Sajjad
 * on 28-01-2021 at 15:33
 * mohammadsajjad679@gmail.com
 */

public class FarmDetailHeaderItemViewHolder extends BaseViewHolder {

    ImageView home_top_offers_item_image;

    public FarmDetailHeaderItemViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.farm_details_header_item_layout));

        home_top_offers_item_image=itemView.findViewById(R.id.home_top_offers_item_image);
    }

    @Override
    public void bindView(RecyclerViewListItem items) {

     //   Glide.with(itemView.getContext()).load(item.getFarmLogo()).into(circleImageView);



    }
}
