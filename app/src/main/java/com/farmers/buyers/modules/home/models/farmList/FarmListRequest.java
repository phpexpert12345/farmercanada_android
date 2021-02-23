package com.farmers.buyers.modules.home.models.farmList;

import java.io.Serializable;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/16/2021.
 */
public class FarmListRequest implements Serializable {

    private String authKey;
    private Double customer_lat;
    private Double customer_long;
    private String customer_full_address;
    private String customer_city;
    private String farm_type;
    private String farm_type_developer_information;
    private String farm_service_type;
    private String order_type_developer_information;
    private String farm_category_id;
    private String pageno;
    private String LoginId;

    public FarmListRequest(String authKey, Double customer_lat, Double customer_long, String customer_full_address, String customer_city, String farm_type, String farm_service_type, String farm_category_id, String pageno, String loginId) {
        this.authKey = authKey;
        this.customer_lat = customer_lat;
        this.customer_long = customer_long;
        this.customer_full_address = customer_full_address;
        this.customer_city = customer_city;
        this.farm_type = farm_type;
        this.farm_service_type = farm_service_type;
        this.farm_category_id = farm_category_id;
        this.pageno = pageno;
        LoginId = loginId;
    }


    public String getAuthKey() {
        return authKey;
    }

    public Double getCustomer_lat() {
        return customer_lat;
    }

    public Double getCustomer_long() {
        return customer_long;
    }

    public String getCustomer_full_address() {
        return customer_full_address;
    }

    public String getCustomer_city() {
        return customer_city;
    }

    public String getFarm_type() {
        return farm_type;
    }

    public String getFarm_type_developer_information() {
        return farm_type_developer_information;
    }

    public String getFarm_service_type() {
        return farm_service_type;
    }

    public String getOrder_type_developer_information() {
        return order_type_developer_information;
    }

    public String getFarm_category_id() {
        return farm_category_id;
    }

    public String getPageno() {
        return pageno;
    }

    public String getLoginId() {
        return LoginId;
    }
}
