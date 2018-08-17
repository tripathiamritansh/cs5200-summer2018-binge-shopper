package neu.edu.bingeshopper.network;

import java.util.List;

import neu.edu.bingeshopper.Repository.Model.Inventory;
import neu.edu.bingeshopper.Repository.Model.Product;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SellerService {

    @GET("api/inventory/product/{productId}/getSeller")
    Call<List<Inventory>> getAllSellersForProduct(@Path("productId") int id);


    @POST("api/inventory/user/{userId}/price/{price}/qty/{qty}/addProduct")
    Call<Inventory> addProductToInventory(@Path("userId") int userId, @Path("price") int price, @Path("qty") int qty, @Body Product product);
}
