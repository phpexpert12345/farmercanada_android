package com.farmers.buyers.modules.seller.manageCalender.view;

import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.seller.manageCalender.model.CalenderItems;

/**
 * created by Mohammad Sajjad
 * on 10-02-2021 at 22:20
 * mohammadsajjad679@gmail.com
 */

public class CalenderItemViewHolder extends BaseViewHolder {
    TextView titleTv;

    public CalenderItemViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.calender_item_layout));
        titleTv = itemView.findViewById(R.id.calender_item_day_tv);
    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        CalenderItems item = (CalenderItems) items;

        titleTv.setText(item.getTitle());


    }
}
