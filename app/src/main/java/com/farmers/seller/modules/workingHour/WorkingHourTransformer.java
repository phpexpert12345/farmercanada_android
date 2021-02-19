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
        item.add(new StoreTimeListItem(1, "5 Minute"));
        item.add(new StoreTimeListItem(2, "10 Minute"));
        item.add(new StoreTimeListItem(3, "15 Minute"));
        item.add(new StoreTimeListItem(4, "20 Minute"));
        item.add(new StoreTimeListItem(5, "25 Minute"));
        item.add(new StoreTimeListItem(6, "30 Minute"));
        item.add(new StoreTimeListItem(7, "35 Minute"));
        item.add(new StoreTimeListItem(8, "40 Minute"));
        item.add(new StoreTimeListItem(9, "45 Minute"));
        item.add(new StoreTimeListItem(10, "50 Minute"));
        item.add(new StoreTimeListItem(11, "1 Hour"));
        item.add(new StoreTimeListItem(12, "2 Hour"));
        return item;
    }

    public static List<WeekDayListItem> getWeekDaysList() {

        List<DropDownData> dropDownDataList = new ArrayList<>();
        dropDownDataList.add(new DropDownData(1, "1 Order"));
        dropDownDataList.add(new DropDownData(3, "3 Order"));
        dropDownDataList.add(new DropDownData(4, "4 Order"));
        dropDownDataList.add(new DropDownData(5, "5 Order"));
        dropDownDataList.add(new DropDownData(6, "6 Order"));
        dropDownDataList.add(new DropDownData(7, "7 Order"));
        dropDownDataList.add(new DropDownData(8, "8 Order"));
        dropDownDataList.add(new DropDownData(9, "9 Order"));
        dropDownDataList.add(new DropDownData(10, "10 Order"));

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
        item.add(new PickupTimeListItem(1, "5 Minute"));
        item.add(new PickupTimeListItem(2, "10 Minute"));
        item.add(new PickupTimeListItem(3, "15 Minute"));
        item.add(new PickupTimeListItem(4, "20 Minute"));
        item.add(new PickupTimeListItem(5, "25 Minute"));
        item.add(new PickupTimeListItem(6, "30 Minute"));
        item.add(new PickupTimeListItem(7, "35 Minute"));
        item.add(new PickupTimeListItem(8, "40 Minute"));
        item.add(new PickupTimeListItem(9, "45 Minute"));
        item.add(new PickupTimeListItem(10, "50 Minute"));
        item.add(new PickupTimeListItem(11, "1 Hour"));
        item.add(new PickupTimeListItem(12, "2 Hour"));
        return item;
    }

    public static List<PickupWeekDayListItem> getPickupWeekDaysList() {

        List<PickupDropDownData> pickupDropDownData = new ArrayList<>();
        pickupDropDownData.add(new PickupDropDownData(1, "1 Order"));
        pickupDropDownData.add(new PickupDropDownData(3, "3 Order"));
        pickupDropDownData.add(new PickupDropDownData(4, "4 Order"));
        pickupDropDownData.add(new PickupDropDownData(5, "5 Order"));
        pickupDropDownData.add(new PickupDropDownData(6, "6 Order"));
        pickupDropDownData.add(new PickupDropDownData(7, "7 Order"));
        pickupDropDownData.add(new PickupDropDownData(8, "8 Order"));
        pickupDropDownData.add(new PickupDropDownData(9, "9 Order"));
        pickupDropDownData.add(new PickupDropDownData(10, "10 Order"));

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
