package com.farmers.buyers.modules.ratingAndReview.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.modules.inbox.view.MessageListViewHolder;

public class ReviewListDelegate extends BaseDelegate {


    public ReviewListDelegate() {
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ReviewListViewHolder(parent);
    }
}
