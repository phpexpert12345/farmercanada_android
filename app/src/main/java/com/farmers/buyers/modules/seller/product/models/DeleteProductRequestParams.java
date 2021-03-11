package com.farmers.buyers.modules.seller.product.models;

/**
 * Created by Mohammad sajjad on 08-03-2021.
 * mohammadsajjad679@gmail.com
 */
public class DeleteProductRequestParams {
    private String loginId;
    private String authKey;
    private String productId;
    private String quantity;

    public DeleteProductRequestParams(String loginId, String authKey, String productId, String quantity) {
        this.loginId = loginId;
        this.authKey = authKey;
        this.productId = productId;
        this.quantity = quantity;
    }

    public DeleteProductRequestParams(String productId, String authKey) {
        this.productId = productId;
        this.authKey = authKey;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getProductId() {
        return productId;
    }

    public String getAuthKey() {
        return authKey;
    }
}
