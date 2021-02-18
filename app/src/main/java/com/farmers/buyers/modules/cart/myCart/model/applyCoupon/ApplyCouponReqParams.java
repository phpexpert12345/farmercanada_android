package com.farmers.buyers.modules.cart.myCart.model.applyCoupon;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/17/2021.
 */
public class ApplyCouponReqParams {
    private String auth_key;
    private String coupon_code;
    private String farm_id;
    private String subtotal_amount;

    public ApplyCouponReqParams(String auth_key, String coupon_code, String farm_id, String subtotal_amount) {
        this.auth_key = auth_key;
        this.coupon_code = coupon_code;
        this.farm_id = farm_id;
        this.subtotal_amount = subtotal_amount;
    }

    public String getAuth_key() {
        return auth_key;
    }

    public String getCoupon_code() {
        return coupon_code;
    }

    public String getFarm_id() {
        return farm_id;
    }

    public String getSubtotal_amount() {
        return subtotal_amount;
    }
}
