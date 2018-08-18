package neu.edu.bingeshopper.presentation.admin;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import neu.edu.bingeshopper.Repository.Model.Repository;
import neu.edu.bingeshopper.Repository.Model.User;
import neu.edu.bingeshopper.Repository.UserRepository;

public class AdminViewModel extends ViewModel {

    private UserRepository userRepository;
    private MutableLiveData<AdminViewModelResponse> responseMutableLiveData;

    @Inject
    public AdminViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public MutableLiveData<AdminViewModelResponse> getResponseMutableLiveData() {

        if (responseMutableLiveData == null) {
            responseMutableLiveData = new MutableLiveData<>();
        }
        return responseMutableLiveData;
    }


    public void approveUser(int userId, boolean approve) {

        userRepository.approveUser(userId, approve, new Repository.RepositoryCallBack<UserRepository.AdminResponse>() {
            @Override
            public void onSuccess(UserRepository.AdminResponse response) {
                responseMutableLiveData.setValue(new AdminViewModelResponse(Status.Success, response.getUsers()));
            }

            @Override
            public void onError(UserRepository.AdminResponse response) {
                responseMutableLiveData.setValue(new AdminViewModelResponse(Status.Error, response.getMessage()));
            }
        });
    }

    public void deleteUser(int userId) {
        userRepository.deleteUser(userId, new Repository.RepositoryCallBack<UserRepository.AdminResponse>() {
            @Override
            public void onSuccess(UserRepository.AdminResponse response) {
                responseMutableLiveData.setValue(new AdminViewModelResponse(Status.Success, response.getUsers()));
            }

            @Override
            public void onError(UserRepository.AdminResponse response) {
                responseMutableLiveData.setValue(new AdminViewModelResponse(Status.Error, response.getMessage()));
            }
        });
    }

    public void getAllUsers() {
        userRepository.getAllUsers(new Repository.RepositoryCallBack<UserRepository.AdminResponse>() {
            @Override
            public void onSuccess(UserRepository.AdminResponse response) {
                responseMutableLiveData.setValue(new AdminViewModelResponse(Status.Success, response.getUsers()));
            }

            @Override
            public void onError(UserRepository.AdminResponse response) {
                responseMutableLiveData.setValue(new AdminViewModelResponse(Status.Error, response.getMessage()));
            }
        });
    }

    public class AdminViewModelResponse {
        private Status status;
        private List<User> users;
        private String message;

        public AdminViewModelResponse(Status status, String message) {
            this.status = status;
            this.message = message;
        }

        public AdminViewModelResponse(Status status, List<User> users) {
            this.users = users;
            this.status = status;
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public List<User> getUsers() {
            return users;
        }

        public void setUsers(List<User> users) {
            this.users = users;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    enum Status {
        Success,
        Error
    }

}
