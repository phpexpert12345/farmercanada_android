package com.farmers.seller.modules.broadcastMessage.model;

public class BroadcastMessageRequest {

    String LoginId;
    String auth_key;
    String farm_id;
    String message_title;
    String message_content;
    String message_target;
    String message_status;
    String messageId;

    public BroadcastMessageRequest(String loginId, String auth_key, String farm_id,
                                   String message_title, String message_content, String message_target, String message_status, String messageId) {
        LoginId = loginId;
        this.auth_key = auth_key;
        this.farm_id = farm_id;
        this.message_title = message_title;
        this.message_content = message_content;
        this.message_target = message_target;
        this.message_status = message_status;
        this.messageId = messageId;
    }

    public BroadcastMessageRequest(String loginId, String auth_key, String farm_id,
                                   String message_title, String message_content, String message_target, String message_status) {
        LoginId = loginId;
        this.auth_key = auth_key;
        this.farm_id = farm_id;
        this.message_title = message_title;
        this.message_content = message_content;
        this.message_target = message_target;
        this.message_status = message_status;
    }

    public BroadcastMessageRequest(String loginId, String auth_key, String farm_id) {
        LoginId = loginId;
        this.auth_key = auth_key;
        this.farm_id = farm_id;
    }

    public String getLoginId() {
        return LoginId;
    }

    public void setLoginId(String loginId) {
        LoginId = loginId;
    }

    public String getAuth_key() {
        return auth_key;
    }

    public void setAuth_key(String auth_key) {
        this.auth_key = auth_key;
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

    public String getMessage_target() {
        return message_target;
    }

    public void setMessage_target(String message_target) {
        this.message_target = message_target;
    }

    public String getMessage_status() {
        return message_status;
    }

    public void setMessage_status(String message_status) {
        this.message_status = message_status;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
