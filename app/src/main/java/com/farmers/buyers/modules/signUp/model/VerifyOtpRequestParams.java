package com.farmers.buyers.modules.signUp.model;

/**
 * created by Mohammad Sajjad
 * on 11-02-2021 at 17:14
 * mohammadsajjad679@gmail.com
 */

public class VerifyOtpRequestParams {

    String userId;
    String otp;

    public VerifyOtpRequestParams(String userId, String otp) {
        this.userId = userId;
        this.otp = otp;
    }

    public String getUserId() {
        return userId;
    }

    public String getOtp() {
        return otp;
    }
}
