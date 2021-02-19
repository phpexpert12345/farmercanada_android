package com.farmers.seller.modules.ourOrders;

import com.farmers.buyers.R;
import com.farmers.buyers.modules.ratingAndReview.model.ReviewListItem;
import com.farmers.buyers.modules.ratingAndReview.model.ReviewedListItem;
import com.farmers.seller.modules.ourOrders.model.OngoingOrderListItem;
import com.farmers.seller.modules.ourOrders.model.OurOrderListItem;
import com.farmers.seller.modules.ourOrders.model.PastOrderListItem;
import com.farmers.seller.modules.ourOrders.model.SideMenuListItem;

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

    public static List<SideMenuListItem> getReviewedList() {
        List<SideMenuListItem> item = new ArrayList<>();
        item.add(new SideMenuListItem("My Product", R.mipmap.my_product_icon, 1));
        item.add(new SideMenuListItem("My Orders", R.mipmap.shopping_bag_icon, 2));
        item.add(new SideMenuListItem("My Earning", R.mipmap.earning_icon, 3));
        item.add(new SideMenuListItem("Broadcast Message", R.mipmap.broadcast_icon, 4));
        item.add(new SideMenuListItem("Manage Coupon", R.mipmap.coupon_icon, 5));
        item.add(new SideMenuListItem("Manage Calender", R.mipmap.calender_icon, 6));
        item.add(new SideMenuListItem("My Profile", R.mipmap.profile_icon, 7));
        item.add(new SideMenuListItem("Working Hours", R.mipmap.profile_icon, 8));
        return item;
    }
}
