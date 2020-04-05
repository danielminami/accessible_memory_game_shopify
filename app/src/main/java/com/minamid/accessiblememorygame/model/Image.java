package com.minamid.accessiblememorygame.model;

import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("src")
    private String src;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
