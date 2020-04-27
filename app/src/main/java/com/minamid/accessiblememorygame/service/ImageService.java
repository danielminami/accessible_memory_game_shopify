package com.minamid.accessiblememorygame.service;

import android.util.Log;

import com.minamid.accessiblememorygame.base.CustomRetrofit;
import com.minamid.accessiblememorygame.model.ImageResponse;
import com.minamid.accessiblememorygame.util.Config;
import com.minamid.accessiblememorygame.util.ResponseStatusCode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageService {

    private final String TAG = "ImageService";
    ImageApi api;

    public ImageService() {
        api = CustomRetrofit.setup().create(ImageApi.class);
    }

    public interface FetchImageCallBack {

        void onSuccess(ImageResponse imageResponse);

        void onFailure(ResponseStatusCode responseStatusCode);

    }

    public void fetchImageList(final FetchImageCallBack callBack) {
        Call<ImageResponse> call = api.fetchImageList(Config.CLIENT_ID);
        call.enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                if (response != null && response.body() != null && response.isSuccessful()) {
                    if (response.raw().cacheResponse() != null) {
                        Log.d(TAG, "Cache Response");
                    }

                    if (response.raw().networkResponse() != null) {
                        Log.d(TAG, "Network Response");
                    }
                    callBack.onSuccess(response.body());
                } else {
                    callBack.onFailure(ResponseStatusCode.FAIL);
                }
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {
                Log.d(TAG, t.getMessage());
                ResponseStatusCode responseStatusCode = ResponseStatusCode.getErrorCode(t);
                callBack.onFailure(responseStatusCode);
            }
        });
    }

}
