package com.farmers.buyers.modules.farmDetail.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.modules.home.view.HomeCategoryListItemViewHolder;

/**
 * created by Mohammad Sajjad
 * on 28-01-2021 at 11:59
 * mohammadsajjad679@gmail.com
 */

public class FarmDetailVegetablesDelegate extends BaseDelegate {

    private FarmDetailsVegetableItemsViewHolder.FarmDetailVegetableListener farmDetailVegetableListener;

    public FarmDetailVegetablesDelegate(FarmDetailsVegetableItemsViewHolder.FarmDetailVegetableListener farmDetailVegetableListener) {
        this.farmDetailVegetableListener = farmDetailVegetableListener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new FarmDetailVegetableViewHolder(parent, farmDetailVegetableListener);
    }
}
