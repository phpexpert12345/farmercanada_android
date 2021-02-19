package com.farmers.buyers.modules.signUp.model;

import com.google.gson.annotations.SerializedName;

/**
 * created by Mohammad Sajjad
 * on 11-02-2021 at 17:15
 * mohammadsajjad679@gmail.com
 */

public class VerifyOtpApiModel {

    public boolean status;
    public String status_message;

    private OtpData data;

    public static class OtpData {
    }
}
