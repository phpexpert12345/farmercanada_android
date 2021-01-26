package com.farmers.buyers.modules.home;


import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.modules.home.view.HomeCategoriesItemDelegate;
import com.farmers.buyers.modules.home.view.HomeDelegate;
import com.farmers.buyers.modules.home.view.HomeItemsDelegate;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 25-01-2021 at 20:27
 * mohammadsajjad679@gmail.com
 */

public class HomeAdapter extends BaseAdapter {

    @Override
    public void initDelegate() {
        delegates.put(CardConstant.HOME_HEADER_ADAPTER, new HomeDelegate());
        delegates.put(CardConstant.HOME_FARM_LIST_ITEM_ADAPTER, new HomeItemsDelegate());
        delegates.put(CardConstant.HOME_CATEGORY_ITEM_ADAPTER, new HomeCategoriesItemDelegate());
    }

    public HomeAdapter() {
        super();
        this.initDelegate();
    }


}
