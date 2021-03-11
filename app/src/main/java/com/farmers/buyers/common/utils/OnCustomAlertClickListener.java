package com.farmers.buyers.common.utils;

import android.app.Dialog;

public interface OnCustomAlertClickListener {
    void onNegativeBtnClicked(Dialog dialog);

    void onPositiveBtnClicked(Dialog dialog);
}
