package com.minamid.accessiblememorygame.service;

import com.minamid.accessiblememorygame.model.ImageResponse;
import com.minamid.accessiblememorygame.util.Config;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ImageApi {

    @GET(Config.imageEndPoint)
    Call<ImageResponse> fetchImageList();
}
