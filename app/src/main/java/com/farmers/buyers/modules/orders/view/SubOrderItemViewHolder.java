package com.farmers.buyers.modules.orders.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.orders.model.SubOrdersListItem;

/**
 * created by Mohammad Sajjad
 * on 03-02-2021 at 01:40
 * mohammadsajjad679@gmail.com
 */

public class SubOrderItemViewHolder extends BaseViewHolder {
    TextView statusTv;

    public SubOrderItemViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.sub_order_item_layout));
        statusTv = itemView.findViewById(R.id.sub_order_item_status_tv);
    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        SubOrdersListItem item = (SubOrdersListItem) items;

        switch (item.getStatus()) {
            case 0 : {
                statusTv.setText("Pending");
                break;
            }
            case 1: {
                statusTv.setText("Accepted");

                break;
            }

            case 2: {
                statusTv.setText("Rejected");

                break;
            }
        }

    }


}
