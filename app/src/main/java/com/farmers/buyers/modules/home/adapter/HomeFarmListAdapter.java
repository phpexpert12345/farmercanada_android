package com.farmers.buyers.modules.home.adapter;

import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.modules.home.view.HomeItemsDelegate;
import com.farmers.buyers.modules.home.view.HomeItemsViewHolder;
import com.farmers.buyers.storage.CardConstant;

/**
 * Created by Mohammad sajjad on 22-02-2021.
 * mohammadsajjad679@gmail.com
 */
public class HomeFarmListAdapter extends BaseAdapter {
    private HomeItemsViewHolder.FarmItemClickListener farmItemClickListener;

    public HomeFarmListAdapter(HomeItemsViewHolder.FarmItemClickListener farmItemClickListener) {
        this.farmItemClickListener = farmItemClickListener;
        initDelegate();
    }

    @Override
    public void initDelegate() {
        delegates.put(CardConstant.HOME_FARM_LIST_ITEM_ADAPTER, new HomeItemsDelegate(farmItemClickListener));

    }
}
