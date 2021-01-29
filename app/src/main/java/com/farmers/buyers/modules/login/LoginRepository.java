package com.farmers.buyers.modules.login;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseRepository;
import com.farmers.buyers.modules.login.model.LoginApiModel;
import com.farmers.buyers.modules.login.model.LoginRequestParams;
import com.farmers.buyers.remote.RetrofitBuilder;

import retrofit2.Call;

/**
 * created by Mohammad Sajjad
 * on 29-01-2021 at 15:02
 * mohammadsajjad679@gmail.com
 */

public class LoginRepository extends BaseRepository {


    public void doLogin(LoginRequestParams params, ApiResponseCallback<LoginApiModel> responseCallback) {
        Call<LoginApiModel> call = RetrofitBuilder.createServiceContract().getUserLogin(params);
        makeRequest(call, responseCallback);
    }


/*
    public LiveData<Resources<LoginApiModel>> getLogin(LoginApiModel response) {
        MutableLiveData<Resources<LoginApiModel>> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(Resources.loading(new LoginApiModel()));

        ((Call<LoginApiModel>) apiCall.getFetchData(requestEntity)).enqueue(new Callback<ResponseEntity>() {
            @Override
            public void onResponse(@NotNull Call<ResponseEntity> call, @NotNull Response<ResponseEntity> response) {
                try {
                    LoginApiModel entity = response.body();
                    switch (response.code()) {
                        case 200:
                            mutableLiveData.setValue(Resources.success(entity, entity.getMessage()));
                            break;
                        case 201:
                            mutableLiveData.setValue(Resources.success(entity, entity.getMessage()));
                            break;
                        default:
                            mutableLiveData.setValue(Resources.error(response.toString(), new ResponseEntity()));
                            break;
                    }
                } catch (Exception e) {
                    mutableLiveData.setValue(Resources.error(e.getMessage(), new ResponseEntity()));
                }
            }

            @Override
            public void onFailure(Call<ResponseEntity> call, Throwable t) {
                mutableLiveData.setValue(Resources.error(t.toString(), new ResponseEntity()));
            }
        });
        return mutableLiveData;
    }
*/

}
