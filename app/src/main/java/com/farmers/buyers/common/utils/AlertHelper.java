package com.farmers.buyers.common.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;

import kotlin.jvm.functions.Function0;

/**
 * Created by Mohammad sajjad on 02-03-2021.
 * mohammadsajjad679@gmail.com
 */
public class AlertHelper {

    public static void showAlert(Context context, String title, String message, Boolean positiveBtn, String positiveBtnTitle, Boolean negativeBtn, String negativeBtnTitle, Boolean isCancellable, OnAlertClickListener alertClickListener) {
        Dialog dialog = null;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(positiveBtnTitle, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertClickListener.onPositiveBtnClicked();
                dialog.dismiss();
            }
        });
        if (negativeBtn) {
            alertDialog.setNegativeButton(negativeBtnTitle, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    alertClickListener.onNegativeBtnClicked();
                    dialog.dismiss();
                }
            });
        }


        dialog = alertDialog.create();
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.setCancelable(isCancellable);
        dialog.show();
    }

}

