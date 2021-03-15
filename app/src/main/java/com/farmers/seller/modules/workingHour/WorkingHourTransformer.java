package com.farmers.seller.modules.workingHour;

import com.farmers.buyers.R;
import com.farmers.seller.modules.ourOrders.model.OngoingOrderListItem;
import com.farmers.seller.modules.ourOrders.model.OurOrderListItem;
import com.farmers.seller.modules.ourOrders.model.PastOrderListItem;
import com.farmers.seller.modules.ourOrders.model.SideMenuListItem;
import com.farmers.seller.modules.workingHour.model.DropDownData;
import com.farmers.seller.modules.workingHour.model.PickupDropDownData;
import com.farmers.seller.modules.workingHour.model.PickupTimeListItem;
import com.farmers.seller.modules.workingHour.model.PickupWeekDayListItem;
import com.farmers.seller.modules.workingHour.model.StoreTimeListItem;
import com.farmers.seller.modules.workingHour.model.WeekDayListItem;

import java.util.ArrayList;
import java.util.List;

public class WorkingHourTransformer {

    public static List<StoreTimeListItem> getStoreTimeList() {
        List<StoreTimeListItem> item = new ArrayList<>();
        item.add(new StoreTimeListItem(1, "1 hours"));
        item.add(new StoreTimeListItem(2, "1.30 hours"));
        item.add(new StoreTimeListItem(3, "2 hours"));
        item.add(new StoreTimeListItem(4, "2.30 hours"));
        item.add(new StoreTimeListItem(5, "3 hours"));
        item.add(new StoreTimeListItem(6, "3.30 hours"));
        item.add(new StoreTimeListItem(7, "4 hours"));
        return item;
    }

    public static List<WeekDayListItem> getWeekDaysList() {

        List<DropDownData> dropDownDataList = new ArrayList<>();
        dropDownDataList.add(new DropDownData(5, "5 Order"));
        dropDownDataList.add(new DropDownData(6, "10 Order"));
        dropDownDataList.add(new DropDownData(7, "15 Order"));
        dropDownDataList.add(new DropDownData(8, "20 Order"));
        dropDownDataList.add(new DropDownData(9, "25 Order"));
        dropDownDataList.add(new DropDownData(10, "30 Order"));


        List<WeekDayListItem> item = new ArrayList<>();

        item.add(new WeekDayListItem(1, "Monday", dropDownDataList));
        item.add(new WeekDayListItem(2, "Tuesday", dropDownDataList));
        item.add(new WeekDayListItem(3, "Wednesday", dropDownDataList));
        item.add(new WeekDayListItem(4, "Thursday", dropDownDataList));
        item.add(new WeekDayListItem(5, "Friday", dropDownDataList));
        item.add(new WeekDayListItem(6, "Saturday", dropDownDataList));
        item.add(new WeekDayListItem(7, "Sunday", dropDownDataList));

        return item;
    }

    public static List<PickupTimeListItem> getPickupTimeList() {
        List<PickupTimeListItem> item = new ArrayList<>();
        item.add(new PickupTimeListItem(1, "1 hours"));
        item.add(new PickupTimeListItem(2, "1.30 hours"));
        item.add(new PickupTimeListItem(3, "2 hours"));
        item.add(new PickupTimeListItem(4, "2.30 hours"));
        item.add(new PickupTimeListItem(5, "3 hours"));
        item.add(new PickupTimeListItem(6, "3.30 hours"));
        item.add(new PickupTimeListItem(7, "4 hours"));
        return item;
    }

    public static List<PickupWeekDayListItem> getPickupWeekDaysList() {

        List<PickupDropDownData> pickupDropDownData = new ArrayList<>();
        pickupDropDownData.add(new PickupDropDownData(1, "5 Order"));
        pickupDropDownData.add(new PickupDropDownData(3, "10 Order"));
        pickupDropDownData.add(new PickupDropDownData(4, "15 Order"));
        pickupDropDownData.add(new PickupDropDownData(5, "20 Order"));
        pickupDropDownData.add(new PickupDropDownData(6, "25 Order"));
        pickupDropDownData.add(new PickupDropDownData(6, "30 Order"));

        List<PickupWeekDayListItem> item = new ArrayList<>();

        item.add(new PickupWeekDayListItem(1, "Monday", pickupDropDownData));
        item.add(new PickupWeekDayListItem(2, "Tuesday", pickupDropDownData));
        item.add(new PickupWeekDayListItem(3, "Wednesday", pickupDropDownData));
        item.add(new PickupWeekDayListItem(4, "Thursday", pickupDropDownData));
        item.add(new PickupWeekDayListItem(5, "Friday", pickupDropDownData));
        item.add(new PickupWeekDayListItem(6, "Saturday", pickupDropDownData));
        item.add(new PickupWeekDayListItem(7, "Sunday", pickupDropDownData));

        return item;
    }
}
