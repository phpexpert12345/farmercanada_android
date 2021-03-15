package com.farmers.buyers.modules.home.homeFragment;

/**
 * created by Mohammad Sajjad
 * on 29-01-2021 at 11:40
 * mohammadsajjad679@gmail.com
 */

public class CategoryListRequestParams {

    String userId;
    String authKey;
    String farm_id;
    String mon_available;
    String mon_start_time;
    String mon_close_time;
    String tue_available;
    String tue_start_time;
    String tue_close_time;
    String wed_available;
    String wed_start_time;
    String wed_close_time;
    String thu_available;
    String thu_start_time;
    String thu_close_time;
    String fri_available;
    String fri_start_time;
    String fri_close_time;
    String sat_available;
    String sat_start_time;
    String sat_close_time;
    String sun_available;
    String sun_start_time;
    String sun_close_time;
    String status;
    String order_decline_reason;

    public CategoryListRequestParams(String userId, String authKey, String status, String order_decline_reason) {
        this.userId = userId;
        this.authKey = authKey;
        this.status = status;
        this.order_decline_reason = order_decline_reason;
    }

    public CategoryListRequestParams(String userId, String authKey, String status) {
        this.userId = userId;
        this.authKey = authKey;
        this.status = status;
    }

    public CategoryListRequestParams(String userId, String authKey) {
        this.userId = userId;
        this.authKey = authKey;
    }

    public CategoryListRequestParams(String authKey) {
        this.authKey = authKey;
    }

    public String getOrder_decline_reason() {
        return order_decline_reason;
    }

    public void setOrder_decline_reason(String order_decline_reason) {
        this.order_decline_reason = order_decline_reason;
    }

    public String getUserId() {
        return userId;
    }

    public String getAuthKey() {
        return authKey;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CategoryListRequestParams(String userId, String authKey, String farm_id, String mon_available, String mon_start_time,
                                     String mon_close_time, String tue_available, String tue_start_time, String tue_close_time,
                                     String wed_available, String wed_start_time, String wed_close_time, String thu_available,
                                     String thu_start_time, String thu_close_time, String fri_available, String fri_start_time,
                                     String fri_close_time, String sat_available, String sat_start_time, String sat_close_time,
                                     String sun_available, String sun_start_time, String sun_close_time) {
        this.userId = userId;
        this.authKey = authKey;
        this.farm_id = farm_id;
        this.mon_available = mon_available;
        this.mon_start_time = mon_start_time;
        this.mon_close_time = mon_close_time;
        this.tue_available = tue_available;
        this.tue_start_time = tue_start_time;
        this.tue_close_time = tue_close_time;
        this.wed_available = wed_available;
        this.wed_start_time = wed_start_time;
        this.wed_close_time = wed_close_time;
        this.thu_available = thu_available;
        this.thu_start_time = thu_start_time;
        this.thu_close_time = thu_close_time;
        this.fri_available = fri_available;
        this.fri_start_time = fri_start_time;
        this.fri_close_time = fri_close_time;
        this.sat_available = sat_available;
        this.sat_start_time = sat_start_time;
        this.sat_close_time = sat_close_time;
        this.sun_available = sun_available;
        this.sun_start_time = sun_start_time;
        this.sun_close_time = sun_close_time;
    }

    public String getFarm_id() {
        return farm_id;
    }

    public String getMon_available() {
        return mon_available;
    }

    public String getMon_start_time() {
        return mon_start_time;
    }

    public String getMon_close_time() {
        return mon_close_time;
    }

    public String getTue_available() {
        return tue_available;
    }

    public String getTue_start_time() {
        return tue_start_time;
    }

    public String getTue_close_time() {
        return tue_close_time;
    }

    public String getWed_available() {
        return wed_available;
    }

    public String getWed_start_time() {
        return wed_start_time;
    }

    public String getWed_close_time() {
        return wed_close_time;
    }

    public String getThu_available() {
        return thu_available;
    }

    public String getThu_start_time() {
        return thu_start_time;
    }

    public String getThu_close_time() {
        return thu_close_time;
    }

    public String getFri_available() {
        return fri_available;
    }

    public String getFri_start_time() {
        return fri_start_time;
    }

    public String getFri_close_time() {
        return fri_close_time;
    }

    public String getSat_available() {
        return sat_available;
    }

    public String getSat_start_time() {
        return sat_start_time;
    }

    public String getSat_close_time() {
        return sat_close_time;
    }

    public String getSun_available() {
        return sun_available;
    }

    public String getSun_start_time() {
        return sun_start_time;
    }

    public String getSun_close_time() {
        return sun_close_time;
    }
}