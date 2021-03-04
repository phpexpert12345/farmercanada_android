package com.farmers.seller.modules.setupSellerAccount;

import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.RecyclerViewListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohammad sajjad on 04-03-2021.
 * mohammadsajjad679@gmail.com
 */
public class SetupStoreViewModel extends BaseViewModel {
    public List<RecyclerViewListItem> items = new ArrayList<>();

    public void prepareRageItems () {
        items.addAll(SetupStoreTransformer.getDeliveryRangeItem());
    }


}
