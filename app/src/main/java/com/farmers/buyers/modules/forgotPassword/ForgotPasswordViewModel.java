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

    public void doVerifyMobileForgotPassword(final MutableLiveData<DataFetchState<LoginApiModel>> stateMachine, String mobileNumber, int role) {

        if (mobileNumber.isEmpty()) {
            stateMachine.postValue(DataFetchState.error("Please enter password", new LoginApiModel()));
            return;
        }

        stateMachine.postValue(DataFetchState.<LoginApiModel>loading());

        ForgotPasswordRequestParams loginRequestParams = new ForgotPasswordRequestParams(mobileNumber, role,
                appController.getAuthenticationKey());

        repository.doVerifyForgotPassword(loginRequestParams, new ApiResponseCallback<LoginApiModel>() {
            @Override
            public void onSuccess(LoginApiModel response) {
                if (response.isStatus()) {
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

    public void doForgotPassword(final MutableLiveData<DataFetchState<LoginApiModel>> stateMachine, String otp, String user_d, String password,
                                 String confirm_password) {

        if (password.isEmpty()) {
            stateMachine.postValue(DataFetchState.error("Please enter password", new LoginApiModel()));
            return;
        }

        if (confirm_password.isEmpty()) {
            stateMachine.postValue(DataFetchState.error("Please enter confirm password", new LoginApiModel()));
            return;
        }

        stateMachine.postValue(DataFetchState.<LoginApiModel>loading());

        ForgotPasswordRequestParams loginRequestParams = new ForgotPasswordRequestParams(otp, user_d, password,
                confirm_password, appController.getAuthenticationKey());

        repository.doForgotPassword(loginRequestParams, new ApiResponseCallback<LoginApiModel>() {
            @Override
            public void onSuccess(LoginApiModel response) {
                if (response.isStatus()) {
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