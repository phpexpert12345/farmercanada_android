package com.farmers.buyers.modules.cart.myCart.model.applyCoupon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/17/2021.
 */
public class ApplyCouponData implements Serializable {

    @SerializedName("Coupon_Discount_Price")
    @Expose
    private String Coupon_Discount_Price;

    @SerializedName("coupon_code")
    @Expose
    private String coupon_code;


    public String getCoupon_Discount_Price() {
        return Coupon_Discount_Price;
    }

    public String getCoupon_code() {
        return coupon_code;
    }
}
