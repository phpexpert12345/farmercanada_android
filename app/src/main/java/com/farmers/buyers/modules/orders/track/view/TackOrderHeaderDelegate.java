package com.farmers.buyers.modules.orders.track.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

/**
 * created by Mohammad Sajjad
 * on 03-02-2021 at 13:31
 * mohammadsajjad679@gmail.com
 */

public class TackOrderHeaderDelegate extends BaseDelegate {

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new TrackOrderHeaderViewHolder(parent);
    }
}
