package com.farmers.buyers.modules.orders.track.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.orders.track.model.TrackOrderItemList;

/**
 * created by Mohammad Sajjad
 * on 03-02-2021 at 13:32
 * mohammadsajjad679@gmail.com
 */

public class TrackOrderItemViewHolder extends BaseViewHolder {

    ImageView track_order_item_image;
    TextView tv_product_name, tv_product_quantity, tv_product_amount, tv_product_price, tv_price_in_multiple;

    public TrackOrderItemViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.track_order_item_layout));

        track_order_item_image = itemView.findViewById(R.id.track_order_item_image);
        tv_product_name = itemView.findViewById(R.id.tv_product_name);
        tv_product_quantity = itemView.findViewById(R.id.tv_product_quantity);
        tv_product_amount = itemView.findViewById(R.id.tv_product_amount);
        tv_product_price = itemView.findViewById(R.id.tv_product_price);
        tv_price_in_multiple = itemView.findViewById(R.id.tv_price_in_multiple);
    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        TrackOrderItemList item = (TrackOrderItemList) items;

        Glide.with(itemView.getContext())
                .load(item.getProduct_images())
                .placeholder(R.drawable.farm_image)
                .into(track_order_item_image);

        tv_product_name.setText(item.getProduct_name());
        tv_product_quantity.setText(item.getItem_unit());
        tv_product_amount.setText(item.getItem_price());
        tv_product_price.setText(item.getItem_price());
        tv_price_in_multiple.setText(item.getItem_price() + "*" + item.getItem_quantity());
    }
}
