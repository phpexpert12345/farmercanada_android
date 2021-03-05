package com.farmers.buyers.modules.home.search;

import com.farmers.buyers.common.view.SingleItemDelegate;
import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.modules.farmDetail.view.FarmDetailVegetablesDelegate;
import com.farmers.buyers.modules.farmDetail.view.FarmDetailsVegetableItemsViewHolder;
import com.farmers.buyers.storage.CardConstant;

/**
 * Created by Mohammad sajjad on 01-03-2021.
 * mohammadsajjad679@gmail.com
 */
public class HomeSearchAdapter extends BaseAdapter {

    private FarmDetailsVegetableItemsViewHolder.FarmDetailVegetableListener farmDetailVegetableListener;

    public HomeSearchAdapter(FarmDetailsVegetableItemsViewHolder.FarmDetailVegetableListener farmDetailVegetableListener) {
        super();
        this.farmDetailVegetableListener = farmDetailVegetableListener;
        initDelegate();
    }

    @Override
    public void initDelegate() {
        delegates.put(CardConstant.FARM_DETAIL_VEGETABLE_ADAPTER, new FarmDetailVegetablesDelegate(farmDetailVegetableListener));
        delegates.put(CardConstant.SINGLE_TEXT_ITEM_ADAPTER, new SingleItemDelegate());

    }
}
