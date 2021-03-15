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

    public String getTxt_doc_1() {
        return txt_doc_1;
    }

    public void setTxt_doc_1(String txt_doc_1) {
        this.txt_doc_1 = txt_doc_1;
    }

    public String getTxt_doc_2() {
        return txt_doc_2;
    }

    public void setTxt_doc_2(String txt_doc_2) {
        this.txt_doc_2 = txt_doc_2;
    }

    String txt_doc_1;
    String txt_doc_2;
    String loginId;
    String authKey;
    Double lat;
    Double lng;
    String store_type_farm;
    String store_type_local;
    String pickup_available;
    String delivery_available;

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

    public SetupStoreRequestParams(String storeType, String storeName, String storeAddress, String city, String state,
                                   String country, String postCode, String orderType, String pickUpMinAmount,
                                   String pickUpMsg, String deliveryMapLocationArea, String radius, String deliveryCharge,
                                   String additionalCharge, String deliveryMinAmount, String deliveryMsg, String companyType,
                                   String docType, File doc1, File doc2, File doc3, File doc4, File logo, String loginId,
                                   String authKey, Double lat, Double lng, String store_type_farm, String store_type_local,
                                   String pickup_available, String delivery_available) {
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
        this.store_type_farm = store_type_farm;
        this.store_type_local = store_type_local;
        this.pickup_available = pickup_available;
        this.delivery_available = delivery_available;
    }
    public SetupStoreRequestParams(File doc1, File doc2, File doc3, File doc4,String txt_doc_1,String txt_doc_2,String authKey,String loginId){
        this.doc1 = doc1;
        this.doc2 = doc2;
        this.doc3 = doc3;
        this.doc4 = doc4;
        this.txt_doc_1=txt_doc_1;
        this.txt_doc_2=txt_doc_2;
        this.authKey=authKey;
        this.loginId=loginId;
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

    public String getStore_type_farm() {
        return store_type_farm;
    }

    public void setStore_type_farm(String store_type_farm) {
        this.store_type_farm = store_type_farm;
    }

    public String getStore_type_local() {
        return store_type_local;
    }

    public void setStore_type_local(String store_type_local) {
        this.store_type_local = store_type_local;
    }

    public String getPickup_available() {
        return pickup_available;
    }

    public void setPickup_available(String pickup_available) {
        this.pickup_available = pickup_available;
    }

    public String getDelivery_available() {
        return delivery_available;
    }

    public void setDelivery_available(String delivery_available) {
        this.delivery_available = delivery_available;
    }
}
