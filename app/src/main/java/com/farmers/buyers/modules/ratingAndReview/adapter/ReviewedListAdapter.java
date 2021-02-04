package com.farmers.buyers.modules.ratingAndReview.adapter;

import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.modules.inbox.view.NotificationListDelegate;
import com.farmers.buyers.modules.ratingAndReview.view.ReviewListViewHolder;
import com.farmers.buyers.modules.ratingAndReview.view.ReviewedListDelegate;
import com.farmers.buyers.modules.ratingAndReview.view.ReviewedListViewHolder;
import com.farmers.buyers.storage.CardConstant;

public class ReviewedListAdapter extends BaseAdapter {

    ReviewedListViewHolder.ReviewedItemClickListener reviewedItemClickListener;

    public ReviewedListAdapter(ReviewedListViewHolder.ReviewedItemClickListener reviewedItemClickListener) {
        super();
        this.reviewedItemClickListener = reviewedItemClickListener;
        this.initDelegate();
    }

    @Override
    public void initDelegate() {
        delegates.put(CardConstant.REVIEWED_ITEMS_ADAPTER, new ReviewedListDelegate(reviewedItemClickListener));
    }
}
