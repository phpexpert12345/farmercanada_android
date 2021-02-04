package com.farmers.buyers.modules.orders.track.adapter;

import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.modules.orders.track.view.TackOrderHeaderDelegate;
import com.farmers.buyers.modules.orders.track.view.TrackOrderItemDelegate;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 03-02-2021 at 13:29
 * mohammadsajjad679@gmail.com
 */

public class TrackOrderAdapter extends BaseAdapter {

    public TrackOrderAdapter() {
        super();
        this.initDelegate();
    }

    @Override
    public void initDelegate() {
        delegates.put(CardConstant.TRACK_ORDER_HEADER_ITEM, new TackOrderHeaderDelegate());
        delegates.put(CardConstant.TRACK_ORDER_ITEM_ADAPTER, new TrackOrderItemDelegate());
    }
}
