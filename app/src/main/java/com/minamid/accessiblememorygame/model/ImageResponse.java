package com.minamid.accessiblememorygame.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImageResponse {

    @SerializedName("products")
    private List<Cards> cardsList;

    public List<Cards> getCardsList() {
        return cardsList;
    }

    public class Cards {

        @SerializedName("image")
        private Image mImage;

        public Image getmImage() {
            return mImage;
        }

        public void setmImage(Image mImage) {
            this.mImage = mImage;
        }

        public class Image {

            @SerializedName("src")
            private String mSrc;

            public String getmSrc() {
                return mSrc;
            }

            public void setmSrc(String mSrc) {
                this.mSrc = mSrc;
            }
        }

    }

}
