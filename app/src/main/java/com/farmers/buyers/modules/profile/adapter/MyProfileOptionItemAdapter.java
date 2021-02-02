package com.farmers.buyers.modules.profile.adapter;

import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.modules.profile.view.MyProfileOptionItemDelegate;
import com.farmers.buyers.modules.profile.view.MyProfileOptionItemViewHolder;
import com.farmers.buyers.modules.profile.view.MyProfileOptionMenuDelegate;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 31-01-2021 at 04:29
 * mohammadsajjad679@gmail.com
 */

public class MyProfileOptionItemAdapter extends BaseAdapter {
    private MyProfileOptionItemViewHolder.OnProfileOptionsGridMenuClickedListener profileOptionsGridMenuClicked;

    public MyProfileOptionItemAdapter(MyProfileOptionItemViewHolder.OnProfileOptionsGridMenuClickedListener profileOptionsGridMenuClicked) {
        super();
        this.profileOptionsGridMenuClicked = profileOptionsGridMenuClicked;
        this.initDelegate();
    }
    @Override
    public void initDelegate() {
        delegates.put(CardConstant.PROFILE_OPTION_MENU_ITEM_ADAPTER, new MyProfileOptionItemDelegate(profileOptionsGridMenuClicked));
    }
}
