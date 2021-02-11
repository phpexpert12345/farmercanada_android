package com.farmers.buyers.common.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

/**
 * created by Mohammad Sajjad
 * on 11-02-2021 at 11:02
 * mohammadsajjad679@gmail.com
 */

public class SingleItemDelegate extends BaseDelegate {

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new SingleItemViewHolder(parent);
    }
}
