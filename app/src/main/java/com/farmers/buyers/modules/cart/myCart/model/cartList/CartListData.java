package com.farmers.buyers.modules.cart.myCart.model.cartList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/20/2021.
 */
public class CartListData implements Serializable {

    @SerializedName("FarmProductCartList")
    @Expose
    private List<FarmProductCartList> farmProductCartList = null;

    public List<FarmProductCartList> getFarmProductCartList() {
        return farmProductCartList;
    }
}
