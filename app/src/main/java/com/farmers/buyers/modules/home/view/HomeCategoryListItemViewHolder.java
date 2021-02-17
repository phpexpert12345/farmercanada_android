package com.farmers.buyers.modules.home.view;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.home.models.HomeCategoryItems;

import static com.farmers.buyers.app.App.getAppContext;


/**
 * created by Mohammad Sajjad
 * on 26-01-2021 at 01:54
 * mohammadsajjad679@gmail.com
 */

public class HomeCategoryListItemViewHolder extends BaseViewHolder {
    private TextView categoryName;
    private ImageView imageView;
    private CardView cardView;
    private LinearLayout linearLayout;
    private int lastIndex = 0;

    public HomeCategoryListItemViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.home_category_item_view_holder));
        categoryName = itemView.findViewById(R.id.home_category_name_tv);
        imageView = itemView.findViewById(R.id.home_category_img);
        cardView = itemView.findViewById(R.id.category_item_card);
        linearLayout = itemView.findViewById(R.id.home_category_ll);
    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        HomeCategoryItems item = (HomeCategoryItems) items;
        categoryName.setText(item.getCategory());

        Glide.with(itemView.getContext())
                .load(item.getImgUrl())
                .placeholder(R.drawable.fruit_one)
                .into(imageView);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lastIndex = getAdapterPosition() - 1;
                Log.e("position", String.valueOf(getAdapterPosition()));
                Log.e("index", String.valueOf(lastIndex));
                cardView.setBackground(itemView.getContext().getResources().getDrawable(R.drawable.select_rect_bg));
            }
        });
    }
}