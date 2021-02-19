package com.farmers.buyers.modules.seller.sellerProfile.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.modules.profile.view.MyProfileHeaderViewHolder;

/**
 * created by Mohammad Sajjad
 * on 09-02-2021 at 16:06
 * mohammadsajjad679@gmail.com
 */

public class SellerProfileHeaderDelegate extends BaseDelegate {

    SellerProfileHeaderViewHolder.SellerProfileItemClickListener profileItemClickListener;

    public SellerProfileHeaderDelegate(SellerProfileHeaderViewHolder.SellerProfileItemClickListener profileItemClickListener) {
        this.profileItemClickListener = profileItemClickListener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new SellerProfileHeaderViewHolder(parent, profileItemClickListener);
    }
}