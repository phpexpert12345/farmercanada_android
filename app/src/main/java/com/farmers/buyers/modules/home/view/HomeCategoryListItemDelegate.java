package com.farmers.buyers.modules.home.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

/**
 * created by Mohammad Sajjad
 * on 26-01-2021 at 01:53
 * mohammadsajjad679@gmail.com
 */

public class HomeCategoryListItemDelegate extends BaseDelegate {
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new HomeCategoryListItemViewHolder(parent);
    }
}
