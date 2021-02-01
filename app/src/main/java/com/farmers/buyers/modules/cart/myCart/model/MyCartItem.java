package com.farmers.buyers.modules.cart.myCart.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 29-01-2021 at 18:17
 * mohammadsajjad679@gmail.com
 */

public class MyCartItem implements RecyclerViewListItem {
    int imgUri;
    String name;
    String price;
    String address;

    public MyCartItem(int imgUri, String name, String price, String address) {
        this.imgUri = imgUri;
        this.name = name;
        this.price = price;
        this.address = address;
    }

    public int getImgUri() {
        return imgUri;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public int getViewType() {
        return CardConstant.MY_CART_ITEM_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
