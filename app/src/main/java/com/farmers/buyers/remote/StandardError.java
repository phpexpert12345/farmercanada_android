package com.farmers.buyers.remote;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * created by Mohammad Sajjad
 * on 29-01-2021 at 11:15
 * mohammadsajjad679@gmail.com
 */

public class StandardError extends Throwable {

    private final int errorCode;
    private final String developerError;
    private final String displayError;

    public StandardError() {
        errorCode = 0;
        developerError = "";
        displayError = "";
    }

    public StandardError(int errorCode, String developerError, String displayError) {
        super(displayError);
        this.errorCode = errorCode;
        this.displayError = displayError;
        this.developerError = developerError;

        Map<String, Object>  exceptionAttributes= new HashMap<>();
        exceptionAttributes.put("response_code", errorCode);
        exceptionAttributes.put("developer_error", developerError);
        exceptionAttributes.put("display_error", displayError);

            Log.e("StandardizedError: ", exceptionAttributes.toString());
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getDeveloperError() {
        return developerError;
    }

    public String getDisplayError() {
        return displayError;
    }
}
