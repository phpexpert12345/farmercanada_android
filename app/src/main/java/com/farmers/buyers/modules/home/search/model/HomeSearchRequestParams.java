package com.farmers.buyers.modules.home.search.model;

/**
 * Created by Mohammad sajjad on 01-03-2021.
 * mohammadsajjad679@gmail.com
 */
public class HomeSearchRequestParams {
    String authKey;
    String searchQuery;

    public HomeSearchRequestParams(String authKey, String searchQuery) {
        this.authKey = authKey;
        this.searchQuery = searchQuery;
    }

    public String getAuthKey() {
        return authKey;
    }

    public String getSearchQuery() {
        return searchQuery;
    }
}
