package com.farmers.buyers.modules.seller.coupon.list.view;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;

/**
 * created by Mohammad Sajjad
 * on 09-02-2021 at 17:05
 * mohammadsajjad679@gmail.com
 */

public class ManageCouponViewHolder extends BaseViewHolder {

    public ManageCouponViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.manage_coupon_item_layout));
    }

    @Override
    public void bindView(RecyclerViewListItem items) {

    }
}
