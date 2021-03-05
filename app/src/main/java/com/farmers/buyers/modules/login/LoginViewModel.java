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
            stateMachine.postValue(DataFetchState.error("Please enter mobile number", new LoginApiModel()));
            return;
        }

        if (password.isEmpty()) {
            stateMachine.postValue(DataFetchState.error("Please enter password", new LoginApiModel()));
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
        http://farmercanada.com/buyer_api/phpexpert_login.php?account_email=9899096063&account_password=12345&account_type=2&device_id=eJS07f35RsyO8asoHH0h4q:APA91bGmgmja0Gbe7rG_PITuBTdEeKogeFxCzWoSgZZXAFzJBnGh9ZxYQHRdENrB_LW-Vo1xObN6joVhS-ve4pYSXRHssPQXmo0rWduR2ghUh97nUahuxyxRzwgLzwE-EyjF1l858Vhh&device_platform=Android&auth_key=dH_8Rpyla-E:APA91bFNJsbcya80FU0SrsxtzEXR3qdK100EzdZykXtpQCkZgzp10tfaSC7PjDFSn7JJ6eFsAObjAN6Y_fr4kO8PnhW2PcMkjYVjDZODnf7Iou0AgYYWh0vFRolOVB5d1UTYRfC7gdSB

        repository.doLogin(loginRequestParams, new ApiResponseCallback<LoginApiModel>() {
            @Override
            public void onSuccess(LoginApiModel response) {
                if (response.isStatus()) {
                    SharedPreferenceManager.getInstance().setIsLoggedIn(true);
                    SharedPreferenceManager.getInstance().setLoginId(response.getData().getLoginId());
                    SharedPreferenceManager.getInstance().setSharedPreference("", response.getData().getLoginId());
                    SharedPreferenceManager.getInstance().setRole(response.getData().getAccountTypeName());
                    SharedPreferenceManager.getInstance().setIsStoreSetup(response.getData().getStoreSetupStatus().equals("1"));
                    userType = response.getData().getAccountTypeName();
                    isStoreSetup = response.getData().getStoreSetupStatus();

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