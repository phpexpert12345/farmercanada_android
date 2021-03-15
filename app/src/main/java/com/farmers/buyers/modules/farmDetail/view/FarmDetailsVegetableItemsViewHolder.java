package com.farmers.buyers.modules.farmDetail.view;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
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
    private ImageView imageView,img_cart_veggie;
    private TextView addToCartTv;
    private TextView farmName, tv_sub_cat_price, tv_sub_cat_quantity, txt_quantity;
    private FarmDetailVegetableListener listener;
    private FarmDetailsVegetableItems item;
    private LinearLayout ll_increase_decrease, ll_decrease, ll_increase;
    private RelativeLayout relative_veggie_cart;
    private int cnt = 0;

    public FarmDetailsVegetableItemsViewHolder(@NonNull ViewGroup parent, FarmDetailVegetableListener listener) {
        super(Extensions.inflate(parent, R.layout.farm_details_vegetables_item_layout));
        imageView = itemView.findViewById(R.id.farm_detail_vegetables_img);
        addToCartTv = itemView.findViewById(R.id.farm_details_vegetables_add_to_cart_tv);
        farmName = itemView.findViewById(R.id.home_list_item_layout_farmName);
        tv_sub_cat_price = itemView.findViewById(R.id.tv_sub_cat_price);
        tv_sub_cat_quantity = itemView.findViewById(R.id.tv_sub_cat_quantity);
        txt_quantity = itemView.findViewById(R.id.txt_quantity);
        ll_increase_decrease = itemView.findViewById(R.id.ll_increase_decrease);
        ll_decrease = itemView.findViewById(R.id.ll_decrease);
        ll_increase = itemView.findViewById(R.id.ll_increase);
        img_cart_veggie = itemView.findViewById(R.id.img_cart_veggie);
        relative_veggie_cart = itemView.findViewById(R.id.relative_veggie_cart);

        this.listener = listener;

        addToCartTv.setOnClickListener(view -> {
            cnt = 1;
         //   addToCartTv.setVisibility(View.GONE);
           // ll_increase_decrease.setVisibility(View.VISIBLE);
            listener.onClickFarmDetailVegetableListener(item, cnt);
            //itemView.getContext().startActivity(new Intent(itemView.getContext(), MyCartActivity.class));
        });

        ll_increase.setOnClickListener(view -> {
            cnt++;
            listener.onClickIncreaseCartListener(item, cnt);
            //txt_quantity.setText(String.valueOf(cnt));
        });

        ll_decrease.setOnClickListener(view -> {
            if (cnt <= 0) {

            } else {
                cnt--;
                listener.onClickDecreaseCartListener(item, cnt);
                txt_quantity.setText(String.valueOf(cnt));
            }
        });
    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        FarmDetailsVegetableItems item = (FarmDetailsVegetableItems) items;
        this.item = item;
        Glide.with(itemView.getContext()).load(item.getImageUri()).placeholder(R.drawable.ic_sign_up_logo).into(imageView);
        farmName.setText(item.getTitle());
        tv_sub_cat_price.setText("$"+item.getPrice());
        tv_sub_cat_quantity.setText(item.getQuantity());

        if (item.product_stock.equals("Yes")) {
            img_cart_veggie.setImageResource(R.drawable.ic_shopping_cart);
        } else {
            addToCartTv.setText("Out of stock");
            addToCartTv.setTextColor(itemView.getContext().getResources().getColor(R.color.primaryTextColor));
            relative_veggie_cart.setBackground(itemView.getContext().getResources().getDrawable(R.drawable.light_red_border_bg));
            img_cart_veggie.setImageResource(R.drawable.ic_info);
        }

        if (item.shopping_item_available.equals("No")) {
            relative_veggie_cart.setVisibility(View.VISIBLE);
            ll_increase_decrease.setVisibility(View.GONE);
        } else {
            cnt = Integer.parseInt(item.shopping_item_quantity);
            relative_veggie_cart.setVisibility(View.GONE);
            txt_quantity.setText(item.shopping_item_quantity);
            ll_increase_decrease.setVisibility(View.VISIBLE);
        }

       /* if (((FarmDetailsVegetableItems) items).getInStock()) {
            addToCartTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_shopping_cart, 0, 0, 0);
        } else {
            addToCartTv.setText("Out of stock");
            addToCartTv.setTextColor(itemView.getContext().getResources().getColor(R.color.primaryTextColor));
            addToCartTv.setBackground(itemView.getContext().getResources().getDrawable(R.drawable.light_red_border_bg));
            addToCartTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_info, 0, 0, 0);
        }*/
    }

    public interface FarmDetailVegetableListener {
        void onClickFarmDetailVegetableListener(FarmDetailsVegetableItems item, int cnt);

        void onClickIncreaseCartListener(FarmDetailsVegetableItems item, int cnt);

        void onClickDecreaseCartListener(FarmDetailsVegetableItems item, int cnt);
    }
}