package neu.edu.bingeshopper.presentation.productList;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import java.util.List;

import javax.inject.Inject;

import neu.edu.bingeshopper.Repository.Model.Product;
import neu.edu.bingeshopper.Repository.Model.Repository;
import neu.edu.bingeshopper.Repository.ProductRepository;

public class ProductListViewModel extends ViewModel {


    private ProductRepository repository;

    private MutableLiveData<ProductListViewModelResponse> responseMutableLiveData;

    @Inject
    public ProductListViewModel(ProductRepository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ProductListViewModelResponse> getResponseMutableLiveData() {
        if (responseMutableLiveData == null) {
            responseMutableLiveData = new MutableLiveData<>();
        }
        return responseMutableLiveData;
    }


    public void searchProduct(String query, String sort) {

        repository.searchProduct(query, sort, new Repository.RepositoryCallBack<ProductRepository.ProductRepositoryResponse>() {

            @Override
            public void onSuccess(ProductRepository.ProductRepositoryResponse response) {
                responseMutableLiveData.setValue(new ProductListViewModelResponse(Status.Success, response.getProducts(), null));
            }

            @Override
            public void onError(ProductRepository.ProductRepositoryResponse response) {
                responseMutableLiveData.setValue(new ProductListViewModelResponse(Status.Error, null, response.getMessage()));
            }
        });

    }

    class ProductListViewModelResponse {

        private Status status;
        private List<Product> products;
        private String message;

        public ProductListViewModelResponse(Status status, @Nullable List<Product> products, @Nullable String message) {
            this.status = status;
            this.message = message;
            this.products = products;
        }

        public Status getStatus() {
            return status;
        }

        public List<Product> getProducts() {
            return products;
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
