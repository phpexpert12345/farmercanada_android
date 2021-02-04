package com.farmers.buyers.modules.support.adapter;

import androidx.cardview.widget.CardView;

import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.modules.support.view.SupportItemDelegate;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 04-02-2021 at 11:29
 * mohammadsajjad679@gmail.com
 */

public class SupportAdapter extends BaseAdapter {

    public SupportAdapter() {
        super();
        this.initDelegate();

    }

    @Override
    public void initDelegate() {
        delegates.put(CardConstant.SUPPORT_ITEMS_ADAPTER, new SupportItemDelegate());
    }
}
