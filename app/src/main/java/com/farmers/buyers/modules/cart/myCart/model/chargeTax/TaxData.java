package com.farmers.buyers.modules.cart.myCart.model.chargeTax;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/18/2021.
 */
public class TaxData implements Serializable, RecyclerViewListItem {

    @SerializedName("GST_Tax_Amount")
    @Expose
    private String gSTTaxAmount;
    @SerializedName("GST_Tax")
    @Expose
    private Integer gSTTax;
    @SerializedName("GST_Tax_Type")
    @Expose
    private String gSTTaxType;
    @SerializedName("Service_Tax_Amount")
    @Expose
    private String serviceTaxAmount;
    @SerializedName("Service_Tax")
    @Expose
    private Integer serviceTax;
    @SerializedName("Service_Tax_Type")
    @Expose
    private String serviceTaxType;
    @SerializedName("Package_fee_Amount")
    @Expose
    private String packageFeeAmount;
    @SerializedName("Package_fee_Tax")
    @Expose
    private String packageFeeTax;
    @SerializedName("Package_fee_Type")
    @Expose
    private String packageFeeType;
    @SerializedName("Delivery_charge")
    @Expose
    private String deliveryCharge;
    @SerializedName("minimum_order_amount")
    @Expose
    private String minimumOrderAmount;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @SerializedName("title")
    @Expose
    private String title;

    private boolean isApplyCouponButton;
    private boolean isDiscountTextView;
    private boolean isRemoveDiscountButton;
    private boolean isCouponApplied;


    public boolean isCouponApplied() {
        return isCouponApplied;
    }

    public void setCouponApplied(boolean couponApplied) {
        isCouponApplied = couponApplied;
    }

    private String subTotal;

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public boolean isApplyCouponButton() {
        return isApplyCouponButton;
    }

    public void setApplyCouponButton(boolean applyCouponButton) {
        isApplyCouponButton = applyCouponButton;
    }

    public boolean isDiscountTextView() {
        return isDiscountTextView;
    }

    public void setDiscountTextView(boolean discountTextView) {
        isDiscountTextView = discountTextView;
    }

    public boolean isRemoveDiscountButton() {
        return isRemoveDiscountButton;
    }

    public void setRemoveDiscountButton(boolean removeDiscountButton) {
        isRemoveDiscountButton = removeDiscountButton;
    }

    public float discountAmount;

    public float getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(float discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getgSTTaxAmount() {
        return gSTTaxAmount;
    }

    public Integer getgSTTax() {
        return gSTTax;
    }

    public String getgSTTaxType() {
        return gSTTaxType;
    }

    public String getServiceTaxAmount() {
        return serviceTaxAmount;
    }

    public Integer getServiceTax() {
        return serviceTax;
    }

    public String getServiceTaxType() {
        return serviceTaxType;
    }

    public String getPackageFeeAmount() {
        return packageFeeAmount;
    }

    public String getPackageFeeTax() {
        return packageFeeTax;
    }

    public String getPackageFeeType() {
        return packageFeeType;
    }

    public String getDeliveryCharge() {
        return deliveryCharge;
    }

    public String getMinimumOrderAmount() {
        return minimumOrderAmount;
    }

    @Override
    public int getViewType() {
        return CardConstant.MY_CART_CHECKOUT_ITEM_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
