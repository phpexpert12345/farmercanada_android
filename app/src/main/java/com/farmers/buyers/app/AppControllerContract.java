package com.farmers.buyers.app;

import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

/**
 * created by Mohammad Sajjad
 * on 23-01-2021 at 00:53
 * mohammadsajjad679@gmail.com
 */

public interface AppControllerContract {

    Boolean getIsLoggedIn();
    String getUserId();
    String getAuthenticationKey();
    String getDeviceId();
    String getLoginId();
    String getWalletAmount();
    String getProfilePic();
    String getSharedPreferences(String key, @Nullable Object defaultValue);
}
