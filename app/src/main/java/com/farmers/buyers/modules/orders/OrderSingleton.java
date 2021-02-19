package com.farmers.buyers.modules.orders;

import com.farmers.buyers.modules.cart.myCart.model.chargeTax.TaxData;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/19/2021.
 */
public class OrderSingleton {

    private static OrderSingleton instance;


    TaxData taxData;

    public TaxData getTaxData() {
        return taxData;
    }

    public void setTaxData(TaxData taxData) {
        this.taxData = taxData;
    }

    private float discount_amount;
    private float coupon_discount_amount;
    private float Total_amount;
    private float delivery_amount;
    private float gst_tax_amount;
    private float subtotal;


    public float getDiscount_amount() {
        return discount_amount;
    }

    public void setDiscount_amount(float discount_amount) {
        this.discount_amount = discount_amount;
    }

    public float getCoupon_discount_amount() {
        return coupon_discount_amount;
    }

    public void setCoupon_discount_amount(float coupon_discount_amount) {
        this.coupon_discount_amount = coupon_discount_amount;
    }

    public float getTotal_amount() {
        return Total_amount;
    }

    public void setTotal_amount(float total_amount) {
        Total_amount = total_amount;
    }

    public float getDelivery_amount() {
        return delivery_amount;
    }

    public void setDelivery_amount(float delivery_amount) {
        this.delivery_amount = delivery_amount;
    }

    public float getGst_tax_amount() {
        return gst_tax_amount;
    }

    public void setGst_tax_amount(float gst_tax_amount) {
        this.gst_tax_amount = gst_tax_amount;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public static synchronized OrderSingleton getInstance(){
        return instance;
    }

    public static void setInstance(OrderSingleton instance) {
        OrderSingleton.instance = instance;
    }

    public static void initInstance(){
        if (instance==null){
            instance=new OrderSingleton();
        }
    }
}
