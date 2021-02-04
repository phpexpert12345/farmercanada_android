package com.farmers.buyers.modules.ratingAndReview.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.inbox.model.NotificationListItems;
import com.farmers.buyers.modules.ratingAndReview.model.ReviewedListItem;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReviewedListViewHolder extends BaseViewHolder {

    CardView reviewed_item_card;
 /*   CircleImageView image;
    TextView notificationNameTv;
    TextView orderNumberTv;
    TextView orderStatusTv;
    TextView orderDateTv;
    RelativeLayout notificationItemLayout;*/

    public ReviewedListViewHolder(@NonNull ViewGroup parent, final ReviewedListViewHolder.ReviewedItemClickListener reviewedItemClickListener) {
        super(Extensions.inflate(parent, R.layout.reviewed_list_item_layout));
        reviewed_item_card = itemView.findViewById(R.id.reviewed_item_card);

        reviewed_item_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reviewedItemClickListener.onReviewedItemClicked(getAdapterPosition());
            }
        });
       /* image = itemView.findViewById(R.id.notification_list_item_image);
        notificationNameTv = itemView.findViewById(R.id.notification_item_order_num_tv);
        orderNumberTv = itemView.findViewById(R.id.notification_list_item_name_tv);
        orderStatusTv = itemView.findViewById(R.id.notification_list_item_status_tv);
        orderDateTv = itemView.findViewById(R.id.notification_list_item_date_tv);
        notificationItemLayout = itemView.findViewById(R.id.notification_item_layout);*/
    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        ReviewedListItem item = (ReviewedListItem) items;

       /* switch (item.getOrderStatus()) {
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
        } else {
            notificationItemLayout.setBackground(null);
        }*/
    }

    public interface ReviewedItemClickListener {
        void onReviewedItemClicked(int position);
    }
}
