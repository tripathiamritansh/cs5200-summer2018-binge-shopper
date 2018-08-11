package neu.edu.bingeshopper.network;

import neu.edu.bingeshopper.Repository.Model.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UserServices {

    @POST("api/user/login")
    @FormUrlEncoded
    Call<User> login(@Field("username") String username, @Field("password") String password);

    @POST("api/user/register")
    Call<User> register(@Body User user);

    @PUT("api/user/update")
    Call<User> update(@Body User user);

    @DELETE("api/user/logout")
    Call<ResponseBody> logout();

}
