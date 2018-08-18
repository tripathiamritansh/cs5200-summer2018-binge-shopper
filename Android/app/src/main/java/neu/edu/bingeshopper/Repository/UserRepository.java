package neu.edu.bingeshopper.Repository;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

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
    public UserRepository(@Named("aws") Retrofit retrofit, SharedPreferences sharedPreferences, UserManager userManager) {
        this.sharedPreferences = sharedPreferences;
        this.userManager = userManager;
        this.userServices = retrofit.create(UserServices.class);
    }


    public class UserRepositoryResponse implements ResponseValue {

        @Nullable
        private User user;

        @Nullable
        private String message;

        private Throwable t;

        public UserRepositoryResponse(Throwable t) {
            this.t = t;
        }

        public UserRepositoryResponse(@Nullable User user) {
            this.user = user;
        }

        public UserRepositoryResponse(String message) {
            this.message = message;
        }

        public UserRepositoryResponse(User user, String message) {
            this.user = user;
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


    public class AdminResponse implements ResponseValue {


        @Nullable
        private String message;
        private List<User> users;

        public AdminResponse(String message, List<User> users) {
            this.message = message;
            this.users = users;
        }

        public AdminResponse(List<User> users) {
            this.users = users;
        }

        public AdminResponse(String message) {
            this.message = message;
        }

        @Nullable
        public String getMessage() {
            return message;
        }

        public void setMessage(@Nullable String message) {
            this.message = message;
        }

        public List<User> getUsers() {
            return users;
        }

        public void setUsers(List<User> users) {
            this.users = users;
        }
    }

    public void login(String username, String passport, final RepositoryCallBack callBack) {
        setCallBack(callBack);
        userServices.login(username, passport).enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if (response.isSuccessful()) {
                    userManager.saveUser(response.body());
                    callBack.onSuccess(new UserRepositoryResponse(response.body()));
                } else {
                    callBack.onError(new UserRepositoryResponse(response.message()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                callBack.onError(new UserRepositoryResponse(t.getMessage()));
            }
        });
    }


    public void signUp(User user, final RepositoryCallBack callBack) {
        setCallBack(callBack);
        userServices.register(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    userManager.saveUser(response.body());
                    callBack.onSuccess(new UserRepositoryResponse(response.body()));
                } else {
                    callBack.onError(new UserRepositoryResponse(response.message()));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callBack.onError(new UserRepositoryResponse(t.getMessage()));
            }
        });
    }

    public void updateUser(User user, final RepositoryCallBack<UserRepositoryResponse> callBack) {
        userServices.update(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    userManager.saveUser(response.body());
                    callBack.onSuccess(new UserRepositoryResponse(response.body(), "Successfully Updated!"));
                } else {
                    callBack.onError(new UserRepositoryResponse("Error Updating User, please try again!"));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callBack.onError(new UserRepositoryResponse(t.getMessage()));
            }
        });
    }

    public void getAllUsers(final RepositoryCallBack<AdminResponse> callBack) {
        userServices.getAllUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    callBack.onSuccess(new AdminResponse(response.body()));
                } else {
                    callBack.onError(new AdminResponse("Error fetching user list"));
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                callBack.onError(new AdminResponse(t.getMessage()));
            }
        });
    }

    public void deleteUser(int userId, final RepositoryCallBack<AdminResponse> callBack) {
        userServices.deleteUser(userId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    getAllUsers(callBack);
                } else {
                    callBack.onError(new AdminResponse("Error deleting user"));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callBack.onError(new AdminResponse(t.getMessage()));
            }
        });
    }

    public void approveUser(int userId, boolean approve, final RepositoryCallBack<AdminResponse> callBack) {
        userServices.approveUser(userId, approve).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    getAllUsers(callBack);
                } else {
                    callBack.onError(new AdminResponse("Error updating user"));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callBack.onError(new AdminResponse(t.getMessage()));
            }
        });
    }

}
