package com.farmers.buyers.common.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.common.model.SimpleTitleItem;
import com.farmers.buyers.common.model.SingleTextItem;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;

/**
 * created by Mohammad Sajjad
 * on 11-02-2021 at 11:01
 * mohammadsajjad679@gmail.com
 */

public class SingleItemViewHolder extends BaseViewHolder {

     private TextView titleTv;
    public SingleItemViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.single_text_item_layout));
            titleTv = itemView.findViewById(R.id.simple_title_name_tv);
    }

    @Override
    public void bindView(RecyclerViewListItem items) {

             SingleTextItem item = ((SingleTextItem)items);

             titleTv.setText(item.getTitle());
    }
}
