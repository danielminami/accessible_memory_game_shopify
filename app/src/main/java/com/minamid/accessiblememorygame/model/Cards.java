package com.minamid.accessiblememorygame.model;

import com.google.gson.annotations.SerializedName;

public class Cards {

    @SerializedName("image")
    private Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

}