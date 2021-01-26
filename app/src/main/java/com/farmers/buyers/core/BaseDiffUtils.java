package com.farmers.buyers.core;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 25-01-2021 at 10:05
 * mohammadsajjad679@gmail.com
 */

public class BaseDiffUtils extends DiffUtil.Callback {
    private List<RecyclerViewListItem> oldItems;
    private List<RecyclerViewListItem> newItems;

    BaseDiffUtils(List<RecyclerViewListItem> oldItems, List<RecyclerViewListItem> newItems) {
        this.oldItems = oldItems;
        this.newItems = newItems;
    }

    @Override
    public int getOldListSize() {
        return oldItems.size();
    }

    @Override
    public int getNewListSize() {
        return newItems.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        RecyclerViewListItem oldItem =  oldItems.get(oldItemPosition);
        RecyclerViewListItem newItem =  newItems.get(newItemPosition);
        return oldItem.getViewType() == newItem.getViewType() && oldItem.getUnique() == newItem.getUnique();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        RecyclerViewListItem oldItem =  oldItems.get(oldItemPosition);
        RecyclerViewListItem newItem =  newItems.get(newItemPosition);
        return oldItem.getViewType() == newItem.getViewType() && oldItem.getUnique() == newItem.getUnique();
    }
}
