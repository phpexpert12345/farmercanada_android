package com.farmers.buyers.modules.profile.model;

/**
 * created by Mohammad Sajjad
 * on 29-01-2021 at 11:40
 * mohammadsajjad679@gmail.com
 */

public class ProfileRequestParams {

    String account_type;
    String LoginId;
    String authKey;

    public ProfileRequestParams(String account_type, String loginId, String authKey) {
        this.account_type = account_type;
        LoginId = loginId;
        this.authKey = authKey;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public String getLoginId() {
        return LoginId;
    }

    public void setLoginId(String loginId) {
        LoginId = loginId;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }
}