package com.farmers.buyers.storage;

/**
 * created by Mohammad Sajjad
 * on 25-01-2021 at 21:08
 * mohammadsajjad679@gmail.com
 */

public class CardConstant {

    private static CardConstant cardConstant = null;

    CardConstant() {

    }

    public static CardConstant cardConstant() {

        if (cardConstant == null) {
            cardConstant = new CardConstant();
        }
        return cardConstant;
    }

    public static final int HOME_HEADER_ADAPTER                 = 1;
    public static final int HOME_FARM_LIST_ITEM_ADAPTER         = 2;
    public static final int HOME_CATEGORY_LIST_ITEM_ADAPTER     = 3;
    public static final int HOME_CATEGORY_ITEM_ADAPTER          = 4;
    public static final int HOME_SEARCH_ITEM_ADAPTER            = 5;
    public static final int SIMPLE_TITLE_ITEM_ADAPTER           = 6;
    public static final int HOME_TOP_OFFER_ADAPTER              = 7;
    public static final int HOME_TOP_OFFER_ITEM_ADAPTER         = 8;
    public static final int MULTIPLE_ITEM_TYPE_ADAPTER          = 9;
    public static final int MULTIPLE_ITEM_ADAPTER               = 10;
    public static final int DELIVERY_TYPE_ADAPTER               = 11;
    public static final int HOME_FARM_TYPE_ADAPTER              = 12;
    public static final int FARM_DETAIL_HEADER_ADAPTER          = 13;
    public static final int FARM_DETAIL_ADAPTER                 = 14;
    public static final int FARM_DETAIL_VEGETABLE_ADAPTER       = 15;
    public static final int FARM_DETAIL_VEGETABLE_ITEM_ADAPTER  = 16;
    public static final int FARM_DETAIL_HEADER_ITEM_ADAPTER     = 17;
    public static final int MY_CART_ITEM_ADAPTER                = 18;
    public static final int MY_CART_CHECKOUT_ITEM_ADAPTER       = 19;
    public static final int MY_CART_ADDRESS_ADAPTER             = 20;
    public static final int MY_CART_PAYMENT_METHODS             = 21;
    public static final int PLACE_ORDER_ADAPTER                 = 22;
    public static final int PLACE_ORDER_ITEM_ADAPTER            = 23;
    public static final int PROFILE_HEADER_ADAPTER              = 24;
    public static final int PROFILE_OPTION_MENU_ADAPTER         = 25;
    public static final int PROFILE_OPTION_MENU_ITEM_ADAPTER    = 26;
    public static final int SIMPLE_ROW_ITEM                     = 27;
    public static final int SIMPLE_ROW_LIST_ITEM                = 28;
    public static final int FOLLOWERS_ITEM_ADAPTER              = 29;
    public static final int WALLET_HISTORY_ADAPTER              = 30;
    public static final int NOTIFICATION_LIST_ADAPTER           = 31;
    public static final int MESSAGE_LIST_ADAPTER                = 32;
    public static final int CHAT_ITEM_ADAPTER                   = 33;
    public static final int WALLET_HEADER_ITEM_ADAPTER          = 34;
    public static final int ORDER_ITEMS_ADAPTER                 = 35;
    public static final int SUB_ORDER_ITEM_ADAPTER              = 36;
    public static final int TRACK_ORDER_HEADER_ITEM             = 37;
    public static final int TRACK_ORDER_ITEM_ADAPTER            = 38;
    public static final int REVIEWED_ITEMS_ADAPTER              = 39;
    public static final int REVIEW_ITEMS_ADAPTER                = 40;
    public static final int SUPPORT_ITEMS_ADAPTER               = 41;



}
