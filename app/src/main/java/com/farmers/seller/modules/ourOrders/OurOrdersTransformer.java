package com.farmers.seller.modules.ourOrders;

import com.farmers.buyers.modules.ratingAndReview.model.ReviewListItem;
import com.farmers.buyers.modules.ratingAndReview.model.ReviewedListItem;
import com.farmers.seller.modules.ourOrders.model.OngoingOrderListItem;
import com.farmers.seller.modules.ourOrders.model.OurOrderListItem;
import com.farmers.seller.modules.ourOrders.model.PastOrderListItem;

import java.util.ArrayList;
import java.util.List;

public class OurOrdersTransformer {

    public static List<OurOrderListItem> getOurOrderList() {
        List<OurOrderListItem> item = new ArrayList<>();
        item.add(new OurOrderListItem());
        item.add(new OurOrderListItem());
        item.add(new OurOrderListItem());
        item.add(new OurOrderListItem());
        item.add(new OurOrderListItem());
        item.add(new OurOrderListItem());
        return item;
    }

    public static List<OngoingOrderListItem> getOngoingOrderList() {
        List<OngoingOrderListItem> item = new ArrayList<>();
        item.add(new OngoingOrderListItem());
        item.add(new OngoingOrderListItem());
        item.add(new OngoingOrderListItem());
        item.add(new OngoingOrderListItem());
        item.add(new OngoingOrderListItem());
        item.add(new OngoingOrderListItem());
        return item;
    }

    public static List<PastOrderListItem> getPastOrderList() {
        List<PastOrderListItem> item = new ArrayList<>();
        item.add(new PastOrderListItem());
        item.add(new PastOrderListItem());
        item.add(new PastOrderListItem());
        item.add(new PastOrderListItem());
        item.add(new PastOrderListItem());
        item.add(new PastOrderListItem());
        return item;
    }

    public static List<ReviewedListItem> getReviewedList() {
        List<ReviewedListItem> item = new ArrayList<>();
        item.add(new ReviewedListItem("", "", "", 2, "", false));
        item.add(new ReviewedListItem("", "", "", 1, "", false));
        item.add(new ReviewedListItem("", "", "", 0, "", true));
        item.add(new ReviewedListItem("", "", "", 3, "", true));
        item.add(new ReviewedListItem("", "", "", 0, "", true));
        item.add(new ReviewedListItem("", "", "", 2, "", true));
        return item;
    }
}
