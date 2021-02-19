package com.farmers.buyers.modules.home.adapter;

import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.modules.home.view.HomeCategoryListItemDelegate;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 26-01-2021 at 02:10
 * mohammadsajjad679@gmail.com
 */

public class HomeCategoryAdapter extends BaseAdapter {

    @Override
    public void initDelegate() {
        delegates.put(CardConstant.HOME_CATEGORY_LIST_ITEM_ADAPTER, new HomeCategoryListItemDelegate());
    }

    public HomeCategoryAdapter() {
        super();
        this.initDelegate();
    }
}
