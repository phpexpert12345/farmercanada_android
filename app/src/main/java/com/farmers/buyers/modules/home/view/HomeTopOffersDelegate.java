package com.farmers.buyers.modules.home.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

/**
 * created by Mohammad Sajjad
 * on 26-01-2021 at 13:18
 * mohammadsajjad679@gmail.com
 */

public class HomeTopOffersDelegate extends BaseDelegate {

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new HomeTopOffersViewHolder(parent);
    }
}
