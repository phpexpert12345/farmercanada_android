package com.farmers.buyers.common.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * created by Mohammad Sajjad
 * on 03-02-2021 at 16:37
 * mohammadsajjad679@gmail.com
 */

public class LinearSpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public LinearSpacesItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;
//        outRect.top    = space;
    }
}