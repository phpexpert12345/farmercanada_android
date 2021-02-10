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

public class OurOrderListViewHolder extends BaseViewHolder {

    CardView messageCard;
    LinearLayout ll_order_accept, ll_order_reject;

    public OurOrderListViewHolder(@NonNull ViewGroup parent, final OurOrderItemClickListener ourOrderItemClickListener) {
        super(Extensions.inflate(parent, R.layout.our_order_list_item_layout));
        //messageCard = itemView.findViewById(R.id.message_item_card);

        ll_order_accept = itemView.findViewById(R.id.ll_order_accept);
        ll_order_reject = itemView.findViewById(R.id.ll_order_reject);

      /*  messageCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ourOrderItemClickListener.onOurOrderItemClicked(getAdapterPosition());
            }
        });*/

        ll_order_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ourOrderItemClickListener.onOurOrderItemClicked(getAdapterPosition());
            }
        });
        ll_order_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ourOrderItemClickListener.onOurOrderItemClicked(getAdapterPosition());
            }
        });
    }

    @Override
    public void bindView(RecyclerViewListItem items) {
    }

    public interface OurOrderItemClickListener {
        void onOurOrderItemClicked(int position);
    }
}
