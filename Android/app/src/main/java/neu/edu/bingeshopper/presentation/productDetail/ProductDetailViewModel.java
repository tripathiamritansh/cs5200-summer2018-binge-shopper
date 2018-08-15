package neu.edu.bingeshopper.presentation.productDetail;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import neu.edu.bingeshopper.Repository.Model.ProductReview;
import neu.edu.bingeshopper.Repository.Model.Repository;
import neu.edu.bingeshopper.Repository.ProductRepository;
import neu.edu.bingeshopper.common.UserManager;

public class ProductDetailViewModel extends ViewModel {

    private ProductRepository repository;
    private MutableLiveData<ProductDetailsViewModelResponse> responseMutableLiveData;
    private UserManager userManager;

    @Inject
    public ProductDetailViewModel(ProductRepository repository, UserManager userManager) {
        this.repository = repository;
        this.userManager = userManager;
    }

    public MutableLiveData<ProductDetailsViewModelResponse> getResponseMutableLiveData() {
        if (responseMutableLiveData == null) {
            responseMutableLiveData = new MutableLiveData<>();
        }
        return responseMutableLiveData;
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
