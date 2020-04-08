package com.minamid.accessiblememorygame.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Album {

    @SerializedName("images")
    private List<Image> images;

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
