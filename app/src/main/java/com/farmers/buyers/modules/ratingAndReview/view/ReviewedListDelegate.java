package com.farmers.buyers.modules.ratingAndReview.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.modules.inbox.view.NotificationListViewHolder;

public class ReviewedListDelegate extends BaseDelegate {

    ReviewedListViewHolder.ReviewedItemClickListener reviewedItemClickListener;

    public ReviewedListDelegate(ReviewedListViewHolder.ReviewedItemClickListener reviewedItemClickListener) {
        this.reviewedItemClickListener = reviewedItemClickListener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ReviewedListViewHolder(parent, reviewedItemClickListener);
    }
}
