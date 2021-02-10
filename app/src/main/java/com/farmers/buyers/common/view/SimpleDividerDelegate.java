package com.farmers.buyers.common.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

/**
 * created by Mohammad Sajjad
 * on 10-02-2021 at 15:03
 * mohammadsajjad679@gmail.com
 */

public class SimpleDividerDelegate extends BaseDelegate {

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new SimpleDividerViewHolder(parent);
    }
}
