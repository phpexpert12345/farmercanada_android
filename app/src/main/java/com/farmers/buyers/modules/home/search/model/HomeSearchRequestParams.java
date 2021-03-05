package com.farmers.buyers.modules.home.search.model;

/**
 * Created by Mohammad sajjad on 01-03-2021.
 * mohammadsajjad679@gmail.com
 */
public class HomeSearchRequestParams {
    String authKey;
    String searchQuery;
    String loginId;

    public HomeSearchRequestParams(String authKey, String searchQuery, String loginId) {
        this.authKey = authKey;
        this.searchQuery = searchQuery;
        this.loginId = loginId;
    }

    public String getAuthKey() {
        return authKey;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public String getLoginId() {
        return loginId;
    }
}
