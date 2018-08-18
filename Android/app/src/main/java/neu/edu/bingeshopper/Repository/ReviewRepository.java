package neu.edu.bingeshopper.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import neu.edu.bingeshopper.Repository.Model.ProductReview;
import neu.edu.bingeshopper.Repository.Model.Repository;
import neu.edu.bingeshopper.Repository.Model.Review;
import neu.edu.bingeshopper.Repository.Model.SellerReview;
import neu.edu.bingeshopper.network.ReviewServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ReviewRepository {

    private ReviewServices reviewServices;

    @Inject
    public ReviewRepository(@Named("aws") Retrofit retrofit) {
        reviewServices = retrofit.create(ReviewServices.class);
    }

    public class ReviewRepositoryResponse implements Repository.ResponseValue {
        private List<Review> reviews;
        private String message;

        public ReviewRepositoryResponse(List<Review> reviews) {
            this.reviews = reviews;
        }

        public ReviewRepositoryResponse(String message) {
            this.message = message;
        }

        public List<Review> getReviews() {
            return reviews;
        }

        public void setReviews(List<Review> reviews) {
            this.reviews = reviews;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }


    public void postProduct(int userid, int productId, String review, final Repository.RepositoryCallBack<String> callBack) {
        ProductReview productReview = new ProductReview();
        productReview.setRating(1f);
        productReview.setReview(review);
        reviewServices.postProductReview(userid, productId, productReview).enqueue(new Callback<ProductReview>() {
            @Override
            public void onResponse(Call<ProductReview> call, Response<ProductReview> response) {
                if (response.isSuccessful()) {
                    callBack.onSuccess("Product Review Posted!");
                } else {
                    callBack.onError("Product Review Not Posted!");
                }
            }

            @Override
            public void onFailure(Call<ProductReview> call, Throwable t) {
                callBack.onError(t.getMessage());
            }
        });
    }

    public void postSeller(int buyerId, int sellerId, String review, final Repository.RepositoryCallBack<String> callBack) {
        SellerReview sellerReview = new SellerReview();
        sellerReview.setRating(1f);
        sellerReview.setReview(review);
        reviewServices.postSellerReview(buyerId, sellerId, sellerReview).enqueue(new Callback<SellerReview>() {
            @Override
            public void onResponse(Call<SellerReview> call, Response<SellerReview> response) {
                if (response.isSuccessful()) {
                    callBack.onSuccess("Seller Review Posted!");
                } else {
                    callBack.onError("Seller Review Not Posted!");
                }
            }

            @Override
            public void onFailure(Call<SellerReview> call, Throwable t) {
                callBack.onError(t.getMessage());
            }
        });
    }

    public void getSellerReviews(int userId, final Repository.RepositoryCallBack<ReviewRepositoryResponse> callBack) {
        reviewServices.getSellerReview(userId).enqueue(new Callback<List<SellerReview>>() {
            @Override
            public void onResponse(Call<List<SellerReview>> call, Response<List<SellerReview>> response) {
                if (response.isSuccessful()) {
                    List<Review> reviews = new ArrayList<>();
                    for (SellerReview sellerReview : response.body()) {
                        Review review = new Review();
                        review.setSellerReviews(sellerReview);
                        reviews.add(review);
                    }
                    callBack.onSuccess(new ReviewRepositoryResponse(reviews));
                } else {
                    callBack.onError(new ReviewRepositoryResponse("Error fetching seller reviews"));
                }
            }

            @Override
            public void onFailure(Call<List<SellerReview>> call, Throwable t) {
                callBack.onError(new ReviewRepositoryResponse(t.getMessage()));
            }
        });
    }
}
