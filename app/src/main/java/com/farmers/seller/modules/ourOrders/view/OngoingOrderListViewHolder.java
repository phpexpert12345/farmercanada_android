package com.farmers.seller.modules.ourOrders.view;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;

public class OngoingOrderListViewHolder extends BaseViewHolder {

    CardView messageCard;

    public OngoingOrderListViewHolder(@NonNull ViewGroup parent, final OngoingOrderItemClickListener ongoingOrderItemClickListener) {
        super(Extensions.inflate(parent, R.layout.ongoing_order_list_item_layout));
        messageCard = itemView.findViewById(R.id.message_item_card);

        messageCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ongoingOrderItemClickListener.onOngoingOrderItemClicked(getAdapterPosition());
            }
        });
    }

    @Override
    public void bindView(RecyclerViewListItem items) {
    }

    public interface OngoingOrderItemClickListener {
        void onOngoingOrderItemClicked(int position);
    }
}
