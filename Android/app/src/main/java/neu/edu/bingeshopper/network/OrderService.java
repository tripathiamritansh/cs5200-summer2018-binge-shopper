package neu.edu.bingeshopper.network;

import java.util.List;

import neu.edu.bingeshopper.Repository.Model.Order;
import neu.edu.bingeshopper.Repository.Model.OrderTransactionWrapper;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrderService {

    @POST("api/order/user/{userId}/add")
    Call<Order> placeOrder(@Path("userId") int userId, @Body List<OrderTransactionWrapper> wrapper);

    @GET("api/order/user/{userId}/getOrder")
    Call<List<Order>> getOrders(@Path("userId") int userId);
}
