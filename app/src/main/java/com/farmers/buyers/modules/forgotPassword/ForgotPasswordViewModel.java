package com.farmers.buyers.modules.forgotPassword;

import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.app.AppController;
import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.login.model.LoginApiModel;
import com.farmers.buyers.remote.StandardError;
import com.farmers.buyers.storage.SharedPreferenceManager;

public class ForgotPasswordViewModel extends BaseViewModel {

    private ForgotPasswordRepository repository = new ForgotPasswordRepository();
    private AppController appController = AppController.get();

    public void doForgotPassword(final MutableLiveData<DataFetchState<LoginApiModel>> stateMachine, String mobileNumber, String password, String confirm_password, int role) {

        if (password.isEmpty()) {
            stateMachine.postValue(DataFetchState.error("Please enter password", new LoginApiModel()));
            return;
        }
        if (confirm_password.isEmpty()) {
            stateMachine.postValue(DataFetchState.error("Please enter confirm password", new LoginApiModel()));
            return;
        }

        stateMachine.postValue(DataFetchState.<LoginApiModel>loading());

        ForgotPasswordRequestParams loginRequestParams = new ForgotPasswordRequestParams(mobileNumber, password, confirm_password, appController.getDeviceId(),
                role, "android", appController.getAuthenticationKey());

        repository.doForgotPassword(loginRequestParams, new ApiResponseCallback<LoginApiModel>() {
            @Override
            public void onSuccess(LoginApiModel response) {
                if (response.getData() != null) {
                    SharedPreferenceManager.getInstance().setIsLoggedIn(true);
                    SharedPreferenceManager.getInstance().setToken(response.getData().getToken());
                    stateMachine.postValue(DataFetchState.success(response, response.getData().getMessage()));
                } else {
                    stateMachine.postValue(DataFetchState.<LoginApiModel>error(response.getData().getMessage(), null));
                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.<LoginApiModel>error(standardError.getDisplayError(), null));
            }
        });
    }
}
