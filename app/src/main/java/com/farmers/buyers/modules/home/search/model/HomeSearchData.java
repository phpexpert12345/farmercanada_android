package com.farmers.buyers.modules.home.search.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeSearchData {

@SerializedName("CategoryList")
@Expose
private List<HomeSearchCategoryList> categoryList = null;

public List<HomeSearchCategoryList> getCategoryList() {
    return categoryList;
}

public void setCategoryList(List<HomeSearchCategoryList> categoryList) {
    this.categoryList = categoryList;
}

}
