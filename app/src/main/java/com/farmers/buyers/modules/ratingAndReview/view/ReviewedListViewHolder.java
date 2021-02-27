package com.farmers.buyers.modules.ratingAndReview.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.inbox.model.NotificationListItems;
import com.farmers.buyers.modules.ratingAndReview.model.ReviewListItem;
import com.farmers.buyers.modules.ratingAndReview.model.ReviewedListItem;
import com.farmers.buyers.modules.ratingAndReview.model.reviewAndRating.ReviewList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReviewedListViewHolder extends BaseViewHolder {

    CardView reviewed_item_card;
    TextView commentTextView;
    TextView farmName, duration_tv;
    RatingBar ratingBar;
    CircleImageView message_list_item_image;

    public ReviewedListViewHolder(@NonNull ViewGroup parent, final ReviewedListViewHolder.ReviewedItemClickListener reviewedItemClickListener) {
        super(Extensions.inflate(parent, R.layout.reviewed_list_item_layout));
        reviewed_item_card = itemView.findViewById(R.id.reviewed_item_card);
        message_list_item_image = itemView.findViewById(R.id.message_list_item_image);
        commentTextView = itemView.findViewById(R.id.comment_tv);
        farmName = itemView.findViewById(R.id.farm_name_tv);
        duration_tv = itemView.findViewById(R.id.duration_tv);
        ratingBar = itemView.findViewById(R.id.rating_view);

        reviewed_item_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reviewedItemClickListener.onReviewedItemClicked(getAdapterPosition());
            }
        });

    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        ReviewListItem item = (ReviewListItem) items;
        duration_tv.setText(item.getDate());
        farmName.setText(item.getName());
        commentTextView.setText(item.getComment());
        ratingBar.setRating(Float.parseFloat(String.valueOf(item.getGetRating())));
        Glide.with(itemView.getContext()).load(item.getImage()).into(message_list_item_image);


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
