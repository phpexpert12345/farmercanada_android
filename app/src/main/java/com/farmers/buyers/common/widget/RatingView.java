package com.farmers.buyers.common.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.farmers.buyers.R;

/**
 * created by Mohammad Sajjad
 * on 31-01-2021 at 02:06
 * mohammadsajjad679@gmail.com
 */

public class RatingView extends LinearLayout {
    private View view;
    private RatingBar ratingBar;

    public RatingView(Context context) {
        super(context);
    }

    public RatingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.rating_view_layout, this);

    }

    public RatingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        view = View.inflate(context, R.layout.rating_view_layout, this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RatingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        view = View.inflate(context, R.layout.rating_view_layout, this);
    }

    public void init(RatingConfig ratingConfig) {
        ratingBar = view.findViewById(R.id.rating_view);
        ratingBar.setNumStars(5);
        ratingBar.setRating(ratingConfig.rating);
        ratingBar.setStepSize(1);

        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(0).setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
//        stars.getDrawable(1).setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
    }

    static class RatingConfig {
        int rating;

        public RatingConfig(int rating) {
            this.rating = rating;
        }
    }
}
