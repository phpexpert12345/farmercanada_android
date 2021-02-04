package com.farmers.buyers.modules.support.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

/**
 * created by Mohammad Sajjad
 * on 04-02-2021 at 11:30
 * mohammadsajjad679@gmail.com
 */

public class SupportItemDelegate extends BaseDelegate {

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new SupportItemViewHolder(parent);
    }
}
