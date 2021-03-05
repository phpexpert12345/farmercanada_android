package com.farmers.buyers.modules.orders.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.orders.model.SubOrdersListItem;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * created by Mohammad Sajjad
 * on 03-02-2021 at 01:40
 * mohammadsajjad679@gmail.com
 */

public class SubOrderItemViewHolder extends BaseViewHolder {
    TextView tv_order_name, sub_order_item_time_tv, tv_order_number, tv_order_amount, statusTv;
    CardView subOrderCard;
    SubOrdersListItem itemData;
    Context context;
    CircleImageView  sub_order_item_farm_image;
    public SubOrderItemViewHolder(@NonNull ViewGroup parent, final SubOrderItemClickListener subOrderItemClickListener) {

        super(Extensions.inflate(parent, R.layout.sub_order_item_layout));
        context=parent.getContext();
        statusTv = itemView.findViewById(R.id.sub_order_item_status_tv);
        tv_order_name = itemView.findViewById(R.id.tv_order_name);
        sub_order_item_time_tv = itemView.findViewById(R.id.sub_order_item_time_tv);
        tv_order_number = itemView.findViewById(R.id.tv_order_number);
        tv_order_amount = itemView.findViewById(R.id.tv_order_amount);
        sub_order_item_farm_image=itemView.findViewById(R.id.sub_order_item_farm_image);

        subOrderCard = itemView.findViewById(R.id.sub_ordet_item_card);

        subOrderCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subOrderItemClickListener.onSubOrderItemClicked(itemData);
            }
        });

    }

    @Override
    public void bindView(RecyclerViewListItem items) {

        SubOrdersListItem item = (SubOrdersListItem) items;
        itemData = item;
        tv_order_name.setText(item.getTitle());
        sub_order_item_time_tv.setText(item.getTime());
        tv_order_number.setText(item.getOrderId());
        tv_order_amount.setText(item.getAmount());
        Glide.with(context).load(item.getImageUri()).placeholder(R.drawable.logo).into(sub_order_item_farm_image);
      /*  switch (item.getStatus()) {
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
        }*/

    }

    public interface SubOrderItemClickListener {
        void onSubOrderItemClicked(SubOrdersListItem item);
    }
}
