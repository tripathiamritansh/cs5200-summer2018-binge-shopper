package neu.edu.bingeshopper.presentation.cart;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import neu.edu.bingeshopper.Repository.Model.CartItem;
import neu.edu.bingeshopper.Repository.Model.Repository;
import neu.edu.bingeshopper.Repository.OrderRepository;

public class CartViewModel extends ViewModel {

    private OrderRepository repository;

    private MutableLiveData<CartViewModelResponse> responseMutableLiveData;

    public MutableLiveData<CartViewModelResponse> getResponseMutableLiveData() {
        if (responseMutableLiveData == null) {
            responseMutableLiveData = new MutableLiveData<>();
        }
        return responseMutableLiveData;
    }

    @Inject
    public CartViewModel(OrderRepository repository) {
        this.repository = repository;
    }

    void placeOrder(int userId, List<CartItem> cartItems) {
        repository.placeOrder(cartItems, userId, new Repository.RepositoryCallBack<OrderRepository.OrderRepositoryResponse>() {
            @Override
            public void onSuccess(OrderRepository.OrderRepositoryResponse response) {
                responseMutableLiveData.setValue(new CartViewModelResponse(Status.Success, response.getMessage()));
            }

            @Override
            public void onError(OrderRepository.OrderRepositoryResponse response) {
                responseMutableLiveData.setValue(new CartViewModelResponse(Status.Error, response.getMessage()));
            }
        });
    }


    class CartViewModelResponse {
        private Status status;
        private String message;

        public CartViewModelResponse(Status status, String message) {
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
