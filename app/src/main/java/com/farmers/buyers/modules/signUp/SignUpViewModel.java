package com.farmers.buyers.modules.signUp;

import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.login.model.LoginApiModel;
import com.farmers.buyers.modules.signUp.model.SignUpApiModel;
import com.farmers.buyers.modules.signUp.model.SignUpRequestParams;
import com.farmers.buyers.remote.StandardError;
import com.farmers.buyers.storage.SharedPreferenceManager;

/**
 * created by Mohammad Sajjad
 * on 02-02-2021 at 18:43
 * mohammadsajjad679@gmail.com
 */

public class SignUpViewModel extends BaseViewModel {

    private SignUpRepository repository = new SignUpRepository();


    public void doSignUp(final MutableLiveData<DataFetchState<SignUpApiModel>> stateMachine, SignUpRequestParams signUpRequestParams) {

        stateMachine.postValue(DataFetchState.<SignUpApiModel>loading());

        if (signUpRequestParams.getName().isEmpty() ) {
            stateMachine.postValue(DataFetchState.error("Please enter Name", new SignUpApiModel()));
            return;
        }

       else if (signUpRequestParams.getMobile().isEmpty()) {
            stateMachine.postValue(DataFetchState.error("Please enter Mobile number", new SignUpApiModel()));
            return;
        }

        if (signUpRequestParams.getEmail().isEmpty() ) {
            stateMachine.postValue(DataFetchState.error("Please enter Email", new SignUpApiModel()));
            return;
        }

        if (signUpRequestParams.getPassword().isEmpty()) {
            stateMachine.postValue(DataFetchState.error("Please enter password", new SignUpApiModel()));
            return;
        }


        repository.doUserRegis(signUpRequestParams, new ApiResponseCallback<SignUpApiModel>() {
            @Override
            public void onSuccess(SignUpApiModel response) {
                SharedPreferenceManager.getInstance().setIsLoggedIn(true);
                SharedPreferenceManager.getInstance().setToken(response.data.token);
                stateMachine.postValue(DataFetchState.success(response, "Success"));
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.error(standardError.getDisplayError(), new SignUpApiModel()));
            }
        });
    }
}
