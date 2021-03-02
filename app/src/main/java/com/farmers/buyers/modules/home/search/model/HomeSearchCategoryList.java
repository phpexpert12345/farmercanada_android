package com.farmers.buyers.modules.home.search.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeSearchCategoryList {

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
        private List<HomeSearchSubProductItemsRecord> subProductItemsRecord = null;

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public String getCategoryDescription() {
            return categoryDescription;
        }

        public void setCategoryDescription(String categoryDescription) {
            this.categoryDescription = categoryDescription;
        }

        public String getCategoryItemAvailable() {
            return categoryItemAvailable;
        }

        public void setCategoryItemAvailable(String categoryItemAvailable) {
            this.categoryItemAvailable = categoryItemAvailable;
        }

        public List<HomeSearchSubProductItemsRecord> getSubProductItemsRecord() {
            return subProductItemsRecord;
        }

        public void setSubProductItemsRecord(List<HomeSearchSubProductItemsRecord> subProductItemsRecord) {
            this.subProductItemsRecord = subProductItemsRecord;
        }

    }
