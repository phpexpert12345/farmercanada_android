package com.farmers.buyers.modules.farmDetail.adapter;

import com.farmers.buyers.common.view.SimpleTitleDelegate;
import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.modules.farmDetail.view.FarmDetailDelegate;
import com.farmers.buyers.modules.farmDetail.view.FarmDetailHeaderDelegate;
import com.farmers.buyers.modules.farmDetail.view.FarmDetailHeaderViewHolder;
import com.farmers.buyers.modules.farmDetail.view.FarmDetailVegetablesDelegate;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 28-01-2021 at 12:00
 * mohammadsajjad679@gmail.com
 */

public class FarmDetailsAdapter extends BaseAdapter {
    final FarmDetailHeaderViewHolder.FarmHeaderClickListener headerClickListener;

    @Override
    public void initDelegate() {
        delegates.put(CardConstant.FARM_DETAIL_HEADER_ADAPTER, new FarmDetailHeaderDelegate(headerClickListener));
        delegates.put(CardConstant.FARM_DETAIL_ADAPTER, new FarmDetailDelegate());
        delegates.put(CardConstant.SIMPLE_TITLE_ITEM_ADAPTER, new SimpleTitleDelegate());
        delegates.put(CardConstant.FARM_DETAIL_VEGETABLE_ADAPTER, new FarmDetailVegetablesDelegate());
    }


    public FarmDetailsAdapter(FarmDetailHeaderViewHolder.FarmHeaderClickListener headerClickListener) {
        super();
        this.headerClickListener = headerClickListener;
        this.initDelegate();
    }

}
