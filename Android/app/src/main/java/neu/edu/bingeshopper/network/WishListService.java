package neu.edu.bingeshopper.network;

import java.util.List;

import neu.edu.bingeshopper.Repository.Model.Product;
import neu.edu.bingeshopper.Repository.Model.WishList;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WishListService {

    @GET("api/wishlist/user/{userId}/getProducts")
    Call<List<WishList>> getProductsInWishList(@Path("userId") int userId);

    @POST("api/wishlist/user/{userId}/add")
    Call<Void> addToWishList(@Path("userId") int userId, @Body Product product);
}
