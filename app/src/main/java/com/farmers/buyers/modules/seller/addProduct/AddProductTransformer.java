package com.farmers.buyers.modules.seller.addProduct;

import com.farmers.buyers.modules.home.models.AllDataModel;
import com.farmers.buyers.modules.seller.addProduct.model.CategoryItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohammad sajjad on 06-03-2021.
 * mohammadsajjad679@gmail.com
 */
public class AddProductTransformer {

    public static List<CategoryItem> transformApiModelToCategoryItem(List<AllDataModel.Data> apiData) {
        List<CategoryItem> items = new ArrayList<>();
        items.add(new CategoryItem("-1", "Select Category"));
        for (int i = 0 ; i< apiData.size(); i++) {
            items.add(new CategoryItem(apiData.get(i).getCategory_id(), apiData.get(i).getCategory_name()));
        }
        return items;
    }
}
