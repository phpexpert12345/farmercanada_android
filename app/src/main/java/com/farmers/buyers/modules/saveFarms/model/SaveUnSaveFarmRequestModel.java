package com.farmers.buyers.modules.saveFarms.model;

public class SaveUnSaveFarmRequestModel {
    String farm_id;
    String loginId;
    int farm_favourite_status;
    String auth_key;
    String favoriteId;


    public SaveUnSaveFarmRequestModel(String farm_id, String loginId, int farm_favourite_status, String auth_key, String favoriteId) {
        this.farm_id = farm_id;
        this.loginId = loginId;
        this.farm_favourite_status = farm_favourite_status;
        this.auth_key = auth_key;
        this.favoriteId = favoriteId;
    }

    public String getFarm_id() {
        return farm_id;
    }

    public String getLoginId() {
        return loginId;
    }

    public int getFarm_favourite_status() {
        return farm_favourite_status;
    }

    public String getAuth_key() {
        return auth_key;
    }

    public String getFavoriteId() {
        return favoriteId;
    }
}
