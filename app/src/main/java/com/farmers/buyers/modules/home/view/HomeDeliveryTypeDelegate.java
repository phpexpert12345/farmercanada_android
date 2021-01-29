package com.farmers.buyers.modules.home.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

/**
 * created by Mohammad Sajjad
 * on 27-01-2021 at 12:54
 * mohammadsajjad679@gmail.com
 */

public class HomeDeliveryTypeDelegate extends BaseDelegate {

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new HomeDeliveryTypeViewHolder(parent);
    }
}
