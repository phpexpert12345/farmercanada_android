package com.farmers.buyers.modules.farmDetail.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

/**
 * created by Mohammad Sajjad
 * on 28-01-2021 at 12:12
 * mohammadsajjad679@gmail.com
 */

public class FarmDetailsVegetableItemsDelegate extends BaseDelegate {

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new FarmDetailsVegetableItemsViewHolder(parent);
    }
}
