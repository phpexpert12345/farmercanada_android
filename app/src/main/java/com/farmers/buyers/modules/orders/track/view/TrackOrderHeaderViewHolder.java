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
import com.farmers.buyers.modules.orders.track.model.TrackOrderHeaderItems;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * created by Mohammad Sajjad
 * on 03-02-2021 at 13:32
 * mohammadsajjad679@gmail.com
 */

public class TrackOrderHeaderViewHolder extends BaseViewHolder {
    CircleImageView sub_order_item_farm_image;
    ImageView step1Image, step2Image, step3Image, step4Image;
    View step1View, step2View, step3View;
    TextView tv_product_date_time, tv_product_name, tv_order_number, tv_estimate_delivery_date_time, tv_edit, placedTv, packedTv, onTheWayTv, deliveryTv;

    public TrackOrderHeaderViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.track_order_header_item_layout));
        step1Image = itemView.findViewById(R.id.image_step1);
        step2Image = itemView.findViewById(R.id.image_step2);
        step3Image = itemView.findViewById(R.id.image_step3);
        step4Image = itemView.findViewById(R.id.image_step4);
        step1View = itemView.findViewById(R.id.step_view_one);
        step2View = itemView.findViewById(R.id.step_view_two);
        step3View = itemView.findViewById(R.id.step_view_three);
        placedTv = itemView.findViewById(R.id.tv_placed);
        packedTv = itemView.findViewById(R.id.tv_packed);
        onTheWayTv = itemView.findViewById(R.id.tv_onTheWay);
        deliveryTv = itemView.findViewById(R.id.tv_delivery);
        sub_order_item_farm_image = itemView.findViewById(R.id.sub_order_item_farm_image);

        tv_product_date_time = itemView.findViewById(R.id.tv_product_date_time);
        tv_product_name = itemView.findViewById(R.id.tv_product_name);
        tv_order_number = itemView.findViewById(R.id.tv_order_number);
        tv_estimate_delivery_date_time = itemView.findViewById(R.id.tv_estimate_delivery_date_time);
        tv_edit = itemView.findViewById(R.id.tv_edit);

    }

    @Override
    public void bindView(RecyclerViewListItem items) {

        TrackOrderHeaderItems item = (TrackOrderHeaderItems) items;
        tv_product_date_time.setText(item.getDate());
        tv_product_name.setText(item.getName());
        tv_order_number.setText(item.getOrderId());
        tv_estimate_delivery_date_time.setText(item.getDeliveryTime());

        Glide.with(itemView.getContext())
                .load(item.getLogo())
                .placeholder(R.drawable.farm_image)
                .into(sub_order_item_farm_image);

        switch (item.getOrderState()) {
            case 1: {
                step1Image.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                step1View.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                placedTv.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                break;
            }
            case 2: {
                step1Image.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                step1View.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                step2Image.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                step2View.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                placedTv.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                packedTv.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                break;
            }
            case 3: {
                step1Image.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                step1View.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                step2Image.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                step2View.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                step3Image.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                step3View.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                placedTv.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                packedTv.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                onTheWayTv.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                break;
            }
            case 4: {
                step1Image.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                step1View.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                step2Image.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                step2View.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                step3Image.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                step3View.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                step4Image.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                placedTv.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                packedTv.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                onTheWayTv.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                deliveryTv.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                break;
            }
        }

    }
}
