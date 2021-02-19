package com.farmers.buyers.modules.cart.myCart.model.chargeTax;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/18/2021.
 */
public class TaxData implements Serializable {

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
}
