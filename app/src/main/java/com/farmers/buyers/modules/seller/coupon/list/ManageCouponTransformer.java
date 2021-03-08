package com.farmers.buyers.modules.seller.coupon.list;

import android.widget.LinearLayout;

import com.farmers.buyers.modules.seller.coupon.list.model.CouponCodeListSeller;
import com.farmers.buyers.modules.seller.coupon.list.model.ManageCouponItem;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 09-02-2021 at 18:15
 * mohammadsajjad679@gmail.com
 */

public class ManageCouponTransformer {

    public static List<ManageCouponItem> getCoupons(List<CouponCodeListSeller> apiData) {
        List<ManageCouponItem> item = new ArrayList<>();
        for (int i =0 ; i < apiData.size(); i++) {
            CouponCodeListSeller couponData = apiData.get(i);

            item.add(new ManageCouponItem(
                    String.valueOf(couponData.getCouponID()),
                    couponData.getCouponCode(),
                    "",
                    "",
                    String.valueOf(couponData.getNumberPersonUse()),
                    couponData.getActiveStatusName(),
                    "",
                    "",
                    couponData.getDiscountMinimumOrder(),
                    couponData.getTermCondition(),
                    couponData.getDiscountAmount()
            ));
        }

        return item;
    }
}
