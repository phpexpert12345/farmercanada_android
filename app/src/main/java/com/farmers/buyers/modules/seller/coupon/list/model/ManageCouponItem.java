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
    String status;
    String discountType;
    String discountTypeCheck;
    String discountMinimumAmount;
    String termCondition;
    String amount;

    public ManageCouponItem(String couponId, String couponCode, String addDate, String endDate, String applied, String status, String discountType, String discountTypeCheck, String discountMinimumAmount, String termCondition, String amount) {
        this.couponId = couponId;
        this.couponCode = couponCode;
        this.addDate = addDate;
        this.endDate = endDate;
        this.applied = applied;
        this.status = status;
        this.discountType = discountType;
        this.discountTypeCheck = discountTypeCheck;
        this.discountMinimumAmount = discountMinimumAmount;
        this.termCondition = termCondition;
        this.amount = amount;
    }
//
//    public ManageCouponItem(String couponId, String couponCode, String addDate, String endDate, String applied, String status) {
//        this.couponId = couponId;
//        this.couponCode = couponCode;
//        this.addDate = addDate;
//        this.endDate = endDate;
//        this.applied = applied;
//        this.status = status;
//    }


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

    public String getStatus() {
        return status;
    }

    public String getDiscountType() {
        return discountType;
    }

    public String getDiscountTypeCheck() {
        return discountTypeCheck;
    }

    public String getDiscountMinimumAmount() {
        return discountMinimumAmount;
    }

    public String getTermCondition() {
        return termCondition;
    }

    public String getAmount() {
        return amount;
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
