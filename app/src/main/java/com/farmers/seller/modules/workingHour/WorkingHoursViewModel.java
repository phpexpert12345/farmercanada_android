
package com.farmers.seller.modules.workingHour;

import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.app.AppController;
import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.home.homeFragment.CategoryListRequestParams;
import com.farmers.buyers.remote.StandardError;
import com.farmers.seller.modules.workingHour.model.WorkingHoursResponse;
import com.farmers.seller.modules.workingHour.storeTime.StoreTimeSetup;

import java.util.ArrayList;
import java.util.List;

public class WorkingHoursViewModel extends BaseViewModel {

    private WorkingHoursRepository repository = new WorkingHoursRepository();
    private AppController appController = AppController.get();
    public List<RecyclerViewListItem> items = new ArrayList<>();
    public List<RecyclerViewListItem> weekItems = new ArrayList<>();

    public void getStoreTimeList(final MutableLiveData<DataFetchState<WorkingHoursResponse>> stateMachine) {
        stateMachine.postValue(DataFetchState.<WorkingHoursResponse>loading());
        CategoryListRequestParams loginRequestParams = new CategoryListRequestParams(
                appController.getLoginId(),
                appController.getAuthenticationKey(),
                appController.getFarmId());

        repository.getStoreTimeList(loginRequestParams, new ApiResponseCallback<WorkingHoursResponse>() {
            @Override
            public void onSuccess(WorkingHoursResponse response) {
                items.clear();
                if (response.isStatus()) {
                   // items.addAll(WorkingHourTransformer.getStoreTimeList());
                   // weekItems.addAll(WorkingHourTransformer.getWeekDaysList());
                    stateMachine.postValue(DataFetchState.success(response, response.getStatus_message()));
                } else {
                    stateMachine.postValue(DataFetchState.<WorkingHoursResponse>error(response.getStatus_message(), null));
                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.<WorkingHoursResponse>error(standardError.getDisplayError(), null));
            }
        });
    }

    public void AddUpdateStoreTimeList(final MutableLiveData<DataFetchState<WorkingHoursResponse>> stateMachine, StoreTimeSetup extract) {
        stateMachine.postValue(DataFetchState.<WorkingHoursResponse>loading());
        CategoryListRequestParams loginRequestParams = new CategoryListRequestParams(
                appController.getLoginId(),
                appController.getAuthenticationKey(),
                appController.getFarmId(),
                extract.getMon_available(),
                extract.getMon_start_time(),
                extract.getMon_close_time(),
                extract.getTue_available(),
                extract.getTue_start_time(),
                extract.getTue_close_time(),
                extract.getWed_available(),
                extract.getWed_start_time(),
                extract.getWed_close_time(),
                extract.getThu_available(),
                extract.getThu_start_time(),
                extract.getThu_close_time(),
                extract.getFri_available(),
                extract.getFri_start_time(),
                extract.getFri_close_time(),
                extract.getSat_available(),
                extract.getSat_start_time(),
                extract.getSat_close_time(),
                extract.getSun_available(),
                extract.getSun_start_time(),
                extract.getSun_close_time()
        );

        repository.AddUpdateStoreTimeList(loginRequestParams, new ApiResponseCallback<WorkingHoursResponse>() {
            @Override
            public void onSuccess(WorkingHoursResponse response) {
                items.clear();
                if (response.isStatus()) {
                    stateMachine.postValue(DataFetchState.success(response, response.getStatus_message()));
                } else {
                    stateMachine.postValue(DataFetchState.<WorkingHoursResponse>error(response.getStatus_message(), null));
                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.<WorkingHoursResponse>error(standardError.getDisplayError(), null));
            }
        });
    }
}