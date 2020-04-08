package com.minamid.accessiblememorygame.util;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class LogInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Log.d("REQUEST", String.format("--> %s", request.url()));

        Response response = chain.proceed(request);
        if (response.body() != null) {
            //while (response.body().contentLength() )
            Log.d("RESPONSE", String.format("<-- %d %s", response.code(), response.body().string()));
        }

        return response;
    }
}
