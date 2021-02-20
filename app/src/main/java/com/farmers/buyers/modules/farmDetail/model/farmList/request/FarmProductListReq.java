package com.farmers.buyers.modules.farmDetail.model.farmList.request;

import java.io.Serializable;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/17/2021.
 */
public class FarmProductListReq implements Serializable {

    public String auth_key;
    public String farm_id;

    public FarmProductListReq(String auth_key, String farm_id) {
        this.auth_key = auth_key;
        this.farm_id = farm_id;
    }

    public String getAuth_key() {
        return auth_key;
    }

    public String getFarm_id() {
        return farm_id;
    }
}
