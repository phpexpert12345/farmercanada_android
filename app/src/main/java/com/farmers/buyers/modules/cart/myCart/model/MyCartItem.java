package com.farmers.buyers.modules.cart.myCart.model;

import androidx.annotation.BinderThread;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 29-01-2021 at 18:17
 * mohammadsajjad679@gmail.com
 */

public class MyCartItem implements RecyclerViewListItem {
    String imgUri;
    String name;
    String price;
    String address;
    String cartId;
    int cartItemQuantity;
    String itemSubPrice;

    public MyCartItem(String imgUri, String name, String price, String address, String cartId, int cartItemQuantity1, String itemSubPrice1) {
        this.imgUri = imgUri;
        this.name = name;
        this.price = price;
        this.address = address;
        this.cartId = cartId;
        this.cartItemQuantity = cartItemQuantity1;
        this.itemSubPrice = itemSubPrice1;
    }


    public String getItemSubPrice() {
        return itemSubPrice;
    }

    public void setItemSubPrice(String itemSubPrice) {
        this.itemSubPrice = itemSubPrice;
    }

    public String getImgUri() {
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


    public String getCartId() {
        return cartId;
    }

    public int getCartItemQuantity() {
        return cartItemQuantity;
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
