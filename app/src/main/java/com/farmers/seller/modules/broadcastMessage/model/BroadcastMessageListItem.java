package com.farmers.seller.modules.broadcastMessage.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

public class BroadcastMessageListItem implements RecyclerViewListItem {

    String MessageID;
    String farm_id;
    String message_title;
    String message_content;
    String publish_on;
    String target_audience;
    String message_status;
    String message_status_name;

    public BroadcastMessageListItem() {
    }

    public BroadcastMessageListItem(String messageID, String farm_id, String message_title, String message_content,
                                    String publish_on, String target_audience, String message_status, String message_status_name) {
        MessageID = messageID;
        this.farm_id = farm_id;
        this.message_title = message_title;
        this.message_content = message_content;
        this.publish_on = publish_on;
        this.target_audience = target_audience;
        this.message_status = message_status;
        this.message_status_name = message_status_name;
    }

    public String getMessageID() {
        return MessageID;
    }

    public void setMessageID(String messageID) {
        MessageID = messageID;
    }

    public String getFarm_id() {
        return farm_id;
    }

    public void setFarm_id(String farm_id) {
        this.farm_id = farm_id;
    }

    public String getMessage_title() {
        return message_title;
    }

    public void setMessage_title(String message_title) {
        this.message_title = message_title;
    }

    public String getMessage_content() {
        return message_content;
    }

    public void setMessage_content(String message_content) {
        this.message_content = message_content;
    }

    public String getPublish_on() {
        return publish_on;
    }

    public void setPublish_on(String publish_on) {
        this.publish_on = publish_on;
    }

    public String getTarget_audience() {
        return target_audience;
    }

    public void setTarget_audience(String target_audience) {
        this.target_audience = target_audience;
    }

    public String getMessage_status() {
        return message_status;
    }

    public void setMessage_status(String message_status) {
        this.message_status = message_status;
    }

    public String getMessage_status_name() {
        return message_status_name;
    }

    public void setMessage_status_name(String message_status_name) {
        this.message_status_name = message_status_name;
    }

    @Override
    public int getViewType() {
        return CardConstant.BROAD_CAST_MESSAGE_LIST_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
