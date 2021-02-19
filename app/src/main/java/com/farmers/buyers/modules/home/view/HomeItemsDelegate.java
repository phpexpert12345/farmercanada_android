package com.farmers.buyers.modules.home.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

/**
 * created by Mohammad Sajjad
 * on 25-01-2021 at 22:48
 * mohammadsajjad679@gmail.com
 */

public class HomeItemsDelegate extends BaseDelegate {
    private HomeItemsViewHolder.FarmItemClickListener farmItemClickListener;

    public HomeItemsDelegate(HomeItemsViewHolder.FarmItemClickListener farmItemClickListener) {
        this.farmItemClickListener = farmItemClickListener;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new HomeItemsViewHolder(parent, farmItemClickListener);
    }
}
