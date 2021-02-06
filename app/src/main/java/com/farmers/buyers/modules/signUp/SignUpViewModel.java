package com.farmers.buyers.modules.signUp;

import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.login.model.LoginApiModel;
import com.farmers.buyers.modules.signUp.model.SignUpApiModel;
import com.farmers.buyers.modules.signUp.model.SignUpRequestParams;
import com.farmers.buyers.remote.StandardError;

/**
 * created by Mohammad Sajjad
 * on 02-02-2021 at 18:43
 * mohammadsajjad679@gmail.com
 */

public class SignUpViewModel extends BaseViewModel {

    private SignUpRepository repository = new SignUpRepository();

    public void doSignUp(final MutableLiveData<DataFetchState<SignUpApiModel>> stateMachine) {

        stateMachine.postValue(DataFetchState.<SignUpApiModel>loading());

        SignUpRequestParams params = new SignUpRequestParams("","", "", "");

        repository.doUserSignUp(params, new ApiResponseCallback<SignUpApiModel>() {
            @Override
            public void onSuccess(SignUpApiModel response) {
                stateMachine.postValue(DataFetchState.success(response, "Success"));
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.error(standardError.getDisplayError(), new SignUpApiModel()));
            }
        });
    }
}
