package com.farmers.buyers.modules.login;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.login.model.LoginApiModel;
import com.farmers.buyers.modules.login.model.LoginRequestParams;
import com.farmers.buyers.remote.StandardError;

/**
 * created by Mohammad Sajjad
 * on 29-01-2021 at 15:03
 * mohammadsajjad679@gmail.com
 */

public class LoginViewModel extends BaseViewModel {

    private LoginRepository repository = new LoginRepository();

    public void doLogin(final MutableLiveData<DataFetchState<LoginApiModel>> stateMachine) {

        stateMachine.postValue(DataFetchState.<LoginApiModel>loading());
        LoginRequestParams params = new LoginRequestParams("8273086442", "1234");

        repository.doLogin(params, new ApiResponseCallback<LoginApiModel>() {
            @Override
            public void onSuccess(LoginApiModel response) {
                Log.e("response", response.toString());
                stateMachine.postValue(DataFetchState.success(response, ""));
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.<LoginApiModel>error(standardError.getDisplayError(), null));
            }

        });

    }
}
