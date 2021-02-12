package com.farmers.buyers.modules.signUp;

import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.app.AppController;
import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.signUp.model.SendOtpApiModel;
import com.farmers.buyers.modules.signUp.model.SendOtpRequestParams;
import com.farmers.buyers.modules.signUp.model.SignUpApiModel;
import com.farmers.buyers.modules.signUp.model.SignUpRequestParams;
import com.farmers.buyers.modules.signUp.model.VerifyOtpApiModel;
import com.farmers.buyers.modules.signUp.model.VerifyOtpRequestParams;
import com.farmers.buyers.remote.StandardError;
import com.farmers.buyers.storage.SharedPreferenceManager;

/**
 * created by Mohammad Sajjad
 * on 02-02-2021 at 18:43
 * mohammadsajjad679@gmail.com
 */

public class SignUpViewModel extends BaseViewModel {
    private SignUpRepository repository = new SignUpRepository();
    private AppController controller = AppController.get();


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
                if (response.getStatusCode() == 0) {
                    SharedPreferenceManager.getInstance().setSignUpMobileNumber(response.getData().get(0).getLoginPhone());
                    SharedPreferenceManager.getInstance().setLoginId(response.getData().get(0).getLoginId());
                    SharedPreferenceManager.getInstance().setIsComingFrom(1);
//                    SharedPreferenceManager.getInstance().setIsLoggedIn(true);
                    stateMachine.postValue(DataFetchState.success(response, response.getStatusMessage()));
                }
                else {
                    stateMachine.postValue(DataFetchState.error(response.getStatusMessage(), new SignUpApiModel()));
                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.error(standardError.getDisplayError(), new SignUpApiModel()));
            }
        });
    }


    public void resendOtp(final MutableLiveData<DataFetchState<SendOtpApiModel>> stateMachine, String number) {

        stateMachine.postValue(DataFetchState.<SendOtpApiModel>loading());

        if (number.isEmpty()) {
            stateMachine.postValue(DataFetchState.error("Please Enter mobile number", new SendOtpApiModel()));
            return;
        }

        SendOtpRequestParams params = new SendOtpRequestParams(number, controller.getAuthenticationKey(), controller.getLoginId());

        repository.reSendOtp(params, new ApiResponseCallback<SendOtpApiModel>() {
            @Override
            public void onSuccess(SendOtpApiModel response) {
                if (response.isStatusCode()) {
                    stateMachine.postValue(DataFetchState.success(response, response.getStatusMessage()));
                }
                else  {
                    stateMachine.postValue(DataFetchState.error(response.getStatusMessage(), new SendOtpApiModel()));

                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.error(standardError.getDisplayError(), new SendOtpApiModel()));

            }
        });
    }


    public void verifyOtp(final MutableLiveData<DataFetchState<VerifyOtpApiModel>> stateMachine, String otp) {
        stateMachine.postValue(DataFetchState.<VerifyOtpApiModel>loading());

        if (otp.length() < 4) {
            stateMachine.postValue(DataFetchState.error("Otp is not valid", new VerifyOtpApiModel()));
            return;
        }


        VerifyOtpRequestParams params = new VerifyOtpRequestParams(controller.getUserId(), otp);

        repository.verifyOtp(params, new ApiResponseCallback<VerifyOtpApiModel>() {
            @Override
            public void onSuccess(VerifyOtpApiModel response) {
                stateMachine.postValue(DataFetchState.success(response, "Success"));
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.error(standardError.getDisplayError(), new VerifyOtpApiModel()));
            }
        });
    }


}
