package com.farmers.buyers.modules.ratingAndReview.customerReview;

import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.modules.ratingAndReview.view.ReviewListDelegate;
import com.farmers.buyers.modules.ratingAndReview.view.ReviewedListDelegate;
import com.farmers.buyers.storage.CardConstant;

/**
 * Created by Mohammad sajjad on 27-02-2021.
 * mohammadsajjad679@gmail.com
 */
public class CustomerReviewListAdapter extends BaseAdapter {

    public CustomerReviewListAdapter() {
        initDelegate();
    }

    @Override
    public void initDelegate() {
        delegates.put(CardConstant.REVIEW_ITEMS_ADAPTER, new ReviewListDelegate());
    }
}
