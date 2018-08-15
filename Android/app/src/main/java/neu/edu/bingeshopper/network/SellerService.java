package neu.edu.bingeshopper.network;

import java.util.List;

import neu.edu.bingeshopper.Repository.Model.Inventory;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SellerService {

    @GET("api/inventory/product/{productId}/getSeller")
    Call<List<Inventory>> getAllSellersForProduct(@Path("productId") int id);
}
