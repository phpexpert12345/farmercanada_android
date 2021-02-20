package com.farmers.buyers.modules.orders.track;

import com.farmers.buyers.modules.address.model.AddressApiModel;
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

    public static TrackOrderHeaderItems getTackOrderHeader(List<AddressApiModel.AddressListData> allOrderList) {
        return new TrackOrderHeaderItems(allOrderList.get(0).getFarm_logo(), allOrderList.get(0).getFarm_name(),
                allOrderList.get(0).getOrder_date() + "," + allOrderList.get(0).getOrder_time(),
                allOrderList.get(0).getOrder_number(),
                allOrderList.get(0).getOrder_time(),
                "10 items",
                "$ " + allOrderList.get(0).getTotal_amount(),
                true, 1);
    }

    public static List<TrackOrderItemList> getTrackOrder(List<AddressApiModel.AllRecordsData> allRecordList) {
        List<TrackOrderItemList> item = new ArrayList<>();
        for (int i = 0; i < allRecordList.size(); i++) {
            item.add(new TrackOrderItemList(allRecordList.get(i).getItem_id(), allRecordList.get(i).getProduct_name(),
                    allRecordList.get(i).getProduct_code(), allRecordList.get(i).getProduct_description(),
                    allRecordList.get(i).getProduct_images(),
                    allRecordList.get(i).getItem_quantity(),
                    allRecordList.get(i).getItem_price(), allRecordList.get(i).getItem_unit(),
                    allRecordList.get(i).getItem_size(), allRecordList.get(i).getItem_note()));
        }
        return item;
    }

    public static TrackOrderHeaderItems getTackOrderHeader() {
        return new TrackOrderHeaderItems("", "kin's Farm Market",
                "12-02-2020 10:30 AM",
                "#7338937",
                "Tomorrow, 10 AM - 3 PM",
                "10 items",
                "$ 155.80",
                true, 1);
    }

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
}
