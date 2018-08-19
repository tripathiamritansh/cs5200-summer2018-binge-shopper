package neu.edu.bingeshopper.DI;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.File;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import neu.edu.bingeshopper.Repository.Model.Cart;
import neu.edu.bingeshopper.common.Constants;
import neu.edu.bingeshopper.common.UserManager;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by amritanshtripathi on 6/20/18.
 */

@Module
public class AppModule {
//    private final String baseurl = "http://10.0.2.2:8080/";
    private final String walmartBaseUrl = "http://api.walmartlabs.com/";
    private final String baseurl = "http://ec2-18-219-181-207.us-east-2.compute.amazonaws.com/";

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }


    @Provides
    @Singleton
    @Named("aws")
    public Retrofit retrofit1(OkHttpClient okHttpClient,
                              GsonConverterFactory gsonConverterFactory, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(baseurl)
                .addConverterFactory(gsonConverterFactory)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    @Named("walmart")
    public Retrofit retrofit2(OkHttpClient okHttpClient,
                              GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder()
                .baseUrl(walmartBaseUrl)
                .addConverterFactory(gsonConverterFactory)
                .client(okHttpClient)
                .build();
    }


    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    public GsonConverterFactory gsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }


    @Provides
    @Singleton
    public OkHttpClient okHttpClient(final Cache cache, HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient()
                .newBuilder()
                .cache(cache)
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    @Provides
    @Singleton
    public Cache cache(File cacheFile) {
        return new Cache(cacheFile, 10 * 1000 * 1000); //10 MB
    }

    @Provides
    @Singleton
    public File file(Context context) {
        File file = new File(context.getCacheDir(), "HttpCache");
        file.mkdirs();
        return file;
    }

    @Provides
    @Singleton
    public HttpLoggingInterceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e(this.getClass().getSimpleName(), message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    @Provides
    @Singleton
    public Picasso picasso(Context context, OkHttp3Downloader okHttp3Downloader) {
        return new Picasso.Builder(context).
                downloader(okHttp3Downloader).
                build();
    }

    @Provides
    @Singleton
    public OkHttp3Downloader okHttp3Downloader(OkHttpClient okHttpClient) {
        return new OkHttp3Downloader(okHttpClient);
    }


    @Provides
    @Singleton
    public SharedPreferences getSharePreferences(Context context) {

        return context.getSharedPreferences(Constants.SHARED_PERF, Context.MODE_PRIVATE);

    }

    @Provides
    @Singleton
    public UserManager getUserManager(SharedPreferences sharedPreferences) {
        return new UserManager(sharedPreferences);
    }

    @Provides
    @Singleton
    public Cart getCart(SharedPreferences sharedPreferences) {
        return new Cart(sharedPreferences);
    }
}
