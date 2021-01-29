package com.farmers.buyers.modules.farmDetail.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

/**
 * created by Mohammad Sajjad
 * on 28-01-2021 at 14:39
 * mohammadsajjad679@gmail.com
 */

public class FarmDetailHeaderDelegate extends BaseDelegate {
    final FarmDetailHeaderViewHolder.FarmHeaderClickListener listener;

    public FarmDetailHeaderDelegate(FarmDetailHeaderViewHolder.FarmHeaderClickListener listener) {
        this.listener = listener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new FarmDetailHeaderViewHolder(parent, listener);
    }
}
