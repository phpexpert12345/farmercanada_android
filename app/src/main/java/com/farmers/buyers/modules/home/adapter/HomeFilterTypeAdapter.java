package com.farmers.buyers.modules.home.adapter;

import com.farmers.buyers.common.view.MultipleTextItemDelegate;
import com.farmers.buyers.common.view.MultipleTextItemViewHolder;
import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.modules.home.view.HomeFilterItemDelegate;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 27-01-2021 at 11:16
 * mohammadsajjad679@gmail.com
 */

public class HomeFilterTypeAdapter extends BaseAdapter {
private MultipleTextItemViewHolder.FilterItemClickListener listener;
    public HomeFilterTypeAdapter(MultipleTextItemViewHolder.FilterItemClickListener listener) {
        super();
        this.listener = listener;
        this.initDelegate();
    }
    @Override
    public void initDelegate() {
        delegates.put(CardConstant.MULTIPLE_ITEM_ADAPTER, new MultipleTextItemDelegate(listener));
    }
}
