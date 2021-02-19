package com.farmers.buyers.modules.seller.product;

import com.farmers.buyers.modules.seller.product.models.ProductListItems;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 08-02-2021 at 16:11
 * mohammadsajjad679@gmail.com
 */

public class ProductListTransformer {

    public static List<ProductListItems> getProducts() {
        List<ProductListItems> items = new ArrayList<>();
        items.add(new ProductListItems());
        items.add(new ProductListItems());
        items.add(new ProductListItems());
        items.add(new ProductListItems());
        items.add(new ProductListItems());
        items.add(new ProductListItems());
        items.add(new ProductListItems());
        items.add(new ProductListItems());
        items.add(new ProductListItems());
        items.add(new ProductListItems());
        return items;
    }

}
