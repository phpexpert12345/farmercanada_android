package com.farmers.seller.modules.viewOrderDetails;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseActivity;

public class ViewOrderDetailsActivity extends BaseActivity implements View.OnClickListener {

    public String KEY = "";
    public LinearLayout ll_order_status, ll_order_delivered, ll_order_reject_accept;
    public Button bt_track_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order_details);

        setupToolbar(new ToolbarConfig("Order Details", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        }, false, new ToolbarMenuConfig(R.drawable.ic_notification, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        })));
        init();
    }

    private void init() {

        KEY = getIntent().getStringExtra("KEY");

        ll_order_status = findViewById(R.id.ll_order_status);
        ll_order_delivered = findViewById(R.id.ll_order_delivered);
        ll_order_reject_accept = findViewById(R.id.ll_order_reject_accept);
        bt_track_order = findViewById(R.id.bt_track_order);

        ll_order_status.setVisibility(View.GONE);
        ll_order_delivered.setVisibility(View.GONE);
        ll_order_reject_accept.setVisibility(View.GONE);

        ll_order_status.setOnClickListener(this);
        ll_order_delivered.setOnClickListener(this);
        ll_order_reject_accept.setOnClickListener(this);
        bt_track_order.setOnClickListener(this);

        if (KEY.equalsIgnoreCase("reject_accept")) {
            ll_order_reject_accept.setVisibility(View.VISIBLE);
        } else if (KEY.equalsIgnoreCase("order_preparing")) {
            ll_order_status.setVisibility(View.VISIBLE);
        } else if (KEY.equalsIgnoreCase("order_delivered")) {
            ll_order_delivered.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public Boolean showToolbar() {
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_order_status:
                break;

            case R.id.ll_order_delivered:
                break;

            case R.id.ll_order_reject_accept:
                break;

            case R.id.bt_track_order:
                order_track_dialog(this);
                break;
        }
    }

    public void order_track_dialog(Activity activity) {

        final Dialog dialog = new Dialog(activity, R.style.NewDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.order_track_dialog);

        Button bt_order_track = dialog.findViewById(R.id.bt_order_track);
        Button bt_continue = dialog.findViewById(R.id.bt_continue);

        bt_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        bt_order_track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(lp);
        dialog.show();
    }
}