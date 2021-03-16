package com.farmers.buyers.modules.cart.checkout.adapter;

import com.farmers.buyers.common.view.SimpleTitleDelegate;
import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.modules.cart.checkout.view.CheckOutFromCartAddressViewHolder;
import com.farmers.buyers.modules.cart.checkout.view.CheckoutFromCartAddressDelegate;
import com.farmers.buyers.modules.cart.checkout.view.CheckoutTitleDelegate;
import com.farmers.buyers.modules.cart.checkout.view.PaymentMethodsDelegate;
import com.farmers.buyers.modules.cart.checkout.view.PaymentMethodsViewHolder;
import com.farmers.buyers.modules.cart.myCart.view.MyCartCheckOutDelegate;
import com.farmers.buyers.modules.cart.myCart.view.MyCartCheckoutViewHolder;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 30-01-2021 at 11:51
 * mohammadsajjad679@gmail.com
 */

public class CheckOutCartItemAdapter extends BaseAdapter {
    private MyCartCheckoutViewHolder.MyCartCheckOutClickListeners cartCheckOutClickListeners;
    private MyCartCheckoutViewHolder.MyCoupounClickListeners coupounClickListeners;
    private CheckOutFromCartAddressViewHolder.ChangeAddressCallback addressCallback;
    private PaymentMethodsViewHolder.PaymentMethodListener paymentMethodListener;

    public CheckOutCartItemAdapter(MyCartCheckoutViewHolder.MyCartCheckOutClickListeners cartCheckOutClickListeners,
                                   MyCartCheckoutViewHolder.MyCoupounClickListeners couponListener,
                                   CheckOutFromCartAddressViewHolder.ChangeAddressCallback addressListener,
                                   PaymentMethodsViewHolder.PaymentMethodListener paymentMethodListener) {
        super();
        this.cartCheckOutClickListeners = cartCheckOutClickListeners;
        this.coupounClickListeners = couponListener;
        this.addressCallback = addressListener;
        this.paymentMethodListener = paymentMethodListener;
        this.initDelegate();
    }

    @Override
    public void initDelegate() {
        delegates.put(CardConstant.MY_CART_ADDRESS_ADAPTER, new CheckoutFromCartAddressDelegate(addressCallback));
        delegates.put(CardConstant.MY_CART_PAYMENT_METHODS, new PaymentMethodsDelegate(paymentMethodListener));
        delegates.put(CardConstant.SIMPLE_TITLE_ITEM_ADAPTER, new CheckoutTitleDelegate());
        delegates.put(CardConstant.MY_CART_CHECKOUT_ITEM_ADAPTER, new MyCartCheckOutDelegate(cartCheckOutClickListeners, coupounClickListeners));
    }
}
