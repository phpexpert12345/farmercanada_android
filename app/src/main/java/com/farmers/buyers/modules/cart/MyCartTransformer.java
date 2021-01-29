package com.farmers.buyers.modules.cart;

import com.farmers.buyers.R;
import com.farmers.buyers.modules.cart.model.MyCartItem;

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
}
