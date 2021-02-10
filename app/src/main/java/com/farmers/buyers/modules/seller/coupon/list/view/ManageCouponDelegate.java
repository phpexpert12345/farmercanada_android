package com.farmers.buyers.modules.seller.coupon.list.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

/**
 * created by Mohammad Sajjad
 * on 09-02-2021 at 17:05
 * mohammadsajjad679@gmail.com
 */

public class ManageCouponDelegate extends BaseDelegate {

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ManageCouponViewHolder(parent);
    }
}
