package com.farmers.seller.modules.setupSellerAccount.storeDetails;

import java.io.File;
import java.io.Serializable;

import kotlin.io.FilesKt;

/**
 * Created by Mohammad sajjad on 04-03-2021.
 * mohammadsajjad679@gmail.com
 */
public class StoreSetupExtra implements Serializable {
    String name;
    String location;
    String city;
    String state;
    String country;
    String postalCode;
    String deliveryType;
    String radius;
    String deliveryCharges;
    String pickupCharges;
    String additionalCharges;
    String deliveryMessage;
    String pickupMessage;
    String minimumDeliveryOrder;
    String minimumPickupOrder;
    String docTypeOne;
    String docTypeTwo;
    String companyType;
    Double mapLat;
    Double mapLong;
    String docType;
    String storeLogoPath;
    File storeLogo;
    File docOneFront;
    File docOneBack;
    File docTwoFront;
    File docTwoBack;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public String getDeliveryCharges() {
        return deliveryCharges;
    }

    public void setDeliveryCharges(String deliveryCharges) {
        this.deliveryCharges = deliveryCharges;
    }

    public String getPickupCharges() {
        return pickupCharges;
    }

    public void setPickupCharges(String pickupCharges) {
        this.pickupCharges = pickupCharges;
    }

    public String getAdditionalCharges() {
        return additionalCharges;
    }

    public void setAdditionalCharges(String additionalCharges) {
        this.additionalCharges = additionalCharges;
    }

    public String getDeliveryMessage() {
        return deliveryMessage;
    }

    public void setDeliveryMessage(String deliveryMessage) {
        this.deliveryMessage = deliveryMessage;
    }

    public String getPickupMessage() {
        return pickupMessage;
    }

    public void setPickupMessage(String pickupMessage) {
        this.pickupMessage = pickupMessage;
    }

    public String getMinimumDeliveryOrder() {
        return minimumDeliveryOrder;
    }

    public void setMinimumDeliveryOrder(String minimumDeliveryOrder) {
        this.minimumDeliveryOrder = minimumDeliveryOrder;
    }

    public String getMinimumPickupOrder() {
        return minimumPickupOrder;
    }

    public void setMinimumPickupOrder(String minimumPickupOrder) {
        this.minimumPickupOrder = minimumPickupOrder;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getDocTypeOne() {
        return docTypeOne;
    }

    public void setDocTypeOne(String docTypeOne) {
        this.docTypeOne = docTypeOne;
    }

    public String getDocTypeTwo() {
        return docTypeTwo;
    }

    public void setDocTypeTwo(String docTypeTwo) {
        this.docTypeTwo = docTypeTwo;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public Double getMapLat() {
        return mapLat;
    }

    public void setMapLat(Double mapLat) {
        this.mapLat = mapLat;
    }

    public Double getMapLong() {
        return mapLong;
    }

    public void setMapLong(Double mapLong) {
        this.mapLong = mapLong;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getStoreLogoPath() {
        return storeLogoPath;
    }

    public void setStoreLogoPath(String storeLogoPath) {
        this.storeLogoPath = storeLogoPath;
    }

    public File getStoreLogo() {
        return storeLogo;
    }

    public void setStoreLogo(File storeLogo) {
        this.storeLogo = storeLogo;
    }

    public File getDocOneFront() {
        return docOneFront;
    }

    public void setDocOneFront(File docOneFront) {
        this.docOneFront = docOneFront;
    }

    public File getDocOneBack() {
        return docOneBack;
    }

    public void setDocOneBack(File docOneBack) {
        this.docOneBack = docOneBack;
    }

    public File getDocTwoFront() {
        return docTwoFront;
    }

    public void setDocTwoFront(File docTwoFront) {
        this.docTwoFront = docTwoFront;
    }

    public File getDocTwoBack() {
        return docTwoBack;
    }

    public void setDocTwoBack(File docTwoBack) {
        this.docTwoBack = docTwoBack;
    }
}
