package com.farmers.buyers.modules.cart;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.farmers.buyers.R;

/**
 * created by Mohammad Sajjad
 * on 31-01-2021 at 01:43
 * mohammadsajjad679@gmail.com
 */

public class OrderSuccessDialog {

    private final Context context;
    private Dialog dialog;
    private Button cancelBtn , submitButton;
    private Boolean isCancelable;
    public OrderSuccessDialog(Context context, final OnDialogClickListeners listeners, Boolean isCancelable) {
        this.context = context;
        this.isCancelable = isCancelable;

        dialog = new Dialog(context);
        dialog.setContentView(R.layout.order_success_dialog);
        cancelBtn = dialog.findViewById(R.id.order_success_dialog_cancel_btn);
        submitButton = dialog.findViewById(R.id.order_success_dialog_submit_btn);
        dialog.setCancelable(isCancelable);


        if (dialog.getWindow() != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // this is optional
        }

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listeners.onCancelClicked();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listeners.onSubmitClicked();
            }
        });
    }

    public void showDialog() {
        dialog.show();
    }

    public void dismissDialog() {
        dialog.dismiss();
    }

    public interface OnDialogClickListeners {
        void onCancelClicked();
        void onSubmitClicked();
    }
}
