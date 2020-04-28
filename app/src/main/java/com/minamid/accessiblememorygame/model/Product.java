package com.minamid.accessiblememorygame.model;

import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("id")
    String id;

    @SerializedName("title")
    String title;

    @SerializedName("image")
    Image image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}