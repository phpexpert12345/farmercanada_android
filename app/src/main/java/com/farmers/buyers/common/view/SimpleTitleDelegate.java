package com.farmers.buyers.common.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

/**
 * created by Mohammad Sajjad
 * on 26-01-2021 at 12:45
 * mohammadsajjad679@gmail.com
 */

public class SimpleTitleDelegate extends BaseDelegate {
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new SimpleTitleViewHolder(parent);
    }
}
