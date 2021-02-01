package com.farmers.buyers.modules.followers;

import com.farmers.buyers.modules.followers.model.FollowersItems;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 31-01-2021 at 18:50
 * mohammadsajjad679@gmail.com
 */

public class FollowersTransformer {

    public static List<FollowersItems> getFollowersItems() {
        List<FollowersItems> item  = new ArrayList<>();
        item.add(new FollowersItems(0, "","", false));
        item.add(new FollowersItems(0, "","", false));
        item.add(new FollowersItems(0, "","", false));
        item.add(new FollowersItems(0, "","", false));
        item.add(new FollowersItems(0, "","", false));
        return item;
    }
}
