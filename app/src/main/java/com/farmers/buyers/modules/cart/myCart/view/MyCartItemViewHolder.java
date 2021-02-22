package com.farmers.buyers.modules.cart.myCart.view;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.cart.myCart.model.MyCartItem;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * created by Mohammad Sajjad
 * on 29-01-2021 at 17:44
 * mohammadsajjad679@gmail.com
 */

public class MyCartItemViewHolder extends BaseViewHolder {
    private CircleImageView cartImage;
    private ImageView iconIncrease, iconDecrease;
    private TextView textQuantity,itemName,itemAddress,itemPrice;
    IncreaseCallback increaseCallback;
    DecreaseCallback decreaseCallback;

    int quantity = 0;

    public MyCartItemViewHolder(@NonNull ViewGroup parent,IncreaseCallback increaseCallback1,DecreaseCallback decreaseCallback1) {
        super(Extensions.inflate(parent, R.layout.my_cart_item_layout));
        cartImage = itemView.findViewById(R.id.my_cart_item_image);
        iconIncrease = itemView.findViewById(R.id.ic_increase);
        iconDecrease = itemView.findViewById(R.id.ic_decrease);
        textQuantity = itemView.findViewById(R.id.txt_quantity);
        itemName = itemView.findViewById(R.id.item_name);
        itemPrice = itemView.findViewById(R.id.item_price);
        itemAddress = itemView.findViewById(R.id.item_address);
        decreaseCallback=decreaseCallback1;
        increaseCallback=increaseCallback1;
    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        MyCartItem item = (MyCartItem) items;
        Glide.with(itemView.getContext())
                .load(item.getImgUri())
                .centerCrop()
                .placeholder(R.drawable.cart_one)
                .into(cartImage);
        textQuantity.setText(String.valueOf(item.getCartItemQuantity()));
        itemName.setText(item.getName());

        quantity=item.getCartItemQuantity();
        itemAddress.setText(item.getAddress());



        itemPrice.setText("$ "+String.valueOf(item.getItemSubPrice()));

        iconIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity = quantity + 1;
                textQuantity.setText(String.valueOf(quantity));
                itemPrice.setText("$ "+String.valueOf(Integer.parseInt(item.getPrice())*quantity));

                increaseCallback.increaseCallback(item.getCartId());
            }
        });
        iconDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity = quantity - 1;
                textQuantity.setText(String.valueOf(quantity));
                itemPrice.setText("$ "+String.valueOf(Integer.parseInt(item.getPrice())*quantity));
                decreaseCallback.decreseCallback(item.getCartId());
            }
        });



    }


    public interface IncreaseCallback{
        void increaseCallback(String cartId);
    }
    public interface DecreaseCallback{
        void decreseCallback(String cartId);
    }
}
