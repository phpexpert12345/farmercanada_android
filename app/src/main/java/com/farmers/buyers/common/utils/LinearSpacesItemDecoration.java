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

    private int spacing;

    public LinearSpacesItemDecoration(int spacing) {
        this.spacing = spacing;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if (parent.getAdapter() != null && parent.getChildLayoutPosition(view) == parent.getAdapter().getItemCount()-1) {
            outRect.bottom = spacing;
            outRect.left = spacing;
            outRect.right = spacing;
            outRect.top = spacing;

        }
    }
}
