package com.farmers.buyers.modules.saveFarms.model;

public class SaveUnSaveFarmRequestModel {
    String farm_id;
    String loginId;
    int farm_favourite_status;
    String auth_key;

    public SaveUnSaveFarmRequestModel(String farm_id, String loginId, int farm_favourite_status, String auth_key) {
        this.farm_id = farm_id;
        this.loginId = loginId;
        this.farm_favourite_status = farm_favourite_status;
        this.auth_key = auth_key;
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
}
