package com.farmers.buyers.modules.farmDetail.adapter;

import com.farmers.buyers.common.view.SingleItemDelegate;
import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.modules.farmDetail.view.FarmDetailDelegate;
import com.farmers.buyers.modules.farmDetail.view.FarmDetailHeaderDelegate;
import com.farmers.buyers.modules.farmDetail.view.FarmDetailHeaderViewHolder;
import com.farmers.buyers.modules.farmDetail.view.FarmDetailVegetablesDelegate;
import com.farmers.buyers.modules.farmDetail.view.FarmDetailsVegetableItemsViewHolder;
import com.farmers.buyers.modules.home.view.HomeDeliveryTypeViewHolder;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 28-01-2021 at 12:00
 * mohammadsajjad679@gmail.com
 */

public class FarmDetailsAdapter extends BaseAdapter {
    final FarmDetailHeaderViewHolder.FarmHeaderClickListener headerClickListener;
    private FarmDetailsVegetableItemsViewHolder.FarmDetailVegetableListener farmDetailVegetableListener;
    private HomeDeliveryTypeViewHolder.DeliveryTypeCheckedChangeListener deliveryTypeCheckedChangeListener;

    @Override
    public void initDelegate() {
        delegates.put(CardConstant.FARM_DETAIL_HEADER_ADAPTER, new FarmDetailHeaderDelegate(headerClickListener));
        delegates.put(CardConstant.FARM_DETAIL_ADAPTER, new FarmDetailDelegate(deliveryTypeCheckedChangeListener));
        delegates.put(CardConstant.SINGLE_TEXT_ITEM_ADAPTER, new SingleItemDelegate());
        delegates.put(CardConstant.FARM_DETAIL_VEGETABLE_ADAPTER, new FarmDetailVegetablesDelegate(farmDetailVegetableListener));
    }

    public FarmDetailsAdapter(FarmDetailHeaderViewHolder.FarmHeaderClickListener headerClickListener,
                              FarmDetailsVegetableItemsViewHolder.FarmDetailVegetableListener
                                      farmDetailVegetableListener, HomeDeliveryTypeViewHolder.DeliveryTypeCheckedChangeListener deliveryTypeCheckedChangeListener) {
        super();
        this.farmDetailVegetableListener = farmDetailVegetableListener;
        this.deliveryTypeCheckedChangeListener = deliveryTypeCheckedChangeListener;
        this.headerClickListener = headerClickListener;
        this.initDelegate();
    }
}
