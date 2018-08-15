package neu.edu.bingeshopper.network;

import java.util.List;

import neu.edu.bingeshopper.Repository.Model.ProductReview;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductService {

    @GET("api/product-review/product/{productId}/getByProduct")
    Call<List<ProductReview>> getProductReview(@Path("productId") int id);
}
