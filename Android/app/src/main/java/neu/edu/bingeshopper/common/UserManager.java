package neu.edu.bingeshopper.common;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

import javax.inject.Inject;

import neu.edu.bingeshopper.Repository.Model.User;

public class UserManager {


    private SharedPreferences sharedPreferences;

    @Inject
    public UserManager(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void saveUser(User user) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = new Gson().toJson(user);
        editor.putString(Constants.SHARED_PERF_USER, json);
        editor.apply();

    }


    @Nullable
    public User getUser() {
        String userJson = sharedPreferences.getString(Constants.SHARED_PERF_USER, null);
        Gson gson = new Gson();
        return gson.fromJson(userJson, User.class);
    }

}
