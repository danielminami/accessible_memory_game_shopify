package com.minamid.accessiblememorygame.base;

import com.minamid.accessiblememorygame.util.Config;
import com.minamid.accessiblememorygame.util.LogInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomRetrofit {

    public static Retrofit retrofit;

    public static Retrofit setup(){

        LogInterceptor logInterceptor = new LogInterceptor();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(logInterceptor)
                .build();

        //TODO: Create the custom Retrofit Class with interceptor
        Retrofit.Builder builder = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Config.getInstance().getUrl())
                .addConverterFactory(GsonConverterFactory.create());

        return retrofit = builder.build();
    }

}
