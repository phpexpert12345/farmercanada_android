package com.farmers.buyers.modules.seller.product;

import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.app.App;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.seller.product.models.DeleteProductApiModel;
import com.farmers.buyers.modules.seller.product.models.DeleteProductRequestParams;
import com.farmers.buyers.modules.seller.product.models.ProductListApiModel;
import com.farmers.buyers.modules.seller.product.models.ProductListItems;
import com.farmers.buyers.modules.seller.product.models.ProductListRequestParams;
import com.farmers.buyers.modules.seller.product.models.SubProductItemsRecordSeller;
import com.farmers.buyers.remote.StandardError;
import com.farmers.buyers.storage.SharedPreferenceManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohammad sajjad on 06-03-2021.
 * mohammadsajjad679@gmail.com
 */
public class ProductListViewModel extends BaseViewModel {
    private ProductListRepository repository = new ProductListRepository();
    public List<RecyclerViewListItem> items = new ArrayList<>();
    public List<ProductListItems> productItems = new ArrayList<>();
    private AppController appController = AppController.get();
    public ProductListItems selectedProduct = null;


    public void getProductList(MutableLiveData<DataFetchState<ProductListApiModel>> stateMachine) {
        stateMachine.postValue(DataFetchState.loading());
        productItems.clear();
        items.clear();

        ProductListRequestParams params = new ProductListRequestParams(appController.getLoginId(), SharedPreferenceManager.getInstance().getFarmId(), appController.getAuthenticationKey());

        repository.getProductList(params, new ApiResponseCallback<ProductListApiModel>() {
            @Override
            public void onSuccess(ProductListApiModel response) {
                if (response.getStatus()) {
                    if (!response.getData().getSubProductItemsRecordSeller().isEmpty()) {
                        productItems.addAll(ProductListTransformer.getProducts(response));
                        items.addAll(ProductListTransformer.getProducts(response));
                        stateMachine.postValue(DataFetchState.success(response, response.getStatusMessage()));
                    }
                    else {
                        stateMachine.postValue(DataFetchState.error(response.getStatusMessage(), new ProductListApiModel()));
                    }

                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.error(standardError.getDisplayError(), new ProductListApiModel()));
            }
        });
    }

    public void deleteProduct(MutableLiveData<DataFetchState<DeleteProductApiModel>> stateMachine){
        stateMachine.postValue(DataFetchState.loading());

        DeleteProductRequestParams params = new DeleteProductRequestParams(selectedProduct.getProductId(), appController.getAuthenticationKey());

        repository.deleteProductApiModel(params, new ApiResponseCallback<DeleteProductApiModel>() {
            @Override
            public void onSuccess(DeleteProductApiModel response) {
                if (response.getStatus()) {
                    stateMachine.postValue(DataFetchState.success(response, response.getStatusMessage()));
                }
                else {
                    stateMachine.postValue(DataFetchState.error(response.getStatusMessage(), new DeleteProductApiModel()));

                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.error(standardError.getDisplayError(), new DeleteProductApiModel()));
            }
        });
    }

    public void onProductDelete(MutableLiveData<DataFetchState<DeleteProductApiModel>> stateMachine, int position) {
        selectedProduct = productItems.get(position);
        deleteProduct(stateMachine);
    }


}
