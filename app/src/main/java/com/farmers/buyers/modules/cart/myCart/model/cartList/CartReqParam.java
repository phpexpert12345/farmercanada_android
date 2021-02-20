package com.farmers.buyers.modules.cart.myCart.model.cartList;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/20/2021.
 */
public class CartReqParam {

    private String auth_key;
    private String LoginId;
    private String farm_id;


    public CartReqParam(String auth_key, String loginId, String farm_id) {
        this.auth_key = auth_key;
        LoginId = loginId;
        this.farm_id = farm_id;
    }

    public String getAuth_key() {
        return auth_key;
    }

    public String getLoginId() {
        return LoginId;
    }

    public String getFarm_id() {
        return farm_id;
    }
}
