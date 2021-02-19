package com.farmers.buyers.modules.orders.view;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.orders.model.OrderListItem;

/**
 * created by Mohammad Sajjad
 * on 02-02-2021 at 17:57
 * mohammadsajjad679@gmail.com
 */

public class OrdersItemViewHolder extends BaseViewHolder {
    CardView orderItemCard;

    public OrdersItemViewHolder(@NonNull ViewGroup parent, final OrdersItemClickListener ordersItemClickListener) {
        super(Extensions.inflate(parent, R.layout.orders_item_layout));
        orderItemCard = itemView.findViewById(R.id.order_item_card);

        orderItemCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ordersItemClickListener.onOrderItemClicked(getAdapterPosition());
            }
        });

    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        OrderListItem item = (OrderListItem) items;


    }

    public interface OrdersItemClickListener {
        void onOrderItemClicked(int position);
    }
}
