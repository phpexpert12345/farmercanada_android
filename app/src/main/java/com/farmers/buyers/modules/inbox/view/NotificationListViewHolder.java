package com.farmers.buyers.modules.inbox.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.inbox.model.NotificationListItems;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * created by Mohammad Sajjad
 * on 01-02-2021 at 17:34
 * mohammadsajjad679@gmail.com
 */

public class NotificationListViewHolder extends BaseViewHolder {

    CircleImageView image;
    TextView notificationNameTv;
    TextView orderNumberTv;
    TextView orderStatusTv;
    TextView orderDateTv;
    RelativeLayout notificationItemLayout;


    public NotificationListViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.notification_item_layout));

        image = itemView.findViewById(R.id.notification_list_item_image);
        notificationNameTv = itemView.findViewById(R.id.notification_item_order_num_tv);
        orderNumberTv = itemView.findViewById(R.id.notification_list_item_name_tv);
        orderStatusTv = itemView.findViewById(R.id.notification_list_item_status_tv);
        orderDateTv = itemView.findViewById(R.id.notification_list_item_date_tv);
        notificationItemLayout = itemView.findViewById(R.id.notification_item_layout);
    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        NotificationListItems item = (NotificationListItems) items;

        switch (item.getOrderStatus()) {
            case 0: {
                orderStatusTv.setText("Not Accepted");
                orderStatusTv.setTextColor(itemView.getContext().getResources().getColor(R.color.red));
                break;
            }
            case 1: {
                orderStatusTv.setText("Order Accepted");
                orderStatusTv.setTextColor(itemView.getContext().getResources().getColor(R.color.green));
                break;
            }
            case 2: {
                orderStatusTv.setText("Order Placed");
                orderStatusTv.setTextColor(itemView.getContext().getResources().getColor(R.color.orange));

                break;
            }
            case 3: {
                orderStatusTv.setText("Order Delivered");
                orderStatusTv.setTextColor(itemView.getContext().getResources().getColor(R.color.primary_button_color));
                break;
            }

        }

        if (!item.getRead()) {
            notificationItemLayout.setBackground(itemView.getContext().getResources().getDrawable(R.drawable.light_red_border_transparent_gray_bg));
        }
        else {
            notificationItemLayout.setBackground(null);
        }

    }
}
