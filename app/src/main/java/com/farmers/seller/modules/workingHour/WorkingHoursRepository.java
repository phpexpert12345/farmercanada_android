package com.farmers.seller.modules.workingHour;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseRepository;
import com.farmers.buyers.modules.home.homeFragment.CategoryListRequestParams;
import com.farmers.buyers.modules.home.models.AllDataModel;
import com.farmers.buyers.remote.ApiConstants;
import com.farmers.buyers.remote.RetrofitBuilder;
import com.farmers.seller.modules.ourOrders.model.AllOrderResponse;
import com.farmers.seller.modules.workingHour.model.WorkingHoursResponse;

import retrofit2.Call;

public class WorkingHoursRepository extends BaseRepository {

    public void getStoreTimeList(CategoryListRequestParams params, ApiResponseCallback<WorkingHoursResponse> responseCallback) {
        Call<WorkingHoursResponse> call = RetrofitBuilder.createServiceContract().getStoreTimeList(ApiConstants.STORE_TIME_LIST,
                params.getUserId(),
                params.getAuthKey(),
                params.getStatus()
        );
        makeRequest(call, responseCallback);
    }

    public void AddUpdateStoreTimeList(CategoryListRequestParams params, ApiResponseCallback<WorkingHoursResponse> responseCallback) {
        Call<WorkingHoursResponse> call = RetrofitBuilder.createServiceContract().addUpdateStoreTimeList(ApiConstants.STORE_TIME_UPDATE,
                params.getUserId(),
                params.getAuthKey(),
                params.getFarm_id(),
                params.getMon_available(),
                params.getMon_start_time(),
                params.getMon_close_time(),
                params.getTue_available(),
                params.getTue_start_time(),
                params.getTue_close_time(),
                params.getWed_available(),
                params.getWed_start_time(),
                params.getWed_close_time(),
                params.getThu_available(),
                params.getThu_start_time(),
                params.getThu_close_time(),
                params.getFri_available(),
                params.getFri_start_time(),
                params.getFri_close_time(),
                params.getSat_available(),
                params.getSat_start_time(),
                params.getSat_close_time(),
                params.getSun_available(),
                params.getSun_start_time(),
                params.getSun_close_time());
        makeRequest(call, responseCallback);
    }
}