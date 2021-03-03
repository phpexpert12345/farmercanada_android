package com.farmers.buyers.modules.signUp.model;

import com.farmers.buyers.modules.login.model.LoginApiModel;
import com.google.gson.annotations.SerializedName;

/**
 * created by Mohammad Sajjad
 * on 11-02-2021 at 17:15
 * mohammadsajjad679@gmail.com
 */

public class VerifyOtpApiModel {

    public boolean status;
    public String status_message;

    public LoginApiModel.Data data;

    public static class OtpData {
    }
}
