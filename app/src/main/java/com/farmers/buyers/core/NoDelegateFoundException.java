package com.farmers.buyers.core;

import androidx.annotation.Nullable;

/**
 * created by Mohammad Sajjad
 * on 25-01-2021 at 11:10
 * mohammadsajjad679@gmail.com
 */

public class NoDelegateFoundException extends Exception {

    int item;
    String errorClass;

    NoDelegateFoundException(int item, String errorClass) {
        this.item = item;
        this.errorClass = errorClass;
    }

    @Nullable
    @Override
    public String getMessage() {
        return "No delegate found for the view type : "+item +"in" +errorClass;
    }
}
