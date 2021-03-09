package com.farmers.seller.modules.setupSellerAccount.model;

import java.io.File;

/**
 * Created by Mohammad sajjad on 05-03-2021.
 * mohammadsajjad679@gmail.com
 */
public class SetupStoreRequestParams {
    String storeType;
    String storeName;
    String storeAddress;
    String city;
    String state;
    String country;
    String postCode;
    String orderType;
    String pickUpMinAmount;
    String pickUpMsg;
    String deliveryMapLocationArea;
    String radius;
    String deliveryCharge;
    String additionalCharge;
    String deliveryMinAmount;
    String deliveryMsg;
    String companyType;
    String docType;
    File doc1;
    File doc2;
    File doc3;
    File doc4;
    File logo;
    String loginId;
    String authKey;
    Double lat;
    Double lng;


    public SetupStoreRequestParams(String storeType, String storeName, String storeAddress, String city, String state, String country, String postCode, String orderType, String pickUpMinAmount, String pickUpMsg, String deliveryMapLocationArea, String radius, String deliveryCharge, String additionalCharge, String deliveryMinAmount, String deliveryMsg, String companyType, String docType, File doc1, File doc2, File doc3, File doc4, File logo, String loginId, String authKey, Double lat, Double lng) {
        this.storeType = storeType;
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postCode = postCode;
        this.orderType = orderType;
        this.pickUpMinAmount = pickUpMinAmount;
        this.pickUpMsg = pickUpMsg;
        this.deliveryMapLocationArea = deliveryMapLocationArea;
        this.radius = radius;
        this.deliveryCharge = deliveryCharge;
        this.additionalCharge = additionalCharge;
        this.deliveryMinAmount = deliveryMinAmount;
        this.deliveryMsg = deliveryMsg;
        this.companyType = companyType;
        this.docType = docType;
        this.doc1 = doc1;
        this.doc2 = doc2;
        this.doc3 = doc3;
        this.doc4 = doc4;
        this.logo = logo;
        this.loginId = loginId;
        this.authKey = authKey;
        this.lat = lat;
        this.lng = lng;

    }

    public String getStoreType() {
        return storeType;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getOrderType() {
        return orderType;
    }

    public String getPickUpMinAmount() {
        return pickUpMinAmount;
    }

    public String getPickUpMsg() {
        return pickUpMsg;
    }

    public String getDeliveryMapLocationArea() {
        return deliveryMapLocationArea;
    }

    public String getRadius() {
        return radius;
    }

    public String getDeliveryCharge() {
        return deliveryCharge;
    }

    public String getAdditionalCharge() {
        return additionalCharge;
    }

    public String getDeliveryMinAmount() {
        return deliveryMinAmount;
    }

    public String getDeliveryMsg() {
        return deliveryMsg;
    }

    public String getCompanyType() {
        return companyType;
    }

    public String getDocType() {
        return docType;
    }

    public File getDoc1() {
        return doc1;
    }

    public File getDoc2() {
        return doc2;
    }

    public File getDoc3() {
        return doc3;
    }

    public File getDoc4() {
        return doc4;
    }

    public File getLogo() {
        return logo;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getAuthKey() {
        return authKey;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }
}