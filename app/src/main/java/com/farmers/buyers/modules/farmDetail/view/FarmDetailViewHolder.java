package com.farmers.buyers.modules.farmDetail.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;

import com.bumptech.glide.Glide;
import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.farmDetail.model.FarmDetailItems;
import com.farmers.buyers.modules.home.view.HomeDeliveryTypeViewHolder;
import com.farmers.buyers.storage.SharedPreferenceManager;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * created by Mohammad Sajjad
 * on 28-01-2021 at 11:58
 * mohammadsajjad679@gmail.com
 */

public class FarmDetailViewHolder extends BaseViewHolder {
    TextView farm_detail_item_farm_name_tv, tv_distance, tv_opening_time, tv_rating, tv_open_status, tv_hosted_by, tv_hosted_by_name;
    CircleImageView civ_farm_image;
    private SwitchCompat toggle;
    private TextView deliveryTv, pickUpTv;

    public FarmDetailViewHolder(@NonNull ViewGroup parent, HomeDeliveryTypeViewHolder.DeliveryTypeCheckedChangeListener
            deliveryTypeCheckedChangeListener) {
        super(Extensions.inflate(parent, R.layout.farm_detail_item_layout));

        farm_detail_item_farm_name_tv = itemView.findViewById(R.id.farm_detail_item_farm_name_tv);
        tv_distance = itemView.findViewById(R.id.tv_distance);
        tv_opening_time = itemView.findViewById(R.id.tv_opening_time);
        tv_rating = itemView.findViewById(R.id.tv_rating);
        tv_rating = itemView.findViewById(R.id.tv_rating);
        toggle = itemView.findViewById(R.id.toggleButton1);
        tv_open_status = itemView.findViewById(R.id.tv_open_status);
        tv_hosted_by = itemView.findViewById(R.id.tv_hosted_by);
        tv_hosted_by_name = itemView.findViewById(R.id.tv_hosted_by_name);
        deliveryTv = itemView.findViewById(R.id.app_toggle_delivery_tv);
        pickUpTv = itemView.findViewById(R.id.app_toggle_pickup_tv);
        deliveryTv.setTextColor(itemView.getContext().getResources().getColor(R.color.primary_button_color));
        civ_farm_image = itemView.findViewById(R.id.civ_farm_image);

        if (String.valueOf(SharedPreferenceManager.getInstance().getSharedPreferences("SERVICE_TYPE", "")).equals("0")) {
            toggle.setChecked(false);
        } else {
            toggle.setChecked(true);
        }

        toggle.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                pickUpTv.setTextColor(itemView.getContext().getResources().getColor(R.color.primary_button_color));
                deliveryTv.setTextColor(itemView.getContext().getResources().getColor(R.color.primaryTextColor));
                deliveryTypeCheckedChangeListener.onDeliveryTypeCheckedChangeListener(1);
            } else {
                deliveryTv.setTextColor(itemView.getContext().getResources().getColor(R.color.primary_button_color));
                pickUpTv.setTextColor(itemView.getContext().getResources().getColor(R.color.primaryTextColor));
                deliveryTypeCheckedChangeListener.onDeliveryTypeCheckedChangeListener(0);
            }
        });
    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        FarmDetailItems item = (FarmDetailItems) items;

        Glide.with(itemView.getContext())
                .load(item.getFarmImage())
                .centerCrop()
                .placeholder(R.drawable.ic_sign_up_logo)
                .into(civ_farm_image);

        farm_detail_item_farm_name_tv.setText(item.getFarmName());
        tv_distance.setText(item.getFarm_delivery_radius_text());
        tv_opening_time.setText(item.getFarm_estimate_delivery_time());
        tv_rating.setText(item.getRating());
        //  tv_open_status.setText(item.getFarm_followed_status());
        tv_hosted_by.setText(item.getFarm_opening_hours());
        tv_hosted_by_name.setText("Hosted By " + item.getHostedBy());
    }

    public interface DeliveryTypeCheckedChangeListener {
        void onDeliveryTypeCheckedChangeListener(int type);
    }
}
