package com.farmers.buyers.modules.chat;

import com.farmers.buyers.modules.chat.model.ChatListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 02-02-2021 at 14:51
 * mohammadsajjad679@gmail.com
 */

public class ChatTransformer {

    public static List<ChatListItem> getChats() {
        List<ChatListItem> items = new ArrayList<>();
        items.add(new ChatListItem("hi", null, "2:30 pm", null));
        items.add(new ChatListItem(null, "Hello", null, "2:31 pm"));
        items.add(new ChatListItem("how are you?", null, "2:32 pm", null));
        items.add(new ChatListItem(null, "fine", null, "2:33pm"));
        return items;
    }
}
