package com.minamid.accessiblememorygame.util;

public class Config {
    private static final Config ourInstance = new Config();

    private String url = "https://shopicruit.myshopify.com/";
    private String endPoint = "";

    public static Config getInstance() {
        return ourInstance;
    }

    private Config() {

    }

    public String getUrl() {
        return url;
    }

    public String getEndPoint() {
        return endPoint;
    }
}
