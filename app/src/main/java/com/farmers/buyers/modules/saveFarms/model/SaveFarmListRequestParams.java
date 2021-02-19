package com.farmers.buyers.modules.saveFarms.model;

public class SaveFarmListRequestParams {
    String loginId;
    String auth_key;

    public SaveFarmListRequestParams(String loginId, String auth_key) {
        this.loginId = loginId;
        this.auth_key = auth_key;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getAuth_key() {
        return auth_key;
    }
}
