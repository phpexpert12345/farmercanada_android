package com.farmers.buyers.app;

import androidx.annotation.Nullable;

/**
 * created by Mohammad Sajjad
 * on 29-01-2021 at 10:53
 * mohammadsajjad679@gmail.com
 */

public class NoInternetConnectionException extends Exception {
    @Override
    public String getMessage() {
        return "No Internet Connection";
    }
}
