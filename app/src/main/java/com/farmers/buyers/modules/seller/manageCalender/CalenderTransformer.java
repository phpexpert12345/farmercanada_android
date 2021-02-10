package com.farmers.buyers.modules.seller.manageCalender;

import com.farmers.buyers.modules.seller.manageCalender.model.CalenderItems;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 10-02-2021 at 23:29
 * mohammadsajjad679@gmail.com
 */

public class CalenderTransformer {

    public static List<CalenderItems> getCalenderItems() {
        List<CalenderItems> items = new ArrayList<>();
        items.add(new CalenderItems("S"));
        items.add(new CalenderItems("M"));
        items.add(new CalenderItems("T"));
        items.add(new CalenderItems("W"));
        items.add(new CalenderItems("T"));
        items.add(new CalenderItems("F"));
        items.add(new CalenderItems("S"));
        return items;
    }
}
