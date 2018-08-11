package neu.edu.bingeshopper.presentation.signup;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import neu.edu.bingeshopper.Repository.Model.User;
import neu.edu.bingeshopper.Repository.UserRepository;

public class SignUpViewModel extends ViewModel {

    private UserRepository repository;
    private MutableLiveData<SignUpViewModelResponse> responseMutableLiveData;

    @Inject
    public SignUpViewModel(UserRepository repository) {
        this.repository = repository;
    }

    public MutableLiveData<SignUpViewModelResponse> getResponseMutableLiveData() {

        if (responseMutableLiveData == null) {
            responseMutableLiveData = new MutableLiveData<>();
        }
        return responseMutableLiveData;

    }

    public void signUpUser(User user) {
        repository.signUp(user, new UserRepository.RepositoryCallBack<UserRepository.UserRepositoryResponse>() {
            @Override
            public void onSuccess(UserRepository.UserRepositoryResponse response) {
                responseMutableLiveData.setValue(new SignUpViewModelResponse(Status.Success, null));
            }

            @Override
            public void onError(UserRepository.UserRepositoryResponse response) {
                responseMutableLiveData.setValue(new SignUpViewModelResponse(Status.Error, response.getMessage()));
            }
        });
    }

    class SignUpViewModelResponse {

        private Status status;
        private String message;

        public SignUpViewModelResponse(Status status, @Nullable String message) {
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

    enum Status {
        Success,
        Error

    }
}
