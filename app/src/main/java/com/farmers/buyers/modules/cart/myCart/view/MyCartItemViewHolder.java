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

import java.text.DecimalFormat;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * created by Mohammad Sajjad
 * on 29-01-2021 at 17:44
 * mohammadsajjad679@gmail.com
 */

public class MyCartItemViewHolder extends BaseViewHolder {
    private ImageView cartImage;
    private ImageView iconIncrease, iconDecrease;
    private TextView textQuantity, itemName, itemAddress, itemPrice,txt_unit;
    IncreaseCallback increaseCallback;
    DecreaseCallback decreaseCallback;

    int quantity = 0;

    public MyCartItemViewHolder(@NonNull ViewGroup parent, IncreaseCallback increaseCallback1, DecreaseCallback decreaseCallback1) {
        super(Extensions.inflate(parent, R.layout.my_cart_item_layout));

        cartImage = itemView.findViewById(R.id.my_cart_item_image);
        iconIncrease = itemView.findViewById(R.id.ic_increase);
        iconDecrease = itemView.findViewById(R.id.ic_decrease);
        textQuantity = itemView.findViewById(R.id.txt_quantity);
        itemName = itemView.findViewById(R.id.item_name);
        itemPrice = itemView.findViewById(R.id.item_price);
        itemAddress = itemView.findViewById(R.id.item_address);
        txt_unit=itemView.findViewById(R.id.txt_unit);

        decreaseCallback = decreaseCallback1;
        increaseCallback = increaseCallback1;
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
        quantity = item.getCartItemQuantity();
        if(!item.getAddress().equalsIgnoreCase("")) {
            itemAddress.setVisibility(View.VISIBLE);
            itemAddress.setText(item.getAddress());
        }
        else{
            itemAddress.setVisibility(View.GONE);
        }
double  item_price = Double.parseDouble(item.getItemSubPrice());
        itemPrice.setText("$" + String.format("%.2f",item_price)+" "+"X"+item.getCartItemQuantity());

        iconIncrease.setOnClickListener(view -> {
            quantity++;
            textQuantity.setText(String.valueOf(quantity));
//            itemPrice.setText("$ " + String.valueOf(Double.parseDouble(item.getPrice()) * quantity));

            increaseCallback.increaseCallback(item.getCartId());
        });
        iconDecrease.setOnClickListener(view -> {
            quantity--;
            textQuantity.setText(String.valueOf(quantity));
            decreaseCallback.decreseCallback(item.getCartId());
        });
        txt_unit.setText(item.getItem_unit());
    }

    public interface IncreaseCallback {
        void increaseCallback(String cartId);
    }

    public interface DecreaseCallback {
        void decreseCallback(String cartId);
    }
}
