package com.farmers.buyers.modules.cart.myCart.adapter;

import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.modules.cart.myCart.MyCartFragment;
import com.farmers.buyers.modules.cart.myCart.view.MyCartCheckOutDelegate;
import com.farmers.buyers.modules.cart.myCart.view.MyCartCheckoutViewHolder;
import com.farmers.buyers.modules.cart.myCart.view.MyCartItemViewDelegate;
import com.farmers.buyers.modules.cart.myCart.view.MyCartItemViewHolder;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 29-01-2021 at 17:44
 * mohammadsajjad679@gmail.com
 */

public class MyCartAdapter extends BaseAdapter {
    MyCartCheckoutViewHolder.MyCartCheckOutClickListeners cartCheckOutClickListeners;
    MyCartCheckoutViewHolder.MyCoupounClickListeners myCoupounClickListeners;
    MyCartItemViewHolder.increaseCallback increaseCallback;
    MyCartItemViewHolder.decreaseCallback decreaseCallback;


    public MyCartAdapter(MyCartCheckoutViewHolder.MyCartCheckOutClickListeners cartCheckOutClickListeners,
                         MyCartCheckoutViewHolder.MyCoupounClickListeners myCoupounClickListeners1,
                         MyCartItemViewHolder.increaseCallback increaseCallback1,
                         MyCartItemViewHolder.decreaseCallback decreaseCallback1) {
        super();
        this.cartCheckOutClickListeners = cartCheckOutClickListeners;
        this.myCoupounClickListeners=myCoupounClickListeners1;
        this.increaseCallback=increaseCallback1;
        this.decreaseCallback=decreaseCallback1;
        this.initDelegate();
    }

    @Override
    public void initDelegate() {
        delegates.put(CardConstant.MY_CART_ITEM_ADAPTER, new MyCartItemViewDelegate(decreaseCallback, increaseCallback));
        delegates.put(CardConstant.MY_CART_CHECKOUT_ITEM_ADAPTER, new MyCartCheckOutDelegate(cartCheckOutClickListeners,myCoupounClickListeners));
    }
}
