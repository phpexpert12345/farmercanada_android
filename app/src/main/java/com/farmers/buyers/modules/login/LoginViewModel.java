package com.farmers.buyers.modules.login;

import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.app.AppController;
import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.login.model.LoginApiModel;
import com.farmers.buyers.modules.login.model.LoginRequestParams;
import com.farmers.buyers.remote.StandardError;
import com.farmers.buyers.storage.SharedPreferenceManager;

/**
 * created by Mohammad Sajjad
 * on 29-01-2021 at 15:03
 * mohammadsajjad679@gmail.com
 */

public class LoginViewModel extends BaseViewModel {

    private LoginRepository repository = new LoginRepository();
    private AppController appController = AppController.get();
    public String userType;
    public String isStoreSetup;

    public void doLogin(final MutableLiveData<DataFetchState<LoginApiModel>> stateMachine, String email,
                        String password, int role, String FirebaseToken) {

        if (email.isEmpty() || email.length() < 10) {
            stateMachine.postValue(DataFetchState.error("Please enter valid mobile number", new LoginApiModel()));
            return;
        }

      else  if (password.isEmpty()) {
            stateMachine.postValue(DataFetchState.error("Please enter password", new LoginApiModel()));
            return;
        }
        else if(password.length()<5){
            stateMachine.postValue(DataFetchState.error("Password should  be minimum 5 digit", new LoginApiModel()));
            return;
        }

        stateMachine.postValue(DataFetchState.<LoginApiModel>loading());

        LoginRequestParams loginRequestParams = new LoginRequestParams(
                email,
                password,
                FirebaseToken,
                role,
                "Android",
                appController.getAuthenticationKey());

        repository.doLogin(loginRequestParams, new ApiResponseCallback<LoginApiModel>() {
            @Override
            public void onSuccess(LoginApiModel response) {
                if (response.isStatus()) {
                    SharedPreferenceManager.getInstance().setIsLoggedIn(true);
                    SharedPreferenceManager.getInstance().setLoginId(response.getData().getLoginId());
                    SharedPreferenceManager.getInstance().setSharedPreference("", response.getData().getLoginId());
                    SharedPreferenceManager.getInstance().setRole(response.getData().getAccountTypeName());
                    SharedPreferenceManager.getInstance().setFarmId(response.getData().getFarmId());
                    isStoreSetup = response.getData().getStoreSetupStatus(); //"store_setup_status": "Yes",
                    if (response.getData().getStoreSetupStatus().equals("Yes")) {
                        SharedPreferenceManager.getInstance().setIsStoreSetup(true);
                    }
                    userType = response.getData().getAccountTypeName();

                    stateMachine.postValue(DataFetchState.success(response, response.getStatus_message()));
                } else {
                    stateMachine.postValue(DataFetchState.<LoginApiModel>error(response.getStatus_message(), null));
                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.<LoginApiModel>error(standardError.getDisplayError(), null));
            }

        });

    }
}