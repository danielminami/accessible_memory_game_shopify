package com.minamid.accessiblememorygame.service;

import com.minamid.accessiblememorygame.model.ImageResponse;
import com.minamid.accessiblememorygame.util.Config;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ImageApi {

    @GET(Config.IMAGE_LIST_ENDPOINT)
    Call<ImageResponse> fetchImageList(@Header("Authorization") String auth);
}
