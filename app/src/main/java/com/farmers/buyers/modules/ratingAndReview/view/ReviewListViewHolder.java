package com.farmers.buyers.modules.ratingAndReview.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.ratingAndReview.model.reviewAndRating.ReviewList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReviewListViewHolder extends BaseViewHolder {

    CardView messageCard;
    CardView reviewed_item_card;
    TextView comment_tv;
    TextView farm_name_tv,message_list_time_tv,order_number;
    RatingBar myRatingBar;
    CircleImageView message_list_item_image;


    public ReviewListViewHolder(@NonNull ViewGroup parent, final ReviewItemClickListener messageItemClickListener) {
        super(Extensions.inflate(parent, R.layout.review_list_item_layout));
        messageCard = itemView.findViewById(R.id.message_item_card);
        message_list_time_tv = itemView.findViewById(R.id.message_list_time_tv);
        farm_name_tv = itemView.findViewById(R.id.farm_name_tv);
        comment_tv = itemView.findViewById(R.id.comment_tv);
        myRatingBar = itemView.findViewById(R.id.myRatingBar);
        message_list_item_image = itemView.findViewById(R.id.message_list_item_image);
        order_number = itemView.findViewById(R.id.order_number);
        messageCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                messageItemClickListener.onReviewItemClicked(getAdapterPosition());
            }
        });
    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        ReviewList item = (ReviewList) items;
        message_list_time_tv.setText(item.getCreatedDate());
        farm_name_tv.setText(item.getFarmName());
        comment_tv.setText(item.getComment());
        myRatingBar.setRating(Float.parseFloat(item.getTotalRating()));
        order_number.setText(item.getOrderNumber());
        Glide.with(itemView.getContext()).load(item.getFarmLogo()).into(message_list_item_image);

    }

    public interface ReviewItemClickListener {
        void onReviewItemClicked(int position);
    }
}
