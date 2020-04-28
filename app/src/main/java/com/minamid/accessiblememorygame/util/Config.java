package com.minamid.accessiblememorygame.util;

import android.content.Context;

import com.minamid.accessiblememorygame.model.BoardSize;

import java.io.File;

public class Config {
    private static final Config ourInstance = new Config();

    public static final String URL = "https://shopicruit.myshopify.com/";
    public static final String IMAGE_LIST_ENDPOINT = "/admin/products.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6";
    public static File CACHE_LOCATION;
    public static final int CACHE_SIZE = 4092;
    // TODO: Make it dynamic
    private static int pairsToMatch = 10;
    private static int numOfMatchesPerGame = 2;
    private static int columnSize = 50;

    public static final int timeBoardRevealed = 10;

    public static Config getInstance() {
        return ourInstance;
    }

    private Config() {

    }

    /**
     * Gets the number of cards to form a match
     *
     * @return number of cards to form a match
     */
    public static int getPairsToMatch() {
        return pairsToMatch;
    }

    /**
     * Sets the number of cards to form a match
     *
     * @param pairsToMatch number
     */
    public void setPairsToMatch(int pairsToMatch) {
        this.pairsToMatch = pairsToMatch;
    }

    /**
     * Gets number of pairs to match
     *
     * @return number of pairs to match
     */
    public static int getNumOfMatchesPerGame() {
        return numOfMatchesPerGame;
    }

    /**
     * Sets number of pairs to match
     *
     * @param numOfMatchesPerGame number
     */
    public void setNumOfMatchesPerGame(int numOfMatchesPerGame) {
        this.numOfMatchesPerGame = numOfMatchesPerGame;
    }

}
