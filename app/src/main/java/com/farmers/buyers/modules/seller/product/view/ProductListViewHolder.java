package com.farmers.buyers.modules.seller.product.view;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;

/**
 * created by Mohammad Sajjad
 * on 08-02-2021 at 16:21
 * mohammadsajjad679@gmail.com
 */

public class ProductListViewHolder extends BaseViewHolder {


    public ProductListViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.product_list_item_layout));
    }

    @Override
    public void bindView(RecyclerViewListItem items) {

    }
}
