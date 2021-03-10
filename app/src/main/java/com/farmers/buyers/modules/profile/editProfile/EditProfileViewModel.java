package com.farmers.buyers.modules.profile.editProfile;

import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.app.AppController;
import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.addMoney.AddMoneyRepository;
import com.farmers.buyers.modules.addMoney.model.AddMoneyRequestParams;
import com.farmers.buyers.modules.signUp.model.SignUpApiModel;
import com.farmers.buyers.remote.StandardError;

public class EditProfileViewModel extends BaseViewModel {

    private EditProfileRepository repository = new EditProfileRepository();
    private AppController controller = AppController.get();

    public void editProfile(final MutableLiveData<DataFetchState<SignUpApiModel>> stateMachine, AddMoneyRequestParams addMoneyRequestParams) {

        stateMachine.postValue(DataFetchState.<SignUpApiModel>loading());

        if (addMoneyRequestParams.getAccount_name().isEmpty()) {
            stateMachine.postValue(DataFetchState.error("Please enter Name", new SignUpApiModel()));
            return;
        } else if (addMoneyRequestParams.getAccount_email().isEmpty()) {
            stateMachine.postValue(DataFetchState.error("Please enter email address", new SignUpApiModel()));
            return;
        }


        repository.editProfile(addMoneyRequestParams, new ApiResponseCallback<SignUpApiModel>() {
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
