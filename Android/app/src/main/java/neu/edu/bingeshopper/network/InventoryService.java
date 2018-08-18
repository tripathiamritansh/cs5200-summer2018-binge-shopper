package neu.edu.bingeshopper.network;

import java.util.List;

import neu.edu.bingeshopper.Repository.Model.Inventory;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface InventoryService {

    @GET("api/inventory/user/{userId}/getInventory")
    Call<List<Inventory>> getInventory(@Path("userId") int userId);

    @DELETE("api/inventory/delete/{inventoryId}")
    Call<Void> deleteInventory(@Path("inventoryId") int inventoryId);
}
