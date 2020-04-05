package com.minamid.accessiblememorygame.util;

public class Config {
    private static final Config ourInstance = new Config();

    public static final String url = "https://shopicruit.myshopify.com/";
    public static final String imageEndPoint = "/admin/products.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6";
    public static final int timeBoardRevealed = 60;

    public static Config getInstance() {
        return ourInstance;
    }

    private Config() {

    }

}
