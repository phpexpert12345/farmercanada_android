package com.farmers.buyers.modules.home.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

/**
 * created by Mohammad Sajjad
 * on 27-01-2021 at 13:20
 * mohammadsajjad679@gmail.com
 */

public class HomeHeaderDelegate extends BaseDelegate {
    private HomeHeaderViewHolder.HeaderItemClickListener headerListener;

    public HomeHeaderDelegate(HomeHeaderViewHolder.HeaderItemClickListener headerListener) {
        this.headerListener = headerListener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new HomeHeaderViewHolder(parent, headerListener);
    }
}
