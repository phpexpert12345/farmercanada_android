package com.farmers.buyers.modules.seller.product;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseRepository;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.seller.product.models.DeleteProductApiModel;
import com.farmers.buyers.modules.seller.product.models.DeleteProductRequestParams;
import com.farmers.buyers.modules.seller.product.models.ProductListApiModel;
import com.farmers.buyers.modules.seller.product.models.ProductListRequestParams;
import com.farmers.buyers.remote.ApiConstants;
import com.farmers.buyers.remote.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * Created by Mohammad sajjad on 06-03-2021.
 * mohammadsajjad679@gmail.com
 */
public class ProductListRepository extends BaseRepository {

    public void getProductList(ProductListRequestParams params, ApiResponseCallback<ProductListApiModel> responseCallback) {
        Call<ProductListApiModel> call = RetrofitBuilder.createServiceContract().getProductList(ApiConstants.PRODUCT_LIST, params.getLoginId(), params.getFarmId(), params.getAuthKey());
        makeRequest(call, responseCallback);
    }

    public void deleteProductApiModel(DeleteProductRequestParams params, ApiResponseCallback<DeleteProductApiModel> responseCallback) {
        Call<DeleteProductApiModel> call = RetrofitBuilder.createServiceContract().deleteProduct(ApiConstants.DELETE_PRODUCT, params.getProductId(), params.getAuthKey());
        makeRequest(call, responseCallback);
    }

    public void addStockApiModel(DeleteProductRequestParams params, ApiResponseCallback<DeleteProductApiModel> responseCallback) {
        Call<DeleteProductApiModel> call = RetrofitBuilder.createServiceContract().updateStockProduct(ApiConstants.UPDATE_STOCK_QUANTITY,
                params.getLoginId(),
                params.getAuthKey(),
                params.getProductId(),
                params.getQuantity()
        );
        makeRequest(call, responseCallback);
    }
}
