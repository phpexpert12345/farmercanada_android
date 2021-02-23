package com.farmers.buyers.modules.saveFarms;


import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseRepository;
import com.farmers.buyers.modules.saveFarms.model.SaveFarmListApiModel;
import com.farmers.buyers.modules.saveFarms.model.SaveFarmListRequestParams;
import com.farmers.buyers.modules.saveFarms.model.SaveUnSaveFarmRequestModel;
import com.farmers.buyers.modules.saveFarms.model.SaveUnsaveFarmApiModel;
import com.farmers.buyers.remote.ApiConstants;
import com.farmers.buyers.remote.RetrofitBuilder;

import retrofit2.Call;

public class SaveFarmRepository extends BaseRepository {

    public void getSavedFarmList(SaveFarmListRequestParams params, ApiResponseCallback<SaveFarmListApiModel> responseCallback) {

        Call<SaveFarmListApiModel> call = RetrofitBuilder.createServiceContract().getSavedFarmList(ApiConstants.SAVE_FARM_LIST, params.getLoginId(), params.getAuth_key());
        makeRequest(call, responseCallback);
    }

    public void saveUnSaveFarm(SaveUnSaveFarmRequestModel params, ApiResponseCallback<SaveUnsaveFarmApiModel> responseCallback) {
        Call<SaveUnsaveFarmApiModel> call = RetrofitBuilder.createServiceContract().saveUnSaveFarm(ApiConstants.SAVE_UN_SAVE_FARM, params.getLoginId(), params.getAuth_key(), params.getFarm_id(), params.getFarm_favourite_status(), params.getFarm_id());
        makeRequest(call, responseCallback);

    }

}
