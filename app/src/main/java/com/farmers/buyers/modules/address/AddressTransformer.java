package com.farmers.buyers.modules.address;

import com.farmers.buyers.modules.address.model.AddressApiModel;
import com.farmers.buyers.modules.cart.checkout.model.CheckOutCartAddressItems;
import com.farmers.buyers.modules.home.models.AllDataModel;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 04-02-2021 at 22:38
 * mohammadsajjad679@gmail.com
 */

public class AddressTransformer {

    public static List<CheckOutCartAddressItems> getAddress(List<AddressApiModel.AddressListData> allDataModels) {
        List<CheckOutCartAddressItems> items = new ArrayList<>();

        for (int i = 0; i < allDataModels.size(); i++) {
            items.add(new CheckOutCartAddressItems(allDataModels.get(i).getAddress_id(),
                    allDataModels.get(i).getVendor_city(),
                    allDataModels.get(i).getVendor_address(),
                    allDataModels.get(i).getVendor_country(),
                    allDataModels.get(i).getAddress_title(),
                    allDataModels.get(i).getAccount_phone_number(),
                    allDataModels.get(i).getVendor_state(),
                    allDataModels.get(i).getAddress_postcode(),allDataModels.get(i).getVendor_lat(),allDataModels.get(i).getVendor_long()));
        }

        return items;
    }

//    public static List<CheckOutCartAddressItems> getAddress() {
//        List<CheckOutCartAddressItems> items = new ArrayList<>();
//        items.add(new CheckOutCartAddressItems("", "My Home Addres", "4623 William Head Rd", "Victoria, BC V9C 3Y7, Canada", true, false));
//        items.add(new CheckOutCartAddressItems("", "Daddy Home Addres", "4623 William Head Rd", "Victoria, BC V9C 3Y7, Canada", false, false));
//        items.add(new CheckOutCartAddressItems("", "Daddy Home Addres2", "4623 William Head Rd", "Victoria, BC V9C 3Y7, Canada", false, false));
//        items.add(new CheckOutCartAddressItems("", "Daddy Home Addres3", "4623 William Head Rd", "Victoria, BC V9C 3Y7, Canada", false, false));
//        return items;
//    }
}
