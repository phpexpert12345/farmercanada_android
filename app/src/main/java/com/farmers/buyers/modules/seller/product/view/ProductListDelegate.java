package com.farmers.buyers.modules.seller.product.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

/**
 * created by Mohammad Sajjad
 * on 08-02-2021 at 16:20
 * mohammadsajjad679@gmail.com
 */

public class ProductListDelegate extends BaseDelegate {
    private ProductListViewHolder.ProductListItemClickListener listItemClickListener;

    public ProductListDelegate(ProductListViewHolder.ProductListItemClickListener listItemClickListener) {
        this.listItemClickListener = listItemClickListener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ProductListViewHolder(parent, listItemClickListener);
    }
}
