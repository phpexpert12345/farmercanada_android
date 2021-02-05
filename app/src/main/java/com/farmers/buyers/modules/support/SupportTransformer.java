package com.farmers.buyers.modules.support;

import com.farmers.buyers.modules.ratingAndReview.model.ReviewListItem;
import com.farmers.buyers.modules.support.model.SupportListItem;

import java.util.ArrayList;
import java.util.List;

public class SupportTransformer {

    public static List<SupportListItem> getSupportList() {
        List<SupportListItem> item = new ArrayList<>();
        item.add(new SupportListItem());
        item.add(new SupportListItem());
        item.add(new SupportListItem());
        item.add(new SupportListItem());
        item.add(new SupportListItem());
        item.add(new SupportListItem());
        return item;
    }
}
