package com.farmers.seller.modules.broadcastMessage;

import com.farmers.seller.modules.broadcastMessage.model.BroadcastMessageListItem;
import com.farmers.seller.modules.broadcastMessage.model.BroadcastMessageResponse;

import java.util.ArrayList;
import java.util.List;

public class BroadCastMessageTransformer {

    public static List<BroadcastMessageListItem> getBroadcastList(List<BroadcastMessageResponse.BroadcastListData> broadcastList) {
        List<BroadcastMessageListItem> item = new ArrayList<>();
        for (int i = 0; i < broadcastList.size(); i++) {
            item.add(new BroadcastMessageListItem(
                    broadcastList.get(i).MessageID,
                    broadcastList.get(i).farm_id,
                    broadcastList.get(i).message_title,
                    broadcastList.get(i).message_content,
                    broadcastList.get(i).publish_on,
                    broadcastList.get(i).target_audience,
                    broadcastList.get(i).message_status,
                    broadcastList.get(i).message_status_name));
        }
        return item;
    }
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
