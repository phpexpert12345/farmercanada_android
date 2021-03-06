package com.farmers.buyers.modules.profile.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

/**
 * created by Mohammad Sajjad
 * on 31-01-2021 at 04:31
 * mohammadsajjad679@gmail.com
 */

public class MyProfileOptionItemDelegate extends BaseDelegate {
    private MyProfileOptionItemViewHolder.OnProfileOptionsGridMenuClickedListener profileOptionsGridMenuClicked;

    public MyProfileOptionItemDelegate(MyProfileOptionItemViewHolder.OnProfileOptionsGridMenuClickedListener profileOptionsGridMenuClicked) {
        this.profileOptionsGridMenuClicked = profileOptionsGridMenuClicked;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new MyProfileOptionItemViewHolder(parent, profileOptionsGridMenuClicked);
    }
}
