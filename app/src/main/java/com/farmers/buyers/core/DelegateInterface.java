package com.farmers.buyers.core;

import android.view.ViewGroup;

/**
 * created by Mohammad Sajjad
 * on 25-01-2021 at 10:41
 * mohammadsajjad679@gmail.com
 */

public interface DelegateInterface {
    BaseViewHolder onCreateViewHolder(ViewGroup parent);
    public void onBindViewHolder(BaseViewHolder holder, RecyclerViewListItem items);
}
