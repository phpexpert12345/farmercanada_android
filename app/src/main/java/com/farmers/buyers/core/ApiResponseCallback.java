package com.farmers.buyers.core;

import com.farmers.buyers.remote.StandardError;

/**
 * created by Mohammad Sajjad
 * on 29-01-2021 at 12:14
 * mohammadsajjad679@gmail.com
 */

public interface ApiResponseCallback<T> {
    void onSuccess(T response);
    void onFailure(StandardError standardError);
}
