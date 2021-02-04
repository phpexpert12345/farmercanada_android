package com.farmers.buyers.modules.orders;

import com.farmers.buyers.R;
import com.farmers.buyers.modules.orders.model.OrderListItem;
import com.farmers.buyers.modules.orders.model.SubOrdersListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 02-02-2021 at 18:11
 * mohammadsajjad679@gmail.com
 */

public class OrdersTransformer {

    public static List<OrderListItem> getOrders() {
        List<OrderListItem> items = new ArrayList<>();
        items.add(new OrderListItem("Pomegranate (Name)", "#23789348", "2:30 pm"));
        items.add(new OrderListItem("Pomegranate (Name)", "#23789348", "2:30 pm"));
        return items;
    }

    public static List<OrderListItem> getYesterdayOrders() {
        List<OrderListItem> items = new ArrayList<>();
        items.add(new OrderListItem("Pomegranate (Name)", "#23789348", "2:30 pm"));
        items.add(new OrderListItem("Pomegranate (Name)", "#23789348", "2:30 pm"));
        items.add(new OrderListItem("Pomegranate (Name)", "#23789348", "2:30 pm"));
        items.add(new OrderListItem("Pomegranate (Name)", "#23789348", "2:30 pm"));
        items.add(new OrderListItem("Pomegranate (Name)", "#23789348", "2:30 pm"));
        return items;
    }

    public static List<SubOrdersListItem> getPendingItems() {
        List<SubOrdersListItem> item = new ArrayList<>();
        item.add(new SubOrdersListItem("Kin's Farm Market", "#7338937", "10:30 am", "$ 155.80", 0, R.drawable.farm_icon_image));
        item.add(new SubOrdersListItem("Kin's Farm Market", "#7338937", "10:30 am", "$ 155.80", 0, R.drawable.farm_icon_image));
        item.add(new SubOrdersListItem("Kin's Farm Market", "#7338937", "10:30 am", "$ 155.80", 0, R.drawable.farm_icon_image));
        item.add(new SubOrdersListItem("Kin's Farm Market", "#7338937", "10:30 am", "$ 155.80", 0, R.drawable.farm_icon_image));
        item.add(new SubOrdersListItem("Kin's Farm Market", "#7338937", "10:30 am", "$ 155.80", 0, R.drawable.farm_icon_image));
        item.add(new SubOrdersListItem("Kin's Farm Market", "#7338937", "10:30 am", "$ 155.80", 0, R.drawable.farm_icon_image));
        item.add(new SubOrdersListItem("Kin's Farm Market", "#7338937", "10:30 am", "$ 155.80", 0, R.drawable.farm_icon_image));
        return item;
    }

    public static List<SubOrdersListItem> getAcceptedItems() {
        List<SubOrdersListItem> item = new ArrayList<>();
        item.add(new SubOrdersListItem("Kin's Farm Market", "#7338937", "10:30 am", "$ 155.80", 1, R.drawable.farm_icon_image));
        item.add(new SubOrdersListItem("Kin's Farm Market", "#7338937", "10:30 am", "$ 155.80", 1, R.drawable.farm_icon_image));
        item.add(new SubOrdersListItem("Kin's Farm Market", "#7338937", "10:30 am", "$ 155.80", 1, R.drawable.farm_icon_image));
        item.add(new SubOrdersListItem("Kin's Farm Market", "#7338937", "10:30 am", "$ 155.80", 1, R.drawable.farm_icon_image));
        item.add(new SubOrdersListItem("Kin's Farm Market", "#7338937", "10:30 am", "$ 155.80", 1, R.drawable.farm_icon_image));
        item.add(new SubOrdersListItem("Kin's Farm Market", "#7338937", "10:30 am", "$ 155.80", 1, R.drawable.farm_icon_image));
        item.add(new SubOrdersListItem("Kin's Farm Market", "#7338937", "10:30 am", "$ 155.80", 1, R.drawable.farm_icon_image));
        return item;
    }

    public static List<SubOrdersListItem> getRejectedItems() {
        List<SubOrdersListItem> item = new ArrayList<>();
        item.add(new SubOrdersListItem("Kin's Farm Market", "#7338937", "10:30 am", "$ 155.80", 2, R.drawable.farm_icon_image));
        item.add(new SubOrdersListItem("Kin's Farm Market", "#7338937", "10:30 am", "$ 155.80", 2, R.drawable.farm_icon_image));
        item.add(new SubOrdersListItem("Kin's Farm Market", "#7338937", "10:30 am", "$ 155.80", 2, R.drawable.farm_icon_image));
        item.add(new SubOrdersListItem("Kin's Farm Market", "#7338937", "10:30 am", "$ 155.80", 2, R.drawable.farm_icon_image));
        item.add(new SubOrdersListItem("Kin's Farm Market", "#7338937", "10:30 am", "$ 155.80", 2, R.drawable.farm_icon_image));
        item.add(new SubOrdersListItem("Kin's Farm Market", "#7338937", "10:30 am", "$ 155.80", 2, R.drawable.farm_icon_image));
        item.add(new SubOrdersListItem("Kin's Farm Market", "#7338937", "10:30 am", "$ 155.80", 2, R.drawable.farm_icon_image));
        return item;
    }


}
