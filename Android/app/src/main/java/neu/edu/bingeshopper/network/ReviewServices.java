package neu.edu.bingeshopper.network;

import java.util.List;

import neu.edu.bingeshopper.Repository.Model.ProductReview;
import neu.edu.bingeshopper.Repository.Model.SellerReview;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ReviewServices {

    @POST("api/seller-review/add/buyer/{buyerId}/seller/{sellerId}")
    Call<SellerReview> postSellerReview(@Path("buyerId") int buyerId, @Path("sellerId") int sellerId, @Body SellerReview sellerReview);

    @POST("api/product-review/add/user/{userId}/product/{productId}")
    Call<ProductReview> postProductReview(@Path("userId") int buyerId, @Path("productId") int productId, @Body ProductReview productReview);

    @GET("api/seller-review/user/{userId}/getBySeller")
    Call<List<SellerReview>> getSellerReview(@Path("userId") int userId);


}
