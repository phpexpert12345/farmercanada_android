package com.farmers.buyers.modules.cart.myCart.view;

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
    private TextView textQuantity;

    int quantity = 0;

    public MyCartItemViewHolder(@NonNull ViewGroup parent,increaseCallback increaseCallback,decreaseCallback decreaseCallback) {
        super(Extensions.inflate(parent, R.layout.my_cart_item_layout));
        cartImage = itemView.findViewById(R.id.my_cart_item_image);
        iconIncrease = itemView.findViewById(R.id.ic_increase);
        iconDecrease = itemView.findViewById(R.id.ic_decrease);
        textQuantity = itemView.findViewById(R.id.txt_quantity);
    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        MyCartItem item = (MyCartItem) items;
        Glide.with(itemView.getContext())
                .load(item.getImgUri())
                .centerCrop()
                .placeholder(R.drawable.cart_one)
                .into(cartImage);
       // cartImage.setImageResource(item.getImgUri());
        iconIncrease.setOnClickListener(clickListener);
        iconDecrease.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = view -> {
        switch (view.getId()) {
            case R.id.ic_decrease:
                quantity = quantity - 1;
                textQuantity.setText(String.valueOf(quantity));
                break;
            case R.id.ic_increase:
                quantity = quantity + 1;
                textQuantity.setText(String.valueOf(quantity));
                break;
        }
    };

    public interface increaseCallback{
        void increaseCallback();
    }
    public interface decreaseCallback{
        void decreseCallback();
    }
}
