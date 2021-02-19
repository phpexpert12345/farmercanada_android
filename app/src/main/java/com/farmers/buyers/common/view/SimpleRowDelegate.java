package com.farmers.buyers.common.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

/**
 * created by Mohammad Sajjad
 * on 31-01-2021 at 13:27
 * mohammadsajjad679@gmail.com
 */

public class SimpleRowDelegate extends BaseDelegate {

    SimpleRowViewHolder.OnSimpleRowItemClickedListener rowItemClickedListener;

    public SimpleRowDelegate(SimpleRowViewHolder.OnSimpleRowItemClickedListener rowItemClickedListener) {
        this.rowItemClickedListener = rowItemClickedListener;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new SimpleRowViewHolder(parent, rowItemClickedListener);
    }
}
