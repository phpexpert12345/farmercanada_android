package com.farmers.buyers.core;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * created by Mohammad Sajjad
 * on 25-01-2021 at 10:28
 * mohammadsajjad679@gmail.com
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {


    public BaseViewHolder(@NonNull View view) {
        super(view);
    }

    public abstract void bindView(RecyclerViewListItem items);
    }


