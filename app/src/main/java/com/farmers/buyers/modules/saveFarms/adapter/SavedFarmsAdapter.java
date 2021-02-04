package com.farmers.buyers.modules.saveFarms.adapter;

import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.modules.home.view.HomeItemsDelegate;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 04-02-2021 at 14:19
 * mohammadsajjad679@gmail.com
 */

public class SavedFarmsAdapter extends BaseAdapter {

    public SavedFarmsAdapter() {
        this.initDelegate();
    }

    @Override
    public void initDelegate() {
        delegates.put(CardConstant.HOME_FARM_LIST_ITEM_ADAPTER, new HomeItemsDelegate());
    }
}
