package com.farmers.buyers.modules.farmDetail;

import com.farmers.buyers.R;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.farmDetail.model.FarmDetailHeaderListItem;
import com.farmers.buyers.modules.farmDetail.model.FarmDetailItems;
import com.farmers.buyers.modules.farmDetail.model.FarmDetailsHeaderItems;
import com.farmers.buyers.modules.farmDetail.model.FarmDetailsVegetableItems;
import com.farmers.buyers.modules.farmDetail.model.FarmDetailsVegetablesListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 28-01-2021 at 12:14
 * mohammadsajjad679@gmail.com
 */

public class FarmDetailTransformer {

    public static FarmDetailHeaderListItem getHeaderItems() {
        List<RecyclerViewListItem> item = new ArrayList<>();
        item.add(new FarmDetailsHeaderItems("https://meet.google.com/iyq-kict-oit"));
        item.add(new FarmDetailsHeaderItems("https://meet.google.com/iyq-kict-oit"));
        return new FarmDetailHeaderListItem(item);
    }



    public static FarmDetailItems getFarmDetailItems() {
        return new FarmDetailItems();
    }

    public static FarmDetailsVegetablesListItem getFarmDetailVegList() {
        List<RecyclerViewListItem> item = new ArrayList<>();
        item.add(new FarmDetailsVegetableItems(R.drawable.veg_one, "Pomegranate", "100", "2kg", true));
        item.add(new FarmDetailsVegetableItems(R.drawable.veg_two, "Pomegranate", "100", "2kg", true));
        item.add(new FarmDetailsVegetableItems(R.drawable.fruit_one, "Pomegranate", "100", "2kg", true));
        item.add(new FarmDetailsVegetableItems(R.drawable.fruit_two, "Pomegranate", "100", "2kg", true));
        return new FarmDetailsVegetablesListItem(item);
    }



    public static FarmDetailsVegetablesListItem getFarmDetailFruitList() {
        List<RecyclerViewListItem> item = new ArrayList<>();
        item.add(new FarmDetailsVegetableItems(R.drawable.fruit_one, "Pomegranate", "100", "2kg", false));
        item.add(new FarmDetailsVegetableItems(R.drawable.fruit_two, "Pomegranate", "100", "2kg", true));
        item.add(new FarmDetailsVegetableItems(R.drawable.veg_one, "Pomegranate", "100", "2kg", true));
        item.add(new FarmDetailsVegetableItems(R.drawable.veg_two, "Pomegranate", "100", "2kg", true));
        return new FarmDetailsVegetablesListItem(item);
    }
}
