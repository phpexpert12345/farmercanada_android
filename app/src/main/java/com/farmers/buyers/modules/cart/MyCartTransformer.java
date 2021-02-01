package com.farmers.buyers.modules.cart;

import com.farmers.buyers.R;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.cart.myCart.model.MyCartItem;
import com.farmers.buyers.modules.cart.order.model.PlaceOrderSlotItem;
import com.farmers.buyers.modules.cart.order.model.PlaceOrderSlotListItems;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 29-01-2021 at 18:17
 * mohammadsajjad679@gmail.com
 */

public class MyCartTransformer {

    public static List<MyCartItem> getMyCartItem() {
        List<MyCartItem> items = new ArrayList<>();
        items.add(new MyCartItem(R.drawable.cart_one, "", "", ""));
        items.add(new MyCartItem(R.drawable.cart_two, "", "", ""));
        items.add(new MyCartItem(R.drawable.cart_three, "", "", ""));
        return items;
    }

    public static PlaceOrderSlotListItems getPlaceOrderSlot() {
        List<RecyclerViewListItem> item = new ArrayList<>();
        item.add(new PlaceOrderSlotItem("S", "31 Jan"));
        item.add(new PlaceOrderSlotItem("M", "2 Feb"));
        item.add(new PlaceOrderSlotItem("T","3 Feb"));
        item.add(new PlaceOrderSlotItem("W","4 Feb"));
        item.add(new PlaceOrderSlotItem("T", "5 Feb"));
        item.add(new PlaceOrderSlotItem("F", "6 Feb"));
        item.add(new PlaceOrderSlotItem("S", "7 Feb"));
        return new PlaceOrderSlotListItems(item);
    }
}
