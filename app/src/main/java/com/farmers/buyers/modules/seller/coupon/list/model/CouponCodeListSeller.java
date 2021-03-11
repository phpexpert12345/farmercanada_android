package com.farmers.buyers.modules.seller.coupon.list.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohammad sajjad on 08-03-2021.
 * mohammadsajjad679@gmail.com
 */
public class CouponCodeListSeller {
    @SerializedName("CouponID")
    @Expose
    private Integer couponID;
    @SerializedName("farm_id")
    @Expose
    private Integer farmId;
    @SerializedName("coupon_code")
    @Expose
    private String couponCode;
    @SerializedName("discount_type")
    @Expose
    private Integer discountType;
    @SerializedName("discount_type_check")
    @Expose
    private Object discountTypeCheck;
    @SerializedName("active_status")
    @Expose
    private Integer activeStatus;
    @SerializedName("discount_amount")
    @Expose
    private String discountAmount;
    @SerializedName("discount_minimum_order")
    @Expose
    private String discountMinimumOrder;
    @SerializedName("number_person_use")
    @Expose
    private Integer numberPersonUse;
    @SerializedName("discount_type_name")
    @Expose
    private String discountTypeName;
    @SerializedName("active_status_name")
    @Expose
    private String activeStatusName;
    @SerializedName("term_condition")
    @Expose
    private String termCondition;

    public Integer getCouponID() {
        return couponID;
    }

    public void setCouponID(Integer couponID) {
        this.couponID = couponID;
    }

    public Integer getFarmId() {
        return farmId;
    }

    public void setFarmId(Integer farmId) {
        this.farmId = farmId;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public Integer getDiscountType() {
        return discountType;
    }

    public void setDiscountType(Integer discountType) {
        this.discountType = discountType;
    }

    public Object getDiscountTypeCheck() {
        return discountTypeCheck;
    }

    public void setDiscountTypeCheck(Object discountTypeCheck) {
        this.discountTypeCheck = discountTypeCheck;
    }

    public Integer getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Integer activeStatus) {
        this.activeStatus = activeStatus;
    }

    public String getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(String discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getDiscountMinimumOrder() {
        return discountMinimumOrder;
    }

    public void setDiscountMinimumOrder(String discountMinimumOrder) {
        this.discountMinimumOrder = discountMinimumOrder;
    }

    public Integer getNumberPersonUse() {
        return numberPersonUse;
    }

    public void setNumberPersonUse(Integer numberPersonUse) {
        this.numberPersonUse = numberPersonUse;
    }

    public String getDiscountTypeName() {
        return discountTypeName;
    }

    public void setDiscountTypeName(String discountTypeName) {
        this.discountTypeName = discountTypeName;
    }

    public String getActiveStatusName() {
        return activeStatusName;
    }

    public void setActiveStatusName(String activeStatusName) {
        this.activeStatusName = activeStatusName;
    }

    public String getTermCondition() {
        return termCondition;
    }

    public void setTermCondition(String termCondition) {
        this.termCondition = termCondition;
    }

}
