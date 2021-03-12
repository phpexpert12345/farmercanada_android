package com.farmers.buyers.modules.splash;

import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.remote.StandardError;
import com.farmers.buyers.storage.SharedPreferenceManager;

import java.util.Currency;

/**
 * created by Mohammad Sajjad
 * on 12-02-2021 at 15:27
 * mohammadsajjad679@gmail.com
 */

public class SplashViewModel extends BaseViewModel {

    private SplashRepository repository = new SplashRepository();


    public void authenticateUser(final MutableLiveData<DataFetchState<AuthenticationApiModel>> stateMachine) {
        stateMachine.postValue(DataFetchState.<AuthenticationApiModel>loading());

        repository.authenticateUser(new ApiResponseCallback<AuthenticationApiModel>() {
            @Override
            public void onSuccess(AuthenticationApiModel response) {
                if (response.getStatusCode() == 0) {
                    SharedPreferenceManager.getInstance().setAuthenticationKey(response.getData().get(0).getAuthKey());
                    SharedPreferenceManager.getInstance().setGoogleApiKey(response.getData().get(0).getGoogleMapKey());
                    try {
                        Currency currency = Currency.getInstance(response.getData().get(0).getCurrencyCode());
                        SharedPreferenceManager.getInstance().setCurrencyCode(currency.getSymbol());
                    } catch (Exception e) {
                        SharedPreferenceManager.getInstance().setCurrencyCode(response.getData().get(0).getCurrencyCode());
                    }

                    stateMachine.postValue(DataFetchState.success(response, response.getStatusMessage()));
                } else {
                    stateMachine.postValue(DataFetchState.error(response.getStatusMessage(), new AuthenticationApiModel()));
                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.error(standardError.getDisplayError(), new AuthenticationApiModel()));

            }
        });

    }

}
