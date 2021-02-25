package com.farmers.buyers.modules.home.models.farmList;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/16/2021.
 */
public class FarmData{

    @SerializedName("subProductItemsRecord")

    public ArrayList<SubProductItemRecord> subProductItemRecords;

    public ArrayList<SubProductItemRecord> getSubProductItemRecords() {
        return subProductItemRecords;
    }
}
