package com.farmers.buyers.modules.addMoney;

import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.app.AppController;
import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.addMoney.model.AddMoneyRequestParams;
import com.farmers.buyers.modules.signUp.SignUpRepository;
import com.farmers.buyers.modules.signUp.model.SendOtpApiModel;
import com.farmers.buyers.modules.signUp.model.SendOtpRequestParams;
import com.farmers.buyers.modules.signUp.model.SignUpApiModel;
import com.farmers.buyers.modules.signUp.model.SignUpRequestParams;
import com.farmers.buyers.modules.signUp.model.VerifyOtpApiModel;
import com.farmers.buyers.modules.signUp.model.VerifyOtpRequestParams;
import com.farmers.buyers.remote.StandardError;
import com.farmers.buyers.storage.SharedPreferenceManager;

public class AddMoneyViewModel extends BaseViewModel {

    private AddMoneyRepository repository = new AddMoneyRepository();
    private AppController controller = AppController.get();

    public void addMoney(final MutableLiveData<DataFetchState<SignUpApiModel>> stateMachine, AddMoneyRequestParams addMoneyRequestParams) {

        stateMachine.postValue(DataFetchState.<SignUpApiModel>loading());

        if (addMoneyRequestParams.getWallet_amount().isEmpty()) {
            stateMachine.postValue(DataFetchState.error("Please enter Amount", new SignUpApiModel()));
            return;
        } else if (addMoneyRequestParams.getWallet_transation_id().isEmpty()) {
            stateMachine.postValue(DataFetchState.error("Please enter Transaction Id", new SignUpApiModel()));
            return;
        }
        if (addMoneyRequestParams.getWallet_transation_status().isEmpty()) {
            stateMachine.postValue(DataFetchState.error("Please enter Status", new SignUpApiModel()));
            return;
        }
        repository.addMoney(addMoneyRequestParams, new ApiResponseCallback<SignUpApiModel>() {
            @Override
            public void onSuccess(SignUpApiModel response) {
                if (response.isStatus()) {
                    stateMachine.postValue(DataFetchState.success(response, response.getStatusMessage()));
                } else {
                    stateMachine.postValue(DataFetchState.error(response.getStatusMessage(), new SignUpApiModel()));
                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.error(standardError.getDisplayError(), new SignUpApiModel()));
            }
        });
    }
}
