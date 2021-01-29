package com.farmers.buyers.common.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

/**
 * created by Mohammad Sajjad
 * on 27-01-2021 at 11:08
 * mohammadsajjad679@gmail.com
 */

public class MultipleTextItemDelegate extends BaseDelegate {

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new MultipleTextItemViewHolder(parent);
    }
}
