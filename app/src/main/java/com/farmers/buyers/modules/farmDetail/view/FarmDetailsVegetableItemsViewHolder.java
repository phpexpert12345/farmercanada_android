package com.farmers.buyers.modules.farmDetail.view;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.cart.myCart.MyCartActivity;
import com.farmers.buyers.modules.farmDetail.model.FarmDetailsVegetableItems;

/**
 * created by Mohammad Sajjad
 * on 28-01-2021 at 12:12
 * mohammadsajjad679@gmail.com
 */

public class FarmDetailsVegetableItemsViewHolder extends BaseViewHolder {
    private ImageView imageView;
    private TextView addToCartTv;

    public FarmDetailsVegetableItemsViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.farm_details_vegetables_item_layout));
        imageView = itemView.findViewById(R.id.farm_detail_vegetables_img);
        addToCartTv = itemView.findViewById(R.id.farm_details_vegetables_add_to_cart_tv);

        addToCartTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemView.getContext().startActivity(new Intent(itemView.getContext(), MyCartActivity.class));
            }
        });
    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        imageView.setImageResource(((FarmDetailsVegetableItems)items).getImageUri());

        if (((FarmDetailsVegetableItems)items).getInStock()) {
            addToCartTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_shopping_cart, 0, 0, 0);
        }
        else {
            addToCartTv.setText("Out of stock");
            addToCartTv.setTextColor(itemView.getContext().getResources().getColor(R.color.primaryTextColor));
            addToCartTv.setBackground(itemView.getContext().getResources().getDrawable(R.drawable.light_red_border_bg));
            addToCartTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_info, 0, 0, 0);

        }

    }
}
