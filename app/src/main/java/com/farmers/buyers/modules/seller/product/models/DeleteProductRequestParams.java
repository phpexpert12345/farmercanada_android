package com.farmers.buyers.modules.seller.product.models;

/**
 * Created by Mohammad sajjad on 08-03-2021.
 * mohammadsajjad679@gmail.com
 */
public class DeleteProductRequestParams {
    private String productId;
    private String authKey;

    public DeleteProductRequestParams(String productId, String authKey) {
        this.productId = productId;
        this.authKey = authKey;
    }

    public String getProductId() {
        return productId;
    }

    public String getAuthKey() {
        return authKey;
    }
}
