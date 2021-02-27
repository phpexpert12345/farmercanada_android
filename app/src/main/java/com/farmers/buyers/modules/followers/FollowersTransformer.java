package com.farmers.buyers.modules.followers;

import com.farmers.buyers.modules.followers.model.FollowersApiModel;
import com.farmers.buyers.modules.followers.model.FollowersItems;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 31-01-2021 at 18:50
 * mohammadsajjad679@gmail.com
 */

public class FollowersTransformer {

    public static List<FollowersItems> getFollowersItems(List<FollowersApiModel.FarmFollowedList> listItem) {
        List<FollowersItems> item  = new ArrayList<>();
        for (int i = 0 ; i< listItem.size() ; i++) {
            FollowersApiModel.FarmFollowedList data = listItem.get(i);
            if (data.getFarmFollowedStatus().equals("Yes")) {
                item.add(new FollowersItems(data.getFarmLogo(), data.getFarmName(), "", data.getFarmFollowedStatus(), data.getFarm_id(), String.valueOf(data.getFollowed_id())));
            }
        }
        return item;
    }
}
