package com.farmers.buyers.storage;

/**
 * created by Mohammad Sajjad
 * on 25-01-2021 at 21:08
 * mohammadsajjad679@gmail.com
 */

public class CardConstant {

    private static CardConstant cardConstant = null ;

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


}
