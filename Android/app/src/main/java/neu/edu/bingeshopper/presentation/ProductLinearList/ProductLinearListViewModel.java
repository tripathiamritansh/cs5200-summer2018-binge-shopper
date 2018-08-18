package neu.edu.bingeshopper.presentation.ProductLinearList;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import neu.edu.bingeshopper.Repository.LinearListRepository;
import neu.edu.bingeshopper.Repository.Model.Repository;

public class ProductLinearListViewModel extends ViewModel {

    private LinearListRepository repository;
    private MutableLiveData<ProductLinearListViewModelResponse> responseMutableLiveData;

    @Inject
    public ProductLinearListViewModel(LinearListRepository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ProductLinearListViewModelResponse> getResponseMutableLiveData() {
        if (responseMutableLiveData == null) {
            responseMutableLiveData = new MutableLiveData<>();
        }
        return responseMutableLiveData;
    }

    public void getSellerInventory(int userId) {
        repository.getSellerInventory(userId, new Repository.RepositoryCallBack<LinearListRepository.LinearListRepositoryResponse>() {
            @Override
            public void onSuccess(LinearListRepository.LinearListRepositoryResponse response) {
                responseMutableLiveData.setValue(new ProductLinearListViewModelResponse(Status.Success, response.getData()));
            }

            @Override
            public void onError(LinearListRepository.LinearListRepositoryResponse response) {
                responseMutableLiveData.setValue(new ProductLinearListViewModelResponse(Status.Error, response.getMessage()));
            }
        });
    }

    public void getTransactionList(int orderId) {
        repository.getTransactionForOrder(orderId, new Repository.RepositoryCallBack<LinearListRepository.LinearListRepositoryResponse>() {
            @Override
            public void onSuccess(LinearListRepository.LinearListRepositoryResponse response) {
                responseMutableLiveData.setValue(new ProductLinearListViewModelResponse(Status.Success, response.getData()));
            }

            @Override
            public void onError(LinearListRepository.LinearListRepositoryResponse response) {
                responseMutableLiveData.setValue(new ProductLinearListViewModelResponse(Status.Error, response.getMessage()));
            }
        });
    }

    public void getWishList(int userId) {
        repository.getUserWishList(userId, new Repository.RepositoryCallBack<LinearListRepository.LinearListRepositoryResponse>() {
            @Override
            public void onSuccess(LinearListRepository.LinearListRepositoryResponse response) {
                responseMutableLiveData.setValue(new ProductLinearListViewModelResponse(Status.Success, response.getData()));
            }

            @Override
            public void onError(LinearListRepository.LinearListRepositoryResponse response) {
                responseMutableLiveData.setValue(new ProductLinearListViewModelResponse(Status.Error, response.getMessage()));
            }
        });
    }

    public void getOrderList(int userId) {
        repository.getOrderList(userId, new Repository.RepositoryCallBack<LinearListRepository.LinearListRepositoryResponse>() {
            @Override
            public void onSuccess(LinearListRepository.LinearListRepositoryResponse response) {
                responseMutableLiveData.setValue(new ProductLinearListViewModelResponse(Status.Success, response.getData()));
            }

            @Override
            public void onError(LinearListRepository.LinearListRepositoryResponse response) {
                responseMutableLiveData.setValue(new ProductLinearListViewModelResponse(Status.Error, response.getMessage()));
            }
        });
    }

    class ProductLinearListViewModelResponse {
        private Status status;
        private List<ProductLinearListModel> data;
        private String message;

        public ProductLinearListViewModelResponse(Status status, List<ProductLinearListModel> data) {
            this.status = status;
            this.data = data;
        }

        public ProductLinearListViewModelResponse(Status status, String message) {
            this.status = status;
            this.message = message;
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public List<ProductLinearListModel> getData() {
            return data;
        }

        public void setData(List<ProductLinearListModel> data) {
            this.data = data;
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
