package com.farmers.seller.modules.ourOrders.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;

public class PastOrderListViewHolder extends BaseViewHolder {

    CardView messageCard;
    LinearLayout ll_view_details;

    public PastOrderListViewHolder(@NonNull ViewGroup parent, final PastOrderItemClickListener pastOrderItemClickListener) {
        super(Extensions.inflate(parent, R.layout.past_order_list_item_layout));
        messageCard = itemView.findViewById(R.id.message_item_card);
        ll_view_details = itemView.findViewById(R.id.ll_view_details);

        messageCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pastOrderItemClickListener.onPastOrderItemClicked(getAdapterPosition());
            }
        });

        ll_view_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pastOrderItemClickListener.onPastOrderItemClicked(getAdapterPosition());
            }
        });

    }

    @Override
    public void bindView(RecyclerViewListItem items) {
    }

    public interface PastOrderItemClickListener {
        void onPastOrderItemClicked(int position);
    }
}
