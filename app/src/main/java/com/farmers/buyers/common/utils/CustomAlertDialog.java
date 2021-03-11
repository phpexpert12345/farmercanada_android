package com.farmers.buyers.common.utils;

import android.app.Activity;
import android.app.Dialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.farmers.buyers.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class CustomAlertDialog {

    public static Dialog dialog;

    public static void showDialogCustom(Activity context, int drawable, String title, String message, OnCustomAlertClickListener alertClickListener) {

        dialog = new Dialog(context, R.style.NewDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.layout_custom_alert_dialoge);

        CircleImageView img_alert = dialog.findViewById(R.id.img_alert);
        TextView tv_alert_title = dialog.findViewById(R.id.tv_alert_title);
        TextView tv_alert_message = dialog.findViewById(R.id.tv_alert_message);
        Button bt_ok = dialog.findViewById(R.id.bt_ok);
        Button bt_cancel = dialog.findViewById(R.id.bt_cancel);

        img_alert.setImageResource(drawable);
        tv_alert_title.setText(title);
        tv_alert_message.setText(message);

        bt_ok.setOnClickListener(v -> {
            alertClickListener.onPositiveBtnClicked(dialog);
        });

        bt_cancel.setOnClickListener(v -> {
            alertClickListener.onNegativeBtnClicked(dialog);
        });

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.BOTTOM;
        dialog.getWindow().setAttributes(lp);
        dialog.show();
    }

    public static void showDialogCustom2(Activity contex, int drawable, String title, String message) {

        dialog = new Dialog(contex, R.style.NewDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.layout_custom_alert_dialoge);

        CircleImageView img_alert = dialog.findViewById(R.id.img_alert);
        TextView tv_alert_title = dialog.findViewById(R.id.tv_alert_title);
        TextView tv_alert_message = dialog.findViewById(R.id.tv_alert_message);
        Button bt_ok = dialog.findViewById(R.id.bt_ok);
        Button bt_cancel = dialog.findViewById(R.id.bt_cancel);

        img_alert.setImageResource(drawable);
        tv_alert_title.setText(title);
        tv_alert_message.setText(message);

        bt_ok.setOnClickListener(v -> {

            dialog.dismiss();
            contex.finish();
        });

        bt_cancel.setOnClickListener(v -> {
            dialog.dismiss();
        });

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.BOTTOM;
        dialog.getWindow().setAttributes(lp);
        dialog.show();
    }
}
