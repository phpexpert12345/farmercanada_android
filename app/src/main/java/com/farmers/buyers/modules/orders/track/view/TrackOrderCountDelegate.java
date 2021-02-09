package com.farmers.buyers.modules.orders.track.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

/**
 * created by Mohammad Sajjad
 * on 09-02-2021 at 12:39
 * mohammadsajjad679@gmail.com
 */

public class TrackOrderCountDelegate extends BaseDelegate {

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new TrackOrderCountViewHolder(parent);
    }
}
