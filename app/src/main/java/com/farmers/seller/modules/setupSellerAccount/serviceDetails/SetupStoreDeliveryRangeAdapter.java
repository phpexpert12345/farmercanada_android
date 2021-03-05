package com.farmers.seller.modules.setupSellerAccount.serviceDetails;

import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.storage.CardConstant;
import com.farmers.seller.modules.setupSellerAccount.serviceDetails.view.StoreDeliveryRangeDelegate;
import com.farmers.seller.modules.setupSellerAccount.serviceDetails.view.StoreDeliveryRangeViewHolder;

/**
 * Created by Mohammad sajjad on 04-03-2021.
 * mohammadsajjad679@gmail.com
 */
public class SetupStoreDeliveryRangeAdapter extends BaseAdapter {
    private StoreDeliveryRangeViewHolder.RangeSelectedListener listener;

    public SetupStoreDeliveryRangeAdapter(StoreDeliveryRangeViewHolder.RangeSelectedListener listener) {
        this.listener = listener;
        initDelegate();
    }

    @Override
    public void initDelegate() {
        delegates.put(CardConstant.STORE_SETUP_DELIVERY_RANGE_ADAPTER, new StoreDeliveryRangeDelegate(listener));
    }
}
