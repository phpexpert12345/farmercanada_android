package com.farmers.buyers.common.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.common.model.SimpleTitleItem;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;

/**
 * created by Mohammad Sajjad
 * on 26-01-2021 at 12:45
 * mohammadsajjad679@gmail.com
 */

public class SimpleTitleViewHolder extends BaseViewHolder {
    private TextView titleTv;

    public SimpleTitleViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.simple_title_item_layout));
        titleTv = itemView.findViewById(R.id.simple_title_name_tv);
    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        SimpleTitleItem item = ((SimpleTitleItem)items);
        titleTv.setText(item.getTitle());
    }
}
