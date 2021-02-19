package com.farmers.buyers.core;

import android.view.ViewGroup;

/**
 * created by Mohammad Sajjad
 * on 25-01-2021 at 10:43
 * mohammadsajjad679@gmail.com
 */

public class BaseDelegate implements DelegateInterface {
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, RecyclerViewListItem items) {
        holder.bindView(items);
    }
}
