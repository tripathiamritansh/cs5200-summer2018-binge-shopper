package neu.edu.bingeshopper.presentation;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import neu.edu.bingeshopper.Repository.UserRepository;

public class MainActivityViewModel extends ViewModel {

    private UserRepository repository;
    private MutableLiveData<MainActivityViewModelResponse> responseMutableLiveData;

    @Inject
    public MainActivityViewModel(UserRepository repository) {
        this.repository = repository;
    }

    public MutableLiveData<MainActivityViewModelResponse> getResponseMutableLiveData() {
        if (responseMutableLiveData == null) {
            responseMutableLiveData = new MutableLiveData<>();
        }
        return responseMutableLiveData;
    }


    public void logoutUser(){

    }
    class MainActivityViewModelResponse {

        private Status status;
        private String message;

        public MainActivityViewModelResponse(Status status, @Nullable String message) {
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
}
