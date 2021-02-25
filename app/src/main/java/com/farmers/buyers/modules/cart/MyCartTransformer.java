package com.farmers.buyers.modules.cart;

import android.util.Log;

import com.farmers.buyers.R;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.cart.myCart.model.MyCartItem;
import com.farmers.buyers.modules.cart.myCart.model.cartList.FarmProductCartList;
import com.farmers.buyers.modules.cart.myCart.model.chargeTax.TaxData;
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

    public static List<MyCartItem> getMyCartItem(List<FarmProductCartList> cartLists) {
        List<MyCartItem> items = new ArrayList<>();

        for (int i = 0; cartLists.size() > i; i++) {
            Double item_quantity, item_price;
            item_quantity = Double.parseDouble(cartLists.get(i).getItemQuantity());
            item_price = Double.parseDouble(cartLists.get(i).getItemPrice());

            Double itemSubPrice = item_price * item_quantity;

            items.add(new MyCartItem(cartLists.get(i).product_images,
                    cartLists.get(i).product_name,
                    cartLists.get(i).getItemPrice(),
                    cartLists.get(i).getFarmAddress(),
                    cartLists.get(i).getCartId(),
                    Integer.parseInt(cartLists.get(i).getItemQuantity()),
                    String.valueOf(itemSubPrice)));

        }
        return items;
    }

    public static PlaceOrderSlotListItems getPlaceOrderSlot() {
        List<RecyclerViewListItem> item = new ArrayList<>();
        item.add(new PlaceOrderSlotItem("S", "31 Jan"));
        item.add(new PlaceOrderSlotItem("M", "2 Feb"));
        item.add(new PlaceOrderSlotItem("T", "3 Feb"));
        item.add(new PlaceOrderSlotItem("W", "4 Feb"));
        item.add(new PlaceOrderSlotItem("T", "5 Feb"));
        item.add(new PlaceOrderSlotItem("F", "6 Feb"));
        item.add(new PlaceOrderSlotItem("S", "7 Feb"));
        return new PlaceOrderSlotListItems(item);
    }

    public static TaxData getTaxDataItem(TaxData taxData) {
        return taxData;
    }
}
