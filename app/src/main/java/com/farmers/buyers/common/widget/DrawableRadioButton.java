package com.farmers.buyers.common.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.farmers.buyers.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * created by Mohammad Sajjad
 * on 05-02-2021 at 01:16
 * mohammadsajjad679@gmail.com
 */

public class DrawableRadioButton extends LinearLayout {

    private View view;
    private CircleImageView checkImage;
    private CircleImageView unCheckImage;
    private TextView titleTv;
    private Context context;


    public DrawableRadioButton(Context context) {
        super(context);
        this.context = context;
        view = View.inflate(context, R.layout.drawable_radio_button_layout, this);
    }

    public DrawableRadioButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.drawable_radio_button_layout, this);

    }

    public DrawableRadioButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        view = View.inflate(context, R.layout.drawable_radio_button_layout, this);

    }

    public DrawableRadioButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        view = View.inflate(context, R.layout.drawable_radio_button_layout, this);

    }


    public void prepareRadioButton(final DrawableRadioCheckedChangeListener listener) {
        checkImage = view.findViewById(R.id.drawable_radio_check_image);
        unCheckImage = view.findViewById(R.id.drawable_radio_Uncheck_image);


        checkImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDrawableCheckedChange(true);
                checkImage.setVisibility(GONE);
                unCheckImage.setVisibility(VISIBLE);
            }
        });

        unCheckImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDrawableCheckedChange(false);
                checkImage.setVisibility(VISIBLE);
                unCheckImage.setVisibility(GONE);
            }
        });
    }


    public void prepareRadioButton(Drawable checkIcon, Drawable unCheckIcon, String title, final DrawableRadioCheckedChangeListener listener) {
        checkImage = view.findViewById(R.id.drawable_radio_check_image);
        unCheckImage = view.findViewById(R.id.drawable_radio_Uncheck_image);
        titleTv = view.findViewById(R.id.drawable_radio_title_tv);
        if (title != null) {
            titleTv.setText(title);
            titleTv.setVisibility(VISIBLE);
        } else {
            titleTv.setVisibility(GONE);
        }

        checkImage.setImageDrawable(checkIcon);
        unCheckImage.setImageDrawable(unCheckIcon);

        checkImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDrawableCheckedChange(true);
                checkImage.setVisibility(GONE);
                unCheckImage.setVisibility(VISIBLE);
            }
        });

        unCheckImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDrawableCheckedChange(false);
                checkImage.setVisibility(VISIBLE);
                unCheckImage.setVisibility(GONE);
            }
        });
    }

    public interface DrawableRadioCheckedChangeListener {
        void onDrawableCheckedChange(Boolean isChecked);
    }
}
