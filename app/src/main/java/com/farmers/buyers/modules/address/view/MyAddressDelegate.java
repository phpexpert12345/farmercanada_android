package com.farmers.buyers.modules.address.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.modules.ratingAndReview.view.ReviewListViewHolder;

public class MyAddressDelegate extends BaseDelegate {

    MyAddressListViewHolder.AddressItemClickListener addressItemClickListener;

    public MyAddressDelegate(MyAddressListViewHolder.AddressItemClickListener addressItemClickListener) {
        this.addressItemClickListener = addressItemClickListener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new MyAddressListViewHolder(parent, addressItemClickListener);
    }
}
