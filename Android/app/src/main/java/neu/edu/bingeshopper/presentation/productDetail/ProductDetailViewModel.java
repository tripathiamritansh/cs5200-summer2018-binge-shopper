package neu.edu.bingeshopper.presentation.productDetail;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import neu.edu.bingeshopper.Repository.InventoryRepository;
import neu.edu.bingeshopper.Repository.Model.Product;
import neu.edu.bingeshopper.Repository.Model.ProductReview;
import neu.edu.bingeshopper.Repository.Model.Repository;
import neu.edu.bingeshopper.Repository.ProductRepository;
import neu.edu.bingeshopper.common.UserManager;

public class ProductDetailViewModel extends ViewModel {

    private ProductRepository repository;
    private MutableLiveData<ProductDetailsViewModelResponse> responseMutableLiveData;
    private MutableLiveData<String> wishListLiveData;
    private UserManager userManager;
    private InventoryRepository inventoryRepository;

    @Inject
    public ProductDetailViewModel(ProductRepository repository, UserManager userManager, InventoryRepository inventoryRepository) {
        this.repository = repository;
        this.userManager = userManager;
        this.inventoryRepository = inventoryRepository;
    }

    public MutableLiveData<ProductDetailsViewModelResponse> getResponseMutableLiveData() {
        if (responseMutableLiveData == null) {
            responseMutableLiveData = new MutableLiveData<>();
        }
        return responseMutableLiveData;
    }

    public MutableLiveData<String> getWishListLiveData() {
        if (wishListLiveData == null) {
            wishListLiveData = new MutableLiveData<>();
        }
        return wishListLiveData;
    }

    public void fetchReviews(int productId) {

        repository.getProductReview(productId, new Repository.RepositoryCallBack() {
            @Override
            public void onSuccess(Object response) {
                List<ProductReview> reviews = (List<ProductReview>) response;
                responseMutableLiveData.setValue(new ProductDetailsViewModelResponse(Status.Success, reviews, null));
            }

            @Override
            public void onError(Object response) {
                responseMutableLiveData.setValue(new ProductDetailsViewModelResponse(Status.Error, null, ((ProductRepository.ProductRepositoryResponse) response).getMessage()));
            }
        });

    }

    public void addToWishList(int userId, Product product) {
        inventoryRepository.addToWishList(userId, product, new Repository.RepositoryCallBack<InventoryRepository.InventoryRepositoryResponse>() {
            @Override
            public void onSuccess(InventoryRepository.InventoryRepositoryResponse response) {
                wishListLiveData.setValue(response.getMessage());
            }

            @Override
            public void onError(InventoryRepository.InventoryRepositoryResponse response) {
                wishListLiveData.setValue(response.getMessage());
            }
        });
    }

    public void addProductToInventory(int userId, int qty, int price, Product product) {
        inventoryRepository.addProductToInventory(userId, qty, price, product, new Repository.RepositoryCallBack<InventoryRepository.InventoryRepositoryResponse>() {
            @Override
            public void onSuccess(InventoryRepository.InventoryRepositoryResponse response) {
                responseMutableLiveData.setValue(new ProductDetailsViewModelResponse(Status.Success, null, null));
            }

            @Override
            public void onError(InventoryRepository.InventoryRepositoryResponse response) {
                responseMutableLiveData.setValue(new ProductDetailsViewModelResponse(Status.Error, null, response.getMessage()));
            }

        });
    }

    class ProductDetailsViewModelResponse {

        private Status status;
        private List<ProductReview> reviews;
        private String message;

        public ProductDetailsViewModelResponse(Status status, List<ProductReview> reviews, String message) {
            this.status = status;
            this.reviews = reviews;
            this.message = message;
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public List<ProductReview> getReviews() {
            return reviews;
        }

        public void setReviews(List<ProductReview> reviews) {
            this.reviews = reviews;
        }

        public void setMessage(String message) {
            this.message = message;
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
