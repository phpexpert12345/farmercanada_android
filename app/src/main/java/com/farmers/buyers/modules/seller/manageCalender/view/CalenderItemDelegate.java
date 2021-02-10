package com.farmers.buyers.modules.seller.manageCalender.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

/**
 * created by Mohammad Sajjad
 * on 10-02-2021 at 22:20
 * mohammadsajjad679@gmail.com
 */

public class CalenderItemDelegate extends BaseDelegate {
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new CalenderItemViewHolder(parent);
    }
}
