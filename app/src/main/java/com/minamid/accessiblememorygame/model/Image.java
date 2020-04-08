package com.minamid.accessiblememorygame.model;

import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("id")
    private String imageId;

    @SerializedName("description")
    private String description;

    @SerializedName("link")
    private String link;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
