package com.farmers.buyers.modules.ratingAndReview;

import com.farmers.buyers.modules.ratingAndReview.customerReview.model.CustomerReviewListApiModel;
import com.farmers.buyers.modules.ratingAndReview.model.FarmReviewListApiModel;
import com.farmers.buyers.modules.ratingAndReview.model.FarmReviewedListApiModel;
import com.farmers.buyers.modules.ratingAndReview.model.ReviewListItem;
import com.farmers.buyers.modules.ratingAndReview.model.ReviewedListItem;
import com.farmers.buyers.modules.ratingAndReview.model.reviewAndRating.ReviewList;

import java.util.ArrayList;
import java.util.List;

public class ReviewTransfarmer {

  /*  public static List<ReviewListItem> getReviewList() {
        List<ReviewListItem> item = new ArrayList<>();
        item.add(new ReviewListItem());
        item.add(new ReviewListItem());
        item.add(new ReviewListItem());
        item.add(new ReviewListItem());
        item.add(new ReviewListItem());
        item.add(new ReviewListItem());
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
    }*/

    public static List<ReviewListItem> transformApiModelToCustomerReviewList(List<CustomerReviewListApiModel.ReviewList> apiData) {
        List<ReviewListItem> items = new ArrayList<>();
        for (int i = 0 ; i< apiData.size() ; i++) {
            CustomerReviewListApiModel.ReviewList data = apiData.get(i);
            items.add(new ReviewListItem(data.getFarmName(), data.getFarmLogo(), data.getReviewId(), data.getTotalRating(), data.getComment(), data.getCreatedDate(), data.getOrderNumber()));
        }
        return items;
    }

    public static List<ReviewListItem> transformApiModelToFarmReviewList(List<FarmReviewListApiModel.FarmReviewList> apiData) {
        List<ReviewListItem> items = new ArrayList<>();
        for (int i = 0 ; i< apiData.size() ; i++) {
            FarmReviewListApiModel.FarmReviewList data = apiData.get(i);
            items.add(new ReviewListItem(data.getFarmName(), data.getFarmLogo(), data.getReviewId(), data.getTotalRating(), data.getComment(), data.getCreatedDate(), data.getOrderNumber()));
        }
        return items;
    }

    public static List<ReviewListItem> transformApiModelToFarmReviewedItems(List<FarmReviewedListApiModel.FarmReviewList> apiData) {
        List<ReviewListItem> items = new ArrayList<>();
        for (int i = 0 ; i< apiData.size() ; i++) {
            FarmReviewedListApiModel.FarmReviewList data = apiData.get(i);
            items.add(new ReviewListItem(data.getFarmName(), data.getFarmLogo(), data.getReviewId(), data.getTotalRating(), data.getComment(), data.getCreatedDate(), data.getOrderNumber()));
        }
        return items;
    }
}
