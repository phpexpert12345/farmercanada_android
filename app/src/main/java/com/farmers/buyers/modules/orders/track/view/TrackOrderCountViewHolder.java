package com.farmers.buyers.modules.orders.track.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.orders.track.model.TrackOrderCountItem;

/**
 * created by Mohammad Sajjad
 * on 09-02-2021 at 12:39
 * mohammadsajjad679@gmail.com
 */

public class TrackOrderCountViewHolder extends BaseViewHolder {
    TextView  txt_total_count,txt_items;



    public TrackOrderCountViewHolder(@NonNull ViewGroup parent) {

        super(Extensions.inflate(parent, R.layout.track_order_count_holder_layout));
        txt_total_count=itemView.findViewById(R.id.txt_total_count);
        txt_items=itemView.findViewById(R.id.txt_items);
    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        TrackOrderCountItem trackOrderCountItem= (TrackOrderCountItem) items;
        txt_items.setText(trackOrderCountItem.count+" items");
        txt_total_count.setText("$"+String.format("%.2f",Double.parseDouble(trackOrderCountItem.total_price)));
    }
}
