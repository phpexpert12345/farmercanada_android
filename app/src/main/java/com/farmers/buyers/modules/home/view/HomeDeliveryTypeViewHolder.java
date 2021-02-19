package com.farmers.buyers.modules.home.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.common.widget.FarmersToggle;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;

/**
 * created by Mohammad Sajjad
 * on 27-01-2021 at 12:55
 * mohammadsajjad679@gmail.com
 */

public class HomeDeliveryTypeViewHolder extends BaseViewHolder {
    private SwitchCompat toggle;
    private TextView deliveryTv, pickUpTv;


    public HomeDeliveryTypeViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.app_toggle_layout));
        toggle = itemView.findViewById(R.id.toggleButton1);
        deliveryTv = itemView.findViewById(R.id.app_toggle_delivery_tv);
        pickUpTv = itemView.findViewById(R.id.app_toggle_pickup_tv);
        deliveryTv.setTextColor(itemView.getContext().getResources().getColor(R.color.primary_button_color));
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    pickUpTv.setTextColor(itemView.getContext().getResources().getColor(R.color.primary_button_color));
                    deliveryTv.setTextColor(itemView.getContext().getResources().getColor(R.color.primaryTextColor));
                }
                else {
                    deliveryTv.setTextColor(itemView.getContext().getResources().getColor(R.color.primary_button_color));
                    pickUpTv.setTextColor(itemView.getContext().getResources().getColor(R.color.primaryTextColor));
                }
            }
        });
    }

    @Override
    public void bindView(RecyclerViewListItem items) {

    }
}
