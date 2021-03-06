package com.farmers.buyers.modules.address.adapter;

import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.modules.address.view.MyAddressDelegate;
import com.farmers.buyers.modules.address.view.MyAddressListViewHolder;
import com.farmers.buyers.modules.cart.checkout.view.CheckoutFromCartAddressDelegate;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 04-02-2021 at 22:35
 * mohammadsajjad679@gmail.com
 */

public class MyAddressAdapter extends BaseAdapter {

    MyAddressListViewHolder.AddressItemClickListener addressItemClickListener;

    public MyAddressAdapter(MyAddressListViewHolder.AddressItemClickListener addressItemClickListener) {
        super();
        this.addressItemClickListener = addressItemClickListener;
        this.initDelegate();
    }

    @Override
    public void initDelegate() {
        delegates.put(CardConstant.MY_CART_ADDRESS_ADAPTER, new MyAddressDelegate(addressItemClickListener));
    }
}
