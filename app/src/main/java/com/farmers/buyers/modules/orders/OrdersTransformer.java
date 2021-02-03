package com.farmers.buyers.modules.orders;

import com.farmers.buyers.modules.orders.model.OrderListItem;

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
}
