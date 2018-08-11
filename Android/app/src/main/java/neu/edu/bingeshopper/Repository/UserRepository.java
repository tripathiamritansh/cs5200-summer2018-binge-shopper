package neu.edu.bingeshopper.Repository;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import neu.edu.bingeshopper.Repository.Model.Repository;
import neu.edu.bingeshopper.Repository.Model.User;
import neu.edu.bingeshopper.common.UserManager;
import neu.edu.bingeshopper.network.UserServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserRepository extends Repository {

    private UserServices userServices;
    private SharedPreferences sharedPreferences;
    private UserManager userManager;

    @Inject
    public UserRepository(Retrofit retrofit, SharedPreferences sharedPreferences, UserManager userManager) {
        this.sharedPreferences = sharedPreferences;
        this.userManager = userManager;
        this.userServices = retrofit.create(UserServices.class);
    }


    public class UserRespositoryResponse implements ResponseValue {

        @Nullable
        private User user;

        @Nullable
        private String message;

        private Throwable t;

        public UserRespositoryResponse(Throwable t) {
            this.t = t;
        }

        public UserRespositoryResponse(@Nullable User user) {
            this.user = user;
        }

        public UserRespositoryResponse(String message) {
            this.message = message;
        }

        @Nullable
        public String getMessage() {
            return message;
        }

        public Throwable getT() {
            return t;
        }

        @Nullable
        public User getUser() {
            return user;
        }
    }

    public void login(String username, String passport, final RepositoryCallBack callBack) {
        setCallBack(callBack);
        userServices.login(username, passport).enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if (response.isSuccessful()) {
                    userManager.saveUser(response.body());
                    callBack.onSuccess(new UserRespositoryResponse(response.body()));
                } else {
                    callBack.onError(new UserRespositoryResponse(response.message()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                callBack.onError(new UserRespositoryResponse(t.getMessage()));
            }
        });
    }


}
