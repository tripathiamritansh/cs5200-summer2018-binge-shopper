package neu.edu.bingeshopper.presentation.profile;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import neu.edu.bingeshopper.Repository.Model.Repository;
import neu.edu.bingeshopper.Repository.Model.User;
import neu.edu.bingeshopper.Repository.UserRepository;

public class ProfileViewModel extends ViewModel {

    private UserRepository repository;

    private MutableLiveData<ProfileViewModelResponse> responseMutableLiveData;

    @Inject
    public ProfileViewModel(UserRepository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ProfileViewModelResponse> getResponseMutableLiveData() {
        if (responseMutableLiveData == null) {
            responseMutableLiveData = new MutableLiveData<>();
        }
        return responseMutableLiveData;
    }

    public void updateProfile(User user) {
        repository.updateUser(user, new Repository.RepositoryCallBack<UserRepository.UserRepositoryResponse>() {
            @Override
            public void onSuccess(UserRepository.UserRepositoryResponse response) {
                responseMutableLiveData.setValue(new ProfileViewModelResponse(response.getMessage(), Status.Success));
            }

            @Override
            public void onError(UserRepository.UserRepositoryResponse response) {
                responseMutableLiveData.setValue(new ProfileViewModelResponse(response.getMessage(), Status.Error));
            }
        });
    }

    class ProfileViewModelResponse {
        private String message;
        private Status status;

        public ProfileViewModelResponse(String message, Status status) {
            this.message = message;
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public Status getStatus() {
            return status;
        }
    }

    enum Status {
        Success,
        Error
    }

}
