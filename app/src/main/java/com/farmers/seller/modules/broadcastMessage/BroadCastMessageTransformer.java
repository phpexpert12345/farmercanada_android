package com.farmers.seller.modules.broadcastMessage;

import com.farmers.buyers.modules.support.model.SupportListItem;
import com.farmers.seller.modules.broadcastMessage.model.BroadcastMessageListItem;

import java.util.ArrayList;
import java.util.List;

public class BroadCastMessageTransformer {

    public static List<BroadcastMessageListItem> getBroadcastList() {
        List<BroadcastMessageListItem> item = new ArrayList<>();
        item.add(new BroadcastMessageListItem());
        item.add(new BroadcastMessageListItem());
        item.add(new BroadcastMessageListItem());
        item.add(new BroadcastMessageListItem());
        item.add(new BroadcastMessageListItem());
        item.add(new BroadcastMessageListItem());
        return item;
    }
}
