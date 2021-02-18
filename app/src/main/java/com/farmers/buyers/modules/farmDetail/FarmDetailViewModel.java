package com.farmers.buyers.modules.farmDetail;

import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.app.AppController;
import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.farmDetail.model.farmList.request.FarmProductListReq;
import com.farmers.buyers.modules.farmDetail.model.farmList.response.FarmListProductResponse;
import com.farmers.buyers.modules.home.homeFragment.HomeFragmentRepository;
import com.farmers.buyers.modules.home.models.farmList.FarmListRequest;
import com.farmers.buyers.modules.home.models.farmList.FarmListResponse;
import com.farmers.buyers.remote.StandardError;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/17/2021.
 */
public class FarmDetailViewModel extends BaseViewModel {

    private FarmDetailRepository repository = new FarmDetailRepository();



    public void getFarmProductList(final MutableLiveData<DataFetchState<FarmListProductResponse>> stateMutableLiveData, FarmProductListReq farmProductListReq){
        stateMutableLiveData.postValue(DataFetchState.<FarmListProductResponse>loading());
        repository.getFarmProductList(farmProductListReq, new ApiResponseCallback<FarmListProductResponse>() {
            @Override
            public void onSuccess(FarmListProductResponse response) {
                if (response.getData()!=null)
                    stateMutableLiveData.postValue(DataFetchState.success(response,response.getStatusMessage()));
                else
                    stateMutableLiveData.postValue(DataFetchState.error(response.getStatusMessage(), new FarmListProductResponse()));
            }
            @Override
            public void onFailure(StandardError standardError) {
                stateMutableLiveData.postValue(DataFetchState.<FarmListProductResponse>error(standardError.getDisplayError(), null));

            }
        });

    }

}
