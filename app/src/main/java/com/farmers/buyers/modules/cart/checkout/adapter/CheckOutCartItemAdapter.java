package com.farmers.buyers.modules.cart.checkout.adapter;

import com.farmers.buyers.common.view.SimpleTitleDelegate;
import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.modules.cart.checkout.view.CheckoutFromCartAddressDelegate;
import com.farmers.buyers.modules.cart.checkout.view.PaymentMethodsDelegate;
import com.farmers.buyers.modules.cart.myCart.view.MyCartCheckOutDelegate;
import com.farmers.buyers.modules.cart.myCart.view.MyCartCheckoutViewHolder;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 30-01-2021 at 11:51
 * mohammadsajjad679@gmail.com
 */

public class CheckOutCartItemAdapter extends BaseAdapter {
    MyCartCheckoutViewHolder.MyCartCheckOutClickListeners cartCheckOutClickListeners;
    MyCartCheckoutViewHolder.MyCoupounClickListeners coupounClickListeners;



    public CheckOutCartItemAdapter(MyCartCheckoutViewHolder.MyCartCheckOutClickListeners cartCheckOutClickListeners, MyCartCheckoutViewHolder.MyCoupounClickListeners couponListener) {
        super();
        this.cartCheckOutClickListeners = cartCheckOutClickListeners;
        this.coupounClickListeners=couponListener;
        this.initDelegate();
    }

    @Override
    public void initDelegate() {
        delegates.put(CardConstant.MY_CART_ADDRESS_ADAPTER, new CheckoutFromCartAddressDelegate());
        delegates.put(CardConstant.MY_CART_PAYMENT_METHODS, new PaymentMethodsDelegate());
        delegates.put(CardConstant.SIMPLE_TITLE_ITEM_ADAPTER, new SimpleTitleDelegate());
        delegates.put(CardConstant.MY_CART_CHECKOUT_ITEM_ADAPTER, new MyCartCheckOutDelegate(cartCheckOutClickListeners,coupounClickListeners));
    }
}
