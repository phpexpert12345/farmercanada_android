package com.farmers.buyers.modules.cart.view;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.cart.model.MyCartItem;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * created by Mohammad Sajjad
 * on 29-01-2021 at 17:44
 * mohammadsajjad679@gmail.com
 */

public class MyCartItemViewHolder extends BaseViewHolder {
    private CircleImageView cartImage;


    public MyCartItemViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.my_cart_item_layout));
        cartImage = itemView.findViewById(R.id.my_cart_item_image);
    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        MyCartItem item = (MyCartItem) items;
        cartImage.setImageResource(item.getImgUri());

    }
}
