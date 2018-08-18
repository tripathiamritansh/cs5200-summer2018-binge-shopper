package neu.edu.bingeshopper.network;

import java.util.List;

import neu.edu.bingeshopper.Repository.Model.Transaction;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TransactionService {

    @GET("api/transaction/order/{orderId}/getByOrder")
    Call<List<Transaction>> getTransactionsForOrder(@Path("orderId") int orderId);
}
