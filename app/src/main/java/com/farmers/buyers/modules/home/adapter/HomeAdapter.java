package com.farmers.buyers.modules.home.adapter;


import com.farmers.buyers.common.view.MultipleTextItemViewHolder;
import com.farmers.buyers.common.view.SimpleTitleDelegate;
import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.modules.home.view.HomeCategoriesItemDelegate;
import com.farmers.buyers.modules.home.view.HomeDelegate;
import com.farmers.buyers.modules.home.view.HomeDeliveryTypeDelegate;
import com.farmers.buyers.modules.home.view.HomeFarmTypeDelegate;
import com.farmers.buyers.modules.home.view.HomeFilterItemDelegate;
import com.farmers.buyers.modules.home.view.HomeHeaderDelegate;
import com.farmers.buyers.modules.home.view.HomeHeaderViewHolder;
import com.farmers.buyers.modules.home.view.HomeItemsDelegate;
import com.farmers.buyers.modules.home.view.HomeTopOffersDelegate;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 25-01-2021 at 20:27
 * mohammadsajjad679@gmail.com
 */

public class HomeAdapter extends BaseAdapter {
    private HomeHeaderViewHolder.HeaderItemClickListener headerListener;
    private MultipleTextItemViewHolder.FilterItemClickListener filterItemClickListener;

    public HomeAdapter(HomeHeaderViewHolder.HeaderItemClickListener headerListener, MultipleTextItemViewHolder.FilterItemClickListener filterItemClickListener) {
        super();
        this.headerListener = headerListener;
        this.filterItemClickListener = filterItemClickListener;
        this.initDelegate();
    }

    @Override
    public void initDelegate() {
        delegates.put(CardConstant.HOME_HEADER_ADAPTER, new HomeHeaderDelegate(headerListener));
        delegates.put(CardConstant.HOME_SEARCH_ITEM_ADAPTER, new HomeDelegate());
        delegates.put(CardConstant.HOME_FARM_LIST_ITEM_ADAPTER, new HomeItemsDelegate());
        delegates.put(CardConstant.HOME_CATEGORY_ITEM_ADAPTER, new HomeCategoriesItemDelegate());
        delegates.put(CardConstant.SIMPLE_TITLE_ITEM_ADAPTER, new SimpleTitleDelegate());
        delegates.put(CardConstant.HOME_TOP_OFFER_ADAPTER, new HomeTopOffersDelegate());
        delegates.put(CardConstant.MULTIPLE_ITEM_TYPE_ADAPTER, new HomeFilterItemDelegate(filterItemClickListener));
        delegates.put(CardConstant.DELIVERY_TYPE_ADAPTER, new HomeDeliveryTypeDelegate());
        delegates.put(CardConstant.HOME_FARM_TYPE_ADAPTER, new HomeFarmTypeDelegate());
    }
}
