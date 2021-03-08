package com.farmers.buyers.modules.seller.coupon.odel;

/**
 * Created by Mohammad sajjad on 08-03-2021.
 * mohammadsajjad679@gmail.com
 */
public class EditCouponRequestParams {
    String couponCode;
    String discountType;
    String discountCheck;
    String amount;
    String minimumOrder;
    String termsCondition;
    String startDate;
    String endDate;
    String couponId;
    String farmId;
    String loginId;
    String authKey;

    public EditCouponRequestParams(String couponCode, String discountType, String discountCheck, String amount, String minimumOrder, String termsCondition, String startDate, String endDate, String couponId, String farmId, String loginId, String authKey) {
        this.couponCode = couponCode;
        this.discountType = discountType;
        this.discountCheck = discountCheck;
        this.amount = amount;
        this.minimumOrder = minimumOrder;
        this.termsCondition = termsCondition;
        this.startDate = startDate;
        this.endDate = endDate;
        this.couponId = couponId;
        this.farmId = farmId;
        this.loginId = loginId;
        this.authKey = authKey;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public String getDiscountType() {
        return discountType;
    }

    public String getDiscountCheck() {
        return discountCheck;
    }

    public String getAmount() {
        return amount;
    }

    public String getMinimumOrder() {
        return minimumOrder;
    }

    public String getTermsCondition() {
        return termsCondition;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getCouponId() {
        return couponId;
    }

    public String getFarmId() {
        return farmId;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getAuthKey() {
        return authKey;
    }
}
