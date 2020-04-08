package com.minamid.accessiblememorygame.model;

import com.google.gson.annotations.SerializedName;

public class ImageResponse {

    @SerializedName("data")
    private Album album;

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
