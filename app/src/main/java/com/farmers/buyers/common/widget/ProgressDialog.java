package com.farmers.buyers.common.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.farmers.buyers.R;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * created by Mohammad Sajjad
 * on 06-02-2021 at 15:35
 * mohammadsajjad679@gmail.com
 */

public class ProgressDialog {

    private static ProgressDialog progressDialog;
    private AVLoadingIndicatorView loader ;
    private TextView titleTv;
    private Dialog dialog;


    public static ProgressDialog getInstance(){
        if (progressDialog == null) {
            progressDialog = new ProgressDialog();
        }
        return progressDialog;
    }

    public void init(Context context, String title) {

        dialog = new Dialog(context, R.style.ProgressDialogTheme);
        View view = LayoutInflater.from(context).inflate(R.layout.progress_dialog_layout, null);
        dialog.setContentView(view);
        loader = view.findViewById(R.id.avi_loader);
        titleTv = view.findViewById(R.id.progress_dialog_title_tv);
        titleTv.setText(title == null ? "Loading..." : title);
        dialog.setCancelable(true);
        dialog.show();

    }

    public void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }



}
