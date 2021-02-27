package com.farmers.buyers.modules.cart.myCart.model.increaseDecrease;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/22/2021.
 */
public class IncreaseDecreaseParams {
    private String auth_key;
    private String cart_id;
    private String option_type;
    private String loginId;


    public IncreaseDecreaseParams(String auth_key, String cart_id, String option_type) {
        this.auth_key = auth_key;
        this.cart_id = cart_id;
        this.option_type = option_type;
    }

    public IncreaseDecreaseParams(String auth_key, String loginId) {
        this.auth_key = auth_key;
        this.loginId = loginId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getAuth_key() {
        return auth_key;
    }

    public String getCart_id() {
        return cart_id;
    }

    public String getOption_type() {
        return option_type;
    }
}
