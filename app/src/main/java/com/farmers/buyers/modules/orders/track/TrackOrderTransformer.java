package com.farmers.buyers.modules.orders.track;

import com.farmers.buyers.modules.orders.track.model.TrackOrderHeaderItems;
import com.farmers.buyers.modules.orders.track.model.TrackOrderItemList;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 03-02-2021 at 14:37
 * mohammadsajjad679@gmail.com
 */

public class TrackOrderTransformer {

    public static List<TrackOrderItemList> getTrackOrder() {
        List<TrackOrderItemList> item = new ArrayList<>();
        item.add(new TrackOrderItemList());
        item.add(new TrackOrderItemList());
        item.add(new TrackOrderItemList());
        item.add(new TrackOrderItemList());
        item.add(new TrackOrderItemList());
        item.add(new TrackOrderItemList());
        return item;
    }

    public static TrackOrderHeaderItems getTackOrderHeader() {
        return new TrackOrderHeaderItems("kin's Farm Market", "12-02-2020 10:30 AM", "#7338937", "Tomorrow, 10 AM - 3 PM", "10 items", "$ 155.80", true, 1);


    }
}
