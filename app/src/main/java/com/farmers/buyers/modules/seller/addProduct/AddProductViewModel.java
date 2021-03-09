package com.farmers.buyers.modules.seller.addProduct;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.app.AppController;
import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.home.homeFragment.CategoryListRequestParams;
import com.farmers.buyers.modules.home.homeFragment.HomeFragmentRepository;
import com.farmers.buyers.modules.home.models.AllDataModel;
import com.farmers.buyers.modules.seller.addProduct.model.AddProductApiResponseModel;
import com.farmers.buyers.modules.seller.addProduct.model.AddProductRequestParams;
import com.farmers.buyers.modules.seller.addProduct.model.CategoryItem;
import com.farmers.buyers.remote.StandardError;
import com.farmers.buyers.storage.SharedPreferenceManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohammad sajjad on 06-03-2021.
 * mohammadsajjad679@gmail.com
 */
public class AddProductViewModel extends BaseViewModel {
    private HomeFragmentRepository homeRepository = new HomeFragmentRepository();
    private AddProductRepository repository = new AddProductRepository();
    private AppController appController = AppController.get();
    public List<CategoryItem> categoryList = new ArrayList<>();
    private CategoryItem selectedCategory;

    public void getCategoryList(final MutableLiveData<DataFetchState<AllDataModel>> stateMachine) {
        stateMachine.postValue(DataFetchState.<AllDataModel>loading());

        CategoryListRequestParams loginRequestParams = new CategoryListRequestParams(appController.getAuthenticationKey());
        homeRepository.getCategoryList(loginRequestParams, new ApiResponseCallback<AllDataModel>() {
            @Override
            public void onSuccess(AllDataModel response) {
                if (response.isStatus()) {
                    categoryList.addAll(AddProductTransformer.transformApiModelToCategoryItem(response.getmData().CategoryList));
                    stateMachine.postValue(DataFetchState.success(response, response.getStatus_message()));
                } else {
                    stateMachine.postValue(DataFetchState.<AllDataModel>error(response.getStatus_message(), null));
                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.<AllDataModel>error(standardError.getDisplayError(), null));
            }
        });
    }

    public void addProduct(MutableLiveData<DataFetchState<AddProductApiResponseModel>> stateMachine, String name, String salesPrice, String unitPrice, String note, String units, String counter, File image) {

        if (name.isEmpty()) {
            stateMachine.postValue(DataFetchState.error("Please enter product name", new AddProductApiResponseModel()));
            return;
        }
        if (unitPrice.isEmpty()) {
            stateMachine.postValue(DataFetchState.error("Please enter Unit Price", new AddProductApiResponseModel()));
            return;
        }
        if (salesPrice.isEmpty()) {
            stateMachine.postValue(DataFetchState.error("Please enter Sales Price", new AddProductApiResponseModel()));
            return;
        }
        if (units.isEmpty()) {
            stateMachine.postValue(DataFetchState.error("Please enter Units", new AddProductApiResponseModel()));
            return;
        }
        if (note.isEmpty()) {
            stateMachine.postValue(DataFetchState.error("Please enter Note", new AddProductApiResponseModel()));
            return;
        }

        if (selectedCategory == null) {
            stateMachine.postValue(DataFetchState.error("Please Select category", new AddProductApiResponseModel()));
            return;
        }

        if (image == null) {
            stateMachine.postValue(DataFetchState.error("Please Select image", new AddProductApiResponseModel()));
            return;
        }

        stateMachine.postValue(DataFetchState.loading());

        AddProductRequestParams params = new AddProductRequestParams(name, counter, units, selectedCategory.getId(), unitPrice, salesPrice, note, SharedPreferenceManager.getInstance().getFarmId(), appController.getLoginId(), appController.getAuthenticationKey(), image);

        repository.addProduct(params, new ApiResponseCallback<AddProductApiResponseModel>() {
            @Override
            public void onSuccess(AddProductApiResponseModel response) {
                if (response.getStatus()) {
                    stateMachine.postValue(DataFetchState.success(response, response.getStatusMessage()));
                }
                else {
                    stateMachine.postValue(DataFetchState.error(response.getStatusMessage(), new AddProductApiResponseModel()));

                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.error(standardError.getDisplayError(), new AddProductApiResponseModel()));
            }
        });




    }

    public void onCategorySelected(CategoryItem data) {
        this.selectedCategory = data;
        Log.e("cat", selectedCategory.getName());
    }




}
