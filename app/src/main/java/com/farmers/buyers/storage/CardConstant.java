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

    public static final int HOME_HEADER_ADAPTER = 1;
    public static final int HOME_FARM_LIST_ITEM_ADAPTER = 2;
    public static final int HOME_CATEGORY_LIST_ITEM_ADAPTER = 3;
    public static final int HOME_CATEGORY_ITEM_ADAPTER = 4;
    public static final int HOME_TOP_OFFER_ITEM_ADAPTER = 5;


}
