package com.farmers.seller.modules.broadcastMessage.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;

public class BroadcastMessageListViewHolder extends BaseViewHolder {

    CardView messageCard;
    LinearLayout ll_order_accept, ll_order_reject;

    public BroadcastMessageListViewHolder(@NonNull ViewGroup parent, final BroadcastItemClickListener broadcastItemClickListener) {
        super(Extensions.inflate(parent, R.layout.broadcast_message_list_item_layout));
        messageCard = itemView.findViewById(R.id.message_item_card);

        messageCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                broadcastItemClickListener.onBroadcastItemClicked(getAdapterPosition());
            }
        });
    }

    @Override
    public void bindView(RecyclerViewListItem items) {
    }

    public interface BroadcastItemClickListener {
        void onBroadcastItemClicked(int position);
    }
}
