package com.farmers.buyers.modules.seller.coupon.list;

import com.farmers.buyers.modules.seller.coupon.list.model.ManageCouponItem;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 09-02-2021 at 18:15
 * mohammadsajjad679@gmail.com
 */

public class ManageCouponTransformer {

    public static List<ManageCouponItem> getCoupons() {
        List<ManageCouponItem> item = new ArrayList<>();
        item.add(new ManageCouponItem("","","","","",false));
        item.add(new ManageCouponItem("","","","","",false));
        item.add(new ManageCouponItem("","","","","",false));
        item.add(new ManageCouponItem("","","","","",false));
        return item;
    }
}
