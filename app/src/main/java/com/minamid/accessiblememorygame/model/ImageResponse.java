package com.minamid.accessiblememorygame.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImageResponse {

    @SerializedName("products")
    private List<Cards> cardsList;

    public List<Cards> getCardsList() {
        return cardsList;
    }

}
