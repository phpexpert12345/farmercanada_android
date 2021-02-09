package com.farmers.buyers.modules.seller.product.adapter;

import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.modules.seller.product.view.ProductListDelegate;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 08-02-2021 at 16:20
 * mohammadsajjad679@gmail.com
 */

public class ProductListAdapter extends BaseAdapter {

    public ProductListAdapter() {
        this.initDelegate();
    }

    @Override
    public void initDelegate() {
        delegates.put(CardConstant.PRODUCT_LIST_ADAPTER, new ProductListDelegate());
    }
}
