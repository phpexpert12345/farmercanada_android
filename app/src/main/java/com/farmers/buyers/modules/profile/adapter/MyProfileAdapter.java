package com.farmers.buyers.modules.profile.adapter;

import com.farmers.buyers.common.view.SimpleRowDelegate;
import com.farmers.buyers.common.view.SimpleRowViewHolder;
import com.farmers.buyers.common.view.SimpleTitleDelegate;
import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.modules.profile.view.MyProfileHeaderDelegate;
import com.farmers.buyers.modules.profile.view.MyProfileHeaderViewHolder;
import com.farmers.buyers.modules.profile.view.MyProfileOptionItemViewHolder;
import com.farmers.buyers.modules.profile.view.MyProfileOptionMenuDelegate;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 31-01-2021 at 03:45
 * mohammadsajjad679@gmail.com
 */

public class MyProfileAdapter extends BaseAdapter {
    MyProfileHeaderViewHolder.MyProfileItemClickListener profileItemClickListener;
    SimpleRowViewHolder.OnSimpleRowItemClickedListener rowItemClickedListener;
    private MyProfileOptionItemViewHolder.OnProfileOptionsGridMenuClickedListener profileOptionsGridMenuClicked;


    public MyProfileAdapter(MyProfileHeaderViewHolder.MyProfileItemClickListener profileItemClickListener, SimpleRowViewHolder.OnSimpleRowItemClickedListener rowItemClickedListener,
                            MyProfileOptionItemViewHolder.OnProfileOptionsGridMenuClickedListener profileOptionsGridMenuClicked) {
        super();
        this.profileItemClickListener = profileItemClickListener;
        this.rowItemClickedListener = rowItemClickedListener;
        this.profileOptionsGridMenuClicked = profileOptionsGridMenuClicked;
        initDelegate();
    }

    @Override
    public void initDelegate() {
        delegates.put(CardConstant.PROFILE_HEADER_ADAPTER, new MyProfileHeaderDelegate(profileItemClickListener));
        delegates.put(CardConstant.PROFILE_OPTION_MENU_ADAPTER, new MyProfileOptionMenuDelegate(profileOptionsGridMenuClicked));
        delegates.put(CardConstant.SIMPLE_TITLE_ITEM_ADAPTER, new SimpleTitleDelegate());
        delegates.put(CardConstant.SIMPLE_ROW_LIST_ITEM, new SimpleRowDelegate(rowItemClickedListener));
    }
}
