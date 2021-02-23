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
    private HomeCategoryListItemViewHolder.CategoryItemClickListener listener;

    public HomeCategoryListItemDelegate(HomeCategoryListItemViewHolder.CategoryItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new HomeCategoryListItemViewHolder(parent, listener);
    }
}
