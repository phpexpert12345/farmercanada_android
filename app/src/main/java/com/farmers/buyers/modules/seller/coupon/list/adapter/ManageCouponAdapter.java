package com.farmers.buyers.modules.seller.coupon.list.adapter;

import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.modules.seller.coupon.list.view.ManageCouponDelegate;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 09-02-2021 at 17:02
 * mohammadsajjad679@gmail.com
 */

public class ManageCouponAdapter extends BaseAdapter {

    public ManageCouponAdapter() {
        this.initDelegate();
    }

    @Override
    public void initDelegate() {
        delegates.put(CardConstant.MANAGE_COUPON_ITEM_ADAPTER, new ManageCouponDelegate());
    }
}
