package com.farmers.buyers.modules.ratingAndReview.adapter;

import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.modules.inbox.view.MessageListDelegate;
import com.farmers.buyers.modules.inbox.view.MessageListViewHolder;
import com.farmers.buyers.modules.ratingAndReview.view.ReviewListDelegate;
import com.farmers.buyers.modules.ratingAndReview.view.ReviewListViewHolder;
import com.farmers.buyers.storage.CardConstant;

public class ReviewListAdapter extends BaseAdapter {


    public ReviewListAdapter() {
        super();
        this.initDelegate();
    }

    @Override
    public void initDelegate() {
        delegates.put(CardConstant.REVIEW_ITEMS_ADAPTER, new ReviewListDelegate());
    }
}
