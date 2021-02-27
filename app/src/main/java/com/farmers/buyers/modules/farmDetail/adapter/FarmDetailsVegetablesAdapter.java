package com.farmers.buyers.modules.farmDetail.adapter;

import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.modules.farmDetail.view.FarmDetailsVegetableItemsDelegate;
import com.farmers.buyers.modules.farmDetail.view.FarmDetailsVegetableItemsViewHolder;
import com.farmers.buyers.modules.followers.view.FollowersViewHolder;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 28-01-2021 at 12:07
 * mohammadsajjad679@gmail.com
 */

public class FarmDetailsVegetablesAdapter extends BaseAdapter {

    private FarmDetailsVegetableItemsViewHolder.FarmDetailVegetableListener farmDetailVegetableListener;

    public FarmDetailsVegetablesAdapter(FarmDetailsVegetableItemsViewHolder.FarmDetailVegetableListener farmDetailVegetableListener) {
        super();
        this.farmDetailVegetableListener = farmDetailVegetableListener;
        this.initDelegate();
    }

    @Override
    public void initDelegate() {
        delegates.put(CardConstant.FARM_DETAIL_VEGETABLE_ITEM_ADAPTER, new FarmDetailsVegetableItemsDelegate(farmDetailVegetableListener));
    }
}
