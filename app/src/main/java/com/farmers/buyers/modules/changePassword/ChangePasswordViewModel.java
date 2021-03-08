package com.farmers.buyers.modules.changePassword;

import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.app.AppController;
import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.login.model.LoginApiModel;
import com.farmers.buyers.remote.StandardError;

public class ChangePasswordViewModel extends BaseViewModel {

    private ChangePasswordRepository repository = new ChangePasswordRepository();
    private AppController appController = AppController.get();

    public void doChangePassword(final MutableLiveData<DataFetchState<LoginApiModel>> stateMachine,
                                 String new_password, String confirm_password, String Old_Password, String Mobile_OTP, String LoginId) {
        if (Old_Password.isEmpty()) {
            stateMachine.postValue(DataFetchState.error("Please enter old password", new LoginApiModel()));
            return;
        }
        else if(Old_Password.length()<5){
            stateMachine.postValue(DataFetchState.error("Old Password should be minimum 5 digits", new LoginApiModel()));
            return;
        }
        if (new_password.isEmpty()) {
            stateMachine.postValue(DataFetchState.error("Please enter password", new LoginApiModel()));
            return;
        }
        else if(new_password.length()<5){
            stateMachine.postValue(DataFetchState.error("new Password should be minimum 5 digits", new LoginApiModel()));
            return;
        }
      else  if (confirm_password.isEmpty()) {
            stateMachine.postValue(DataFetchState.error("Please enter confirm password", new LoginApiModel()));
            return;
        }
      else if(!new_password.equalsIgnoreCase(confirm_password)){
            stateMachine.postValue(DataFetchState.error("Please enter same password", new LoginApiModel()));
            return;
        }


        stateMachine.postValue(DataFetchState.<LoginApiModel>loading());

        ChangePasswordRequestParams changePasswordRequestParams = new ChangePasswordRequestParams(
                new_password,
                confirm_password,
                Old_Password,
                Mobile_OTP,
                LoginId,
                appController.getAuthenticationKey());

        repository.doChangePassword(changePasswordRequestParams, new ApiResponseCallback<LoginApiModel>() {
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