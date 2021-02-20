package com.farmers.buyers.modules.cart.order.model.submit;

import java.io.Serializable;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/19/2021.
 */
public class SubmitRequestParam implements Serializable {

   String  auth_key;
    String customer_long;
    String customer_lat;
    String customer_postcode;
    String customer_city;
    String customer_address;
    String WalletPay;
    String order_type;
    String SpecialInstruction;
    String delivery_time;
    String delivery_date;
    String discount_amount;
    String coupon_discount_amount;
    String Total_amount;
    String delivery_amount;
    String package_fee_amount;
    String service_tax_amount;
    String gst_tax_amount;
    String subtotal;
    String payment_type;
    String address_id;
    String LoginId;
    String instructions;
    String item_unit_type;
    String strsizeid;
    String Price;
    String Quantity;
    String itemId;
    String payment_transaction_id;
    String farm_id;

    public SubmitRequestParam(String auth_key, String customer_long, String customer_lat, String customer_postcode, String customer_city, String customer_address, String walletPay, String order_type, String specialInstruction, String delivery_time, String delivery_date, String discount_amount, String coupon_discount_amount, String total_amount, String delivery_amount, String package_fee_amount, String service_tax_amount, String gst_tax_amount, String subtotal, String payment_type, String address_id, String loginId, String instructions, String item_unit_type, String strsizeid, String price, String quantity, String itemId, String payment_transaction_id, String farm_id) {
        this.auth_key = auth_key;
        this.customer_long = customer_long;
        this.customer_lat = customer_lat;
        this.customer_postcode = customer_postcode;
        this.customer_city = customer_city;
        this.customer_address = customer_address;
        WalletPay = walletPay;
        this.order_type = order_type;
        SpecialInstruction = specialInstruction;
        this.delivery_time = delivery_time;
        this.delivery_date = delivery_date;
        this.discount_amount = discount_amount;
        this.coupon_discount_amount = coupon_discount_amount;
        Total_amount = total_amount;
        this.delivery_amount = delivery_amount;
        this.package_fee_amount = package_fee_amount;
        this.service_tax_amount = service_tax_amount;
        this.gst_tax_amount = gst_tax_amount;
        this.subtotal = subtotal;
        this.payment_type = payment_type;
        this.address_id = address_id;
        LoginId = loginId;
        this.instructions = instructions;
        this.item_unit_type = item_unit_type;
        this.strsizeid = strsizeid;
        Price = price;
        Quantity = quantity;
        this.itemId = itemId;
        this.payment_transaction_id = payment_transaction_id;
        this.farm_id = farm_id;
    }

    public String getAuth_key() {
        return auth_key;
    }

    public String getCustomer_long() {
        return customer_long;
    }

    public String getCustomer_lat() {
        return customer_lat;
    }

    public String getCustomer_postcode() {
        return customer_postcode;
    }

    public String getCustomer_city() {
        return customer_city;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public String getWalletPay() {
        return WalletPay;
    }

    public String getOrder_type() {
        return order_type;
    }

    public String getSpecialInstruction() {
        return SpecialInstruction;
    }

    public String getDelivery_time() {
        return delivery_time;
    }

    public String getDelivery_date() {
        return delivery_date;
    }

    public String getDiscount_amount() {
        return discount_amount;
    }

    public String getCoupon_discount_amount() {
        return coupon_discount_amount;
    }

    public String getTotal_amount() {
        return Total_amount;
    }

    public String getDelivery_amount() {
        return delivery_amount;
    }

    public String getPackage_fee_amount() {
        return package_fee_amount;
    }

    public String getService_tax_amount() {
        return service_tax_amount;
    }

    public String getGst_tax_amount() {
        return gst_tax_amount;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public String getAddress_id() {
        return address_id;
    }

    public String getLoginId() {
        return LoginId;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getItem_unit_type() {
        return item_unit_type;
    }

    public String getStrsizeid() {
        return strsizeid;
    }

    public String getPrice() {
        return Price;
    }

    public String getQuantity() {
        return Quantity;
    }

    public String getItemId() {
        return itemId;
    }

    public String getPayment_transaction_id() {
        return payment_transaction_id;
    }

    public String getFarm_id() {
        return farm_id;
    }
}
