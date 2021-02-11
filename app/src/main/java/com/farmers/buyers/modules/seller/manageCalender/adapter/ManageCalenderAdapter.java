package com.farmers.buyers.modules.seller.manageCalender.adapter;

import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.modules.seller.manageCalender.view.CalenderItemDelegate;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 10-02-2021 at 22:22
 * mohammadsajjad679@gmail.com
 */

public class ManageCalenderAdapter extends BaseAdapter {

    public ManageCalenderAdapter() {
        initDelegate();
    }

    @Override
    public void initDelegate() {
        delegates.put(CardConstant.CALENDER_ITEM_ADAPTER, new CalenderItemDelegate());
    }
}
