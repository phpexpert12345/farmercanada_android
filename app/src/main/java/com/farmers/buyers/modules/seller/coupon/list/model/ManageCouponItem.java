package com.farmers.buyers.modules.seller.coupon.list.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 09-02-2021 at 17:03
 * mohammadsajjad679@gmail.com
 */

public class ManageCouponItem implements RecyclerViewListItem {
    String couponId;
    String couponCode;
    String addDate;
    String endDate;
    String applied;
    Boolean status;

    public ManageCouponItem(String couponId, String couponCode, String addDate, String endDate, String applied, Boolean status) {
        this.couponId = couponId;
        this.couponCode = couponCode;
        this.addDate = addDate;
        this.endDate = endDate;
        this.applied = applied;
        this.status = status;
    }

    public String getCouponId() {
        return couponId;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public String getAddDate() {
        return addDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getApplied() {
        return applied;
    }

    public Boolean getStatus() {
        return status;
    }

    @Override
    public int getViewType() {
        return CardConstant.MANAGE_COUPON_ITEM_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
