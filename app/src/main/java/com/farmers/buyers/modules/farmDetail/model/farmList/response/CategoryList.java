package com.farmers.buyers.modules.farmDetail.model.farmList.response;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/17/2021.
 */
public class CategoryList implements Serializable, RecyclerViewListItem {

    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("category_description")
    @Expose
    private String categoryDescription;
    @SerializedName("category_item_available")
    @Expose
    private String categoryItemAvailable;
    @SerializedName("subProductItemsRecord")
    @Expose
    private List<SubProductItemsRecord> subProductItemsRecord = null;

    public String getCategoryName() {
        return categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public String getCategoryItemAvailable() {
        return categoryItemAvailable;
    }

    public List<SubProductItemsRecord> getSubProductItemsRecord() {
        return subProductItemsRecord;
    }

    @Override
    public int getViewType() {
        return CardConstant.FARM_DETAIL_VEGETABLE_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
