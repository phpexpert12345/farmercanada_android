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

    public FarmDetailDelegate(HomeDeliveryTypeViewHolder.DeliveryTypeCheckedChangeListener deliveryTypeCheckedChangeListener, FarmDetailViewHolder.FarmDetailItemClickListener farmDetailItemClickListener) {
        this.deliveryTypeCheckedChangeListener = deliveryTypeCheckedChangeListener;
        this.farmDetailItemClickListener = farmDetailItemClickListener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new FarmDetailViewHolder(parent, deliveryTypeCheckedChangeListener,farmDetailItemClickListener);
    }
}
