package com.farmers.buyers.modules.saveFarms;

import com.farmers.buyers.modules.home.models.HomeListItem;
import com.farmers.buyers.modules.saveFarms.model.SaveFarmListApiModel;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 05-02-2021 at 16:04
 * mohammadsajjad679@gmail.com
 */

public class SaveFarmTransformer {


    public static List<HomeListItem> getFarmListItem(List<SaveFarmListApiModel.FarmFavouriteList> listItems) {
        List<HomeListItem> farmItems = new ArrayList<>();
        for (int i = 0 ; i< listItems.size() ; i++) {
            SaveFarmListApiModel.FarmFavouriteList item = listItems.get(i);

            farmItems.add(new HomeListItem(item.getFarmName(), item.getFarmDeliveryRadiusText(), item.getRatingAvg().toString(), item.getFarmFavouriteStatus(), item.getFarmId(), item.getFarmCoverPhoto(), item.getFarmLogo(), item.getFarmLatitude(), item.getFarmLongitude()));
        }
        return farmItems;
    }
}
