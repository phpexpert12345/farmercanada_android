package com.farmers.buyers.common.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 31-01-2021 at 13:16
 * mohammadsajjad679@gmail.com
 */

public class SimpleRowListItem implements RecyclerViewListItem {
    private List<SimpleRowItem> item;

    public SimpleRowListItem(List<SimpleRowItem> item) {
        this.item = item;
    }

    public List<SimpleRowItem> getItem() {
        return item;
    }

    @Override
    public int getViewType() {
        return CardConstant.SIMPLE_ROW_LIST_ITEM;
    }

    @Override
    public Object getUnique() {
        return null;
    }
}
