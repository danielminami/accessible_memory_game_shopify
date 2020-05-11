package com.minamid.accessiblememorygame.base;

import com.minamid.accessiblememorygame.util.Config;
import butterknife.BuildConfig;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomRetrofit {

    public static Retrofit retrofit;

    public static Retrofit setup(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

        Cache cache = new Cache(Config.CACHE_LOCATION, Config.CACHE_SIZE);

        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        }

        // TODO: Implement Cache Interceptor
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .cache(cache)
                .build();

        Retrofit.Builder builder = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Config.getInstance().URL)
                .addConverterFactory(GsonConverterFactory.create());

        return retrofit = builder.build();
    }
}
