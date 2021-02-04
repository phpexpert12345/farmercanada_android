package com.farmers.buyers.modules.ratingAndReview.view;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;

public class ReviewListViewHolder extends BaseViewHolder {

    CardView messageCard;

    public ReviewListViewHolder(@NonNull ViewGroup parent, final ReviewItemClickListener messageItemClickListener) {
        super(Extensions.inflate(parent, R.layout.review_list_item_layout));
        messageCard = itemView.findViewById(R.id.message_item_card);

        messageCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                messageItemClickListener.onReviewItemClicked(getAdapterPosition());
            }
        });
    }

    @Override
    public void bindView(RecyclerViewListItem items) {
    }

    public interface ReviewItemClickListener {
        void onReviewItemClicked(int position);
    }
}
