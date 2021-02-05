package com.farmers.buyers.modules.home.view;

import android.content.Intent;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.farmDetail.FarmDetailActivity;
import com.farmers.buyers.modules.home.models.HomeHeaderItem;
import com.farmers.buyers.modules.home.models.HomeListItem;

/**
 * created by Mohammad Sajjad
 * on 25-01-2021 at 21:53
 * mohammadsajjad679@gmail.com
 */

public class HomeItemsViewHolder extends BaseViewHolder {
    private CardView cardView;
    private ImageView saveImage, savedImage;

    public HomeItemsViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.home_list_item_layout));
        cardView = itemView.findViewById(R.id.home_item_card);
        saveImage = itemView.findViewById(R.id.home_list_save_image);
        savedImage = itemView.findViewById(R.id.home_list_saved_image);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemView.getContext().startActivity( new Intent(itemView.getContext(), FarmDetailActivity.class));
            }
        });

    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        HomeListItem item = (HomeListItem) items;
        if (!item.getSaved()) {
            saveImage.setVisibility(View.VISIBLE);
            savedImage.setVisibility(View.GONE);
        }
        else {
            saveImage.setVisibility(View.GONE);
            savedImage.setVisibility(View.VISIBLE);
        }
    }
}
