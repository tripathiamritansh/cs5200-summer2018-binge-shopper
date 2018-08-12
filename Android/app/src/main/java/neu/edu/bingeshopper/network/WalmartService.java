package neu.edu.bingeshopper.network;

import neu.edu.bingeshopper.Repository.Model.WalmartResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WalmartService {

    @GET("v1/search?apiKey=8fa9f36nd853npc3bnejude6&numItems=25")
    Call<WalmartResponse> searchProducts(@Query("query") String query, @Query("sort") String sort);

}
