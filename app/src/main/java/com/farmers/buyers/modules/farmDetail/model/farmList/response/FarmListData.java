package com.farmers.buyers.modules.farmDetail.model.farmList.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/17/2021.
 */
public class FarmListData implements Serializable {

    @SerializedName("CategoryList")
    @Expose
    private List<CategoryList> categoryList = null;

    public List<CategoryList> getCategoryList() {
        return categoryList;
    }
}
