package com.farmers.buyers.modules.farmDetail.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.modules.home.view.HomeDeliveryTypeViewHolder;

/**
 * created by Mohammad Sajjad
 * on 28-01-2021 at 11:58
 * mohammadsajjad679@gmail.com
 */

public class FarmDetailDelegate extends BaseDelegate {

    private HomeDeliveryTypeViewHolder.DeliveryTypeCheckedChangeListener deliveryTypeCheckedChangeListener;
    private FarmDetailViewHolder.FarmDetailItemClickListener farmDetailItemClickListener;
    private String delivery_available,pickup_available;

    public FarmDetailDelegate(HomeDeliveryTypeViewHolder.DeliveryTypeCheckedChangeListener deliveryTypeCheckedChangeListener, FarmDetailViewHolder.FarmDetailItemClickListener farmDetailItemClickListener,String pickup_available,String delivery_available) {
        this.deliveryTypeCheckedChangeListener = deliveryTypeCheckedChangeListener;
        this.farmDetailItemClickListener = farmDetailItemClickListener;
        this.pickup_available=pickup_available;
        this.delivery_available=delivery_available;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new FarmDetailViewHolder(parent, deliveryTypeCheckedChangeListener,farmDetailItemClickListener,pickup_available,delivery_available);
    }
}
