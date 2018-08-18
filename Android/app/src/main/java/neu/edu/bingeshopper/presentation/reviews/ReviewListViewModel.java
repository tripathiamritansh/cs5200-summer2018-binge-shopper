package neu.edu.bingeshopper.presentation.reviews;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import neu.edu.bingeshopper.Repository.Model.Repository;
import neu.edu.bingeshopper.Repository.Model.Review;
import neu.edu.bingeshopper.Repository.ReviewRepository;

public class ReviewListViewModel extends ViewModel {

    private ReviewRepository reviewRepository;

    private MutableLiveData<ReviewListViewModelResponse> reviewListViewModelResponseMutableLiveData;

    @Inject
    public ReviewListViewModel(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public MutableLiveData<ReviewListViewModelResponse> getReviewListViewModelResponseMutableLiveData() {
        if (reviewListViewModelResponseMutableLiveData == null) {
            reviewListViewModelResponseMutableLiveData = new MutableLiveData<>();
        }
        return reviewListViewModelResponseMutableLiveData;
    }

    public void getReviews(int sellerid) {
        reviewRepository.getSellerReviews(sellerid, new Repository.RepositoryCallBack<ReviewRepository.ReviewRepositoryResponse>() {
            @Override
            public void onSuccess(ReviewRepository.ReviewRepositoryResponse response) {
                reviewListViewModelResponseMutableLiveData.setValue(new ReviewListViewModelResponse(Status.Success, response.getReviews()));
            }

            @Override
            public void onError(ReviewRepository.ReviewRepositoryResponse response) {
                reviewListViewModelResponseMutableLiveData.setValue(new ReviewListViewModelResponse(Status.Error, response.getMessage()));
            }
        });
    }

    class ReviewListViewModelResponse {
        private Status status;
        private List<Review> reviews;
        private String message;

        public ReviewListViewModelResponse(Status status, String message) {
            this.status = status;
            this.message = message;
        }

        public ReviewListViewModelResponse(Status status, List<Review> reviews) {
            this.status = status;
            this.reviews = reviews;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public List<Review> getReviews() {
            return reviews;
        }

        public void setReviews(List<Review> reviews) {
            this.reviews = reviews;
        }
    }

    enum Status {
        Success,
        Error
    }
}
