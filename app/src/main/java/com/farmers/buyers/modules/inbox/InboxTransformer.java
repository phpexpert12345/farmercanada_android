package com.farmers.buyers.modules.inbox;

import com.farmers.buyers.modules.inbox.model.MessageListItems;
import com.farmers.buyers.modules.inbox.model.NotificationListItems;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 01-02-2021 at 17:28
 * mohammadsajjad679@gmail.com
 */

public class InboxTransformer {

    public static List<MessageListItems> getMessageList() {
        List<MessageListItems> item = new ArrayList<>();
        item.add(new MessageListItems());
        item.add(new MessageListItems());
        item.add(new MessageListItems());
        item.add(new MessageListItems());
        item.add(new MessageListItems());
        item.add(new MessageListItems());
        return item;
    }

    public static List<NotificationListItems> getNotificationItems() {
        List<NotificationListItems> item = new ArrayList<>();
        item.add(new NotificationListItems("", "", "", 2, "", false));
        item.add(new NotificationListItems("", "", "", 1, "", false));
        item.add(new NotificationListItems("", "", "", 0, "", true));
        item.add(new NotificationListItems("", "", "", 3, "", true));
        item.add(new NotificationListItems("", "", "", 0, "", true));
        item.add(new NotificationListItems("", "", "", 2, "", true));
        return item;
    }
}
