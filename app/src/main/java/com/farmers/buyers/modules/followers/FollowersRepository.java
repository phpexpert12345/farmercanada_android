package com.farmers.buyers.modules.followers;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseRepository;
import com.farmers.buyers.modules.followers.model.FollowUnFollowApiModel;
import com.farmers.buyers.modules.followers.model.FollowUnFollowRequestParams;
import com.farmers.buyers.modules.followers.model.FollowersApiModel;
import com.farmers.buyers.modules.followers.model.FollowersRequestParams;
import com.farmers.buyers.remote.ApiConstants;
import com.farmers.buyers.remote.RetrofitBuilder;

import retrofit2.Call;

public class FollowersRepository extends BaseRepository {

    public void getFollowersList(FollowersRequestParams params, ApiResponseCallback<FollowersApiModel> responseCallback) {
        Call<FollowersApiModel> call = RetrofitBuilder.createServiceContract().getFollowersList(ApiConstants.FOLLOWERS_LIST, params.getUserId(), params.getAuthKey());
        makeRequest(call, responseCallback);
    }


    public void followUnFollowFarm(FollowUnFollowRequestParams params, ApiResponseCallback<FollowUnFollowApiModel> responseCallback) {
        Call<FollowUnFollowApiModel> call = RetrofitBuilder.createServiceContract().followUnFollowFarm(ApiConstants.FOLLOW_UNFOLLOW_USER, params.getLoginId(), params.getAuthKey(), params.getFarmId(), params.getStatus(), params.getFollowId());
        makeRequest(call, responseCallback);

    }

}
