package com.farmers.buyers.modules.profile.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

/**
 * created by Mohammad Sajjad
 * on 31-01-2021 at 03:51
 * mohammadsajjad679@gmail.com
 */

public class MyProfileHeaderDelegate extends BaseDelegate {

    MyProfileHeaderViewHolder.MyProfileItemClickListener profileItemClickListener;

    public MyProfileHeaderDelegate(MyProfileHeaderViewHolder.MyProfileItemClickListener profileItemClickListener) {
        this.profileItemClickListener = profileItemClickListener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new MyProfileHeaderViewHolder(parent, profileItemClickListener);
    }
}
