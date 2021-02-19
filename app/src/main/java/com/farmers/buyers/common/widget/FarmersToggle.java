package com.farmers.buyers.common.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.SwitchCompat;

import com.farmers.buyers.R;

/**
 * created by Mohammad Sajjad
 * on 27-01-2021 at 12:23
 * mohammadsajjad679@gmail.com
 */

public class FarmersToggle extends LinearLayout {

    private Context context;
    private TextView deliveryTv, pickUpTv;
    private SwitchCompat switchCompat;

    public FarmersToggle(Context context) {
        super(context);
        this.context = context;
        View view = View.inflate(context, R.layout.app_toggle_layout, this);
        deliveryTv = view.findViewById(R.id.app_toggle_delivery_tv);
        pickUpTv = view.findViewById(R.id.toggleButton1);
        switchCompat = view.findViewById(R.id.app_toggle_pickup_tv);


    }

    public FarmersToggle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public FarmersToggle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FarmersToggle(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;

    }


    public void init(final ToggleCheckedListener listener) {
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked) {
                    deliveryTv.setTextColor(FarmersToggle.super.getContext().getResources().getColor(R.color.red));
                    pickUpTv.setTextColor(FarmersToggle.super.getContext().getResources().getColor(R.color.primaryTextColor));
                    listener.onToggleCheckedListener(true);


                }
                else  {
                    pickUpTv.setTextColor(FarmersToggle.super.getContext().getResources().getColor(R.color.red));
                    deliveryTv.setTextColor(FarmersToggle.super.getContext().getResources().getColor(R.color.primaryTextColor));
                    listener.onToggleCheckedListener(false);

                }
            }
        });
    }

    interface ToggleCheckedListener {
        void onToggleCheckedListener(Boolean checked);
    }


}
