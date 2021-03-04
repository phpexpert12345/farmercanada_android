package com.farmers.seller.modules.setupSellerAccount.storeDetails;

import java.io.Serializable;

/**
 * Created by Mohammad sajjad on 04-03-2021.
 * mohammadsajjad679@gmail.com
 */
public class StoreSetupExtra implements Serializable {
    String name;
    String location;
    String city;
    String state;
    String deliveryType;
    String radius;
    String deliveryCharges;
    String pickupCharges;
    String additionalCharges;
    String deliveryMessage;
    String pickupMessage;
    String minimumDeliveryOrder;
    String minimumPickupOrder;

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
}
