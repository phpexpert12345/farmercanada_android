package com.farmers.buyers.common.provider;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/**
 * created by Mohammad Sajjad
 * on 08-02-2021 at 14:23
 * mohammadsajjad679@gmail.com
 */

public class PermissionProvider {
    private Activity activity;
    private List<String> list;
    private int code;

    public PermissionProvider(Activity activity, List<String> list, int code) {
        this.activity = activity;
        this.list = list;
        this.code = code;
    }

    private int isPermissionsGranted() {
        // PERMISSION_GRANTED : Constant Value: 0
        // PERMISSION_DENIED : Constant Value: -1
        int counter = 0;

        for (int i = 0 ; i<list.size() ; i++) {
            counter += ContextCompat.checkSelfPermission(activity, list.get(i));
        }
        return counter;
    }

    public final void checkPermissions(@NotNull Function1 callBack) {
        Intrinsics.checkParameterIsNotNull(callBack, "callBack");
        if (this.isPermissionsGranted() != 0) {
            callBack.invoke(false);
            this.showAlert();
        } else {
            callBack.invoke(true);
        }

    }

    private String deniedPermission() {
        for (String permission: list) {
            if (ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_DENIED) return permission;
        }
        return "";
    }

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Need Permission(s)");
        builder.setMessage("Please provide required permissions to do proceed");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                requestPermissions();
            }
        });

        builder.setNeutralButton("Cancel", null);
    }

    public void requestPermissions() {
        String permissions = deniedPermission();

        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permissions)) {
            ActivityCompat.requestPermissions(activity, (String[]) list.toArray(), code);
        }
        else {
            ActivityCompat.requestPermissions(activity, (String[]) list.toArray(), code);
        }
    }

    public Boolean processPermissionResult(int requestCode, String [] permissions, int[] grantResult) {
        int result = 0;
        if (grantResult.length == 0) {
            for (int item:grantResult) {
                result += item;

            }
        }
        if (result == PackageManager.PERMISSION_GRANTED)  {
         return true;
        }
        else {
            return false;
        }
    }

}
