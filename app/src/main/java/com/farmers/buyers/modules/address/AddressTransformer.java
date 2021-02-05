package com.farmers.buyers.modules.address;

import com.farmers.buyers.modules.cart.checkout.model.CheckOutCartAddressItems;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 04-02-2021 at 22:38
 * mohammadsajjad679@gmail.com
 */

public class AddressTransformer {


    public static List<CheckOutCartAddressItems> getAddress() {
        List<CheckOutCartAddressItems> items = new ArrayList<>();
        items.add(new CheckOutCartAddressItems("My Home Addres", "4623 William Head Rd", "Victoria, BC V9C 3Y7, Canada", true));
        items.add(new CheckOutCartAddressItems("Daddy Home Addres", "4623 William Head Rd", "Victoria, BC V9C 3Y7, Canada", false));
        items.add(new CheckOutCartAddressItems("Daddy Home Addres2", "4623 William Head Rd", "Victoria, BC V9C 3Y7, Canada", false));
        items.add(new CheckOutCartAddressItems("Daddy Home Addres3", "4623 William Head Rd", "Victoria, BC V9C 3Y7, Canada", false));
        return items;
    }

}
