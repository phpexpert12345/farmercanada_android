package com.farmers.buyers.modules.cart.myCart.model.chargeTax;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/18/2021.
 */
public class TaxRequestParam {

    private String auth_key;
    private String farm_id;
    private String delivery_distance;
    private String order_type;
    private String subtotal_amount;


    public TaxRequestParam(String auth_key, String farm_id, String delivery_distance, String order_type, String subtotal_amount) {
        this.auth_key = auth_key;
        this.farm_id = farm_id;
        this.delivery_distance = delivery_distance;
        this.order_type = order_type;
        this.subtotal_amount = subtotal_amount;
    }

    public String getAuth_key() {
        return auth_key;
    }

    public String getFarm_id() {
        return farm_id;
    }

    public String getDelivery_distance() {
        return delivery_distance;
    }

    public String getOrder_type() {
        return order_type;
    }

    public String getSubtotal_amount() {
        return subtotal_amount;
    }
}
