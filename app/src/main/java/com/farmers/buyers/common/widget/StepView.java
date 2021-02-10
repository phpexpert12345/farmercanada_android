package com.farmers.buyers.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * created by Mohammad Sajjad
 * on 09-02-2021 at 10:19
 * mohammadsajjad679@gmail.com
 */

public class StepView extends LinearLayout {
    private View view;


    public StepView(Context context) {
        super(context);
    }

    public StepView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public StepView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
