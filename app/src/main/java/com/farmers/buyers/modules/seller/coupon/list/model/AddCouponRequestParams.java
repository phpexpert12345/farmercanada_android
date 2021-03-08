package com.farmers.buyers.modules.seller.coupon.list.model;

/**
 * Created by Mohammad sajjad on 07-03-2021.
 * mohammadsajjad679@gmail.com
 */
public class AddCouponRequestParams {
    private String couponCode;
    private String discountType;
    private String discountAmount;
    private String minimumOrder;
    private String isTermsCondition;
    private String startDate;
    private String endDate;
    private String farmId;
    private String loginId;
    private String authKey;
    private String couponId;


    public AddCouponRequestParams(String couponCode, String discountType, String discountAmount, String minimumOrder, String isTermsCondition, String startDate, String endDate, String farmId, String loginId, String authKey) {
        this.couponCode = couponCode;
        this.discountType = discountType;
        this.discountAmount = discountAmount;
        this.minimumOrder = minimumOrder;
        this.isTermsCondition = isTermsCondition;
        this.startDate = startDate;
        this.endDate = endDate;
        this.farmId = farmId;
        this.loginId = loginId;
        this.authKey = authKey;
    }

    public AddCouponRequestParams(String couponCode, String discountType, String discountAmount, String minimumOrder, String isTermsCondition, String startDate, String endDate, String farmId, String loginId, String authKey, String couponId) {
        this.couponCode = couponCode;
        this.discountType = discountType;
        this.discountAmount = discountAmount;
        this.minimumOrder = minimumOrder;
        this.isTermsCondition = isTermsCondition;
        this.startDate = startDate;
        this.endDate = endDate;
        this.farmId = farmId;
        this.loginId = loginId;
        this.authKey = authKey;
        this.couponId = couponId;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public String getDiscountType() {
        return discountType;
    }

    public String getDiscountAmount() {
        return discountAmount;
    }

    public String getMinimumOrder() {
        return minimumOrder;
    }

    public String getTermsCondition() {
        return isTermsCondition;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
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

    public String getCouponId() {
        return couponId;
    }
}
