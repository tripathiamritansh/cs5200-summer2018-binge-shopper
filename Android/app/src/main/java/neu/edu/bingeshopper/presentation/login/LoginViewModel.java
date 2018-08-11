package neu.edu.bingeshopper.presentation.login;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import neu.edu.bingeshopper.Repository.Model.Repository;
import neu.edu.bingeshopper.Repository.UserRepository;

public class LoginViewModel extends ViewModel {

    private UserRepository repository;
    private MutableLiveData<LoginViewModelResponse> responseMutableLiveData;

    @Inject
    public LoginViewModel(UserRepository repository) {
        this.repository = repository;
    }

    public MutableLiveData<LoginViewModelResponse> getResponseMutableLiveData() {
        if (responseMutableLiveData == null) {
            responseMutableLiveData = new MutableLiveData<>();
        }
        return responseMutableLiveData;
    }

    public void loginUser(String username, String password) {

        repository.login(username, password, new Repository.RepositoryCallBack<UserRepository.UserRespositoryResponse>() {
            @Override
            public void onSuccess(UserRepository.UserRespositoryResponse response) {
                responseMutableLiveData.setValue(new LoginViewModelResponse(Status.Success, null));
            }

            @Override
            public void onError(UserRepository.UserRespositoryResponse response) {
                responseMutableLiveData.setValue(new LoginViewModelResponse(Status.Error, response.getMessage()));
            }
        });

    }

    public class LoginViewModelResponse {

        private Status status;
        private String message;

        public LoginViewModelResponse(Status status, @Nullable String message) {
            this.status = status;
            this.message = message;
        }

        public Status getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }
    }

    public enum Status {
        Success,
        Error

    }
}
