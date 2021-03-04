package com.farmers.seller.modules.setupSellerAccount.serviceDetails.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

/**
 * Created by Mohammad sajjad on 04-03-2021.
 * mohammadsajjad679@gmail.com
 */
public class StoreDeliveryRangeDelegate extends BaseDelegate {
    private StoreDeliveryRangeViewHolder.RangeSelectedListener listener;

    public StoreDeliveryRangeDelegate(StoreDeliveryRangeViewHolder.RangeSelectedListener listener) {
        this.listener = listener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new StoreDeliveryRangeViewHolder(parent, listener);
    }
}
