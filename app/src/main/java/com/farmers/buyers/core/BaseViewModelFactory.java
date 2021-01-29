package com.farmers.buyers.core;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.farmers.buyers.modules.login.LoginViewModel;

/**
 * created by Mohammad Sajjad
 * on 29-01-2021 at 15:49
 * mohammadsajjad679@gmail.com
 */

    public class BaseViewModelFactory implements ViewModelProvider.Factory {

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(LoginViewModel.class)) {
                return (T) new LoginViewModel();
            }

            return null;
        }
    }