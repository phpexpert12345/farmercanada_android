package com.farmers.buyers.modules.support;

import com.farmers.buyers.modules.orders.model.OrderListItem;
import com.farmers.buyers.modules.ratingAndReview.model.ReviewListItem;

import java.util.ArrayList;
import java.util.List;

public class SupportTransformer {

    public static List<ReviewListItem> getSupportList() {
        List<ReviewListItem> item = new ArrayList<>();
        item.add(new ReviewListItem());
        item.add(new ReviewListItem());
        item.add(new ReviewListItem());
        item.add(new ReviewListItem());
        item.add(new ReviewListItem());
        item.add(new ReviewListItem());
        return item;
    }
}
