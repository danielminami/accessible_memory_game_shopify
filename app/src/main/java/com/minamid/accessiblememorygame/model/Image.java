package com.minamid.accessiblememorygame.model;

import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("id")
    private String id;

    @SerializedName("src")
    private String src;

    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
