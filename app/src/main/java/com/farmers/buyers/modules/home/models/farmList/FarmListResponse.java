package com.farmers.buyers.modules.home.models.farmList;

import com.farmers.buyers.modules.home.models.BaseResponse;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * Created by Ganesh on 2/16/2021.
 */
public class FarmListResponse extends BaseResponse  {

    @SerializedName("data")
   public FarmData farmData;

    public FarmData getFarmData() {
        return farmData;
    }




}
