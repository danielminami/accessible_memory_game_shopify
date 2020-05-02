package com.minamid.accessiblememorygame.util;

import java.io.File;

public class Config {
    private static final Config ourInstance = new Config();

    public static final String URL = "https://shopicruit.myshopify.com/";
    public static final String IMAGE_LIST_ENDPOINT = "/admin/products.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6";
    public static File CACHE_LOCATION;
    public static final int CACHE_SIZE = 4092;

    // TODO: Make it dynamic
    // Configurations defined in the SettingsFragment
    private int pairsToMatchToCompleteGame = 10;
    private int numOfCardsToMakeMatch = 2;
    private int numberOfCards = pairsToMatchToCompleteGame * numOfCardsToMakeMatch;
    private int timeBoardRevealed = 10;

    public static Config getInstance() {
        return ourInstance;
    }

    private Config() {

    }

    /**
     * Number of Matches to complete the game
     *
     * @return number of matches
     */
    public int getPairsToMatchToCompleteGame() {
        return pairsToMatchToCompleteGame;
    }

    /**
     * Number of Matches to complete the game
     *
     * @param pairsToMatchToCompleteGame
     */
    public void setPairsToMatchToCompleteGame(int pairsToMatchToCompleteGame) {
        this.pairsToMatchToCompleteGame = pairsToMatchToCompleteGame;
    }

    /**
     * Number of Cards necessary to form a Match
     *
     * @return
     */
    public int getNumOfCardsToMakeMatch() {
        return numOfCardsToMakeMatch;
    }

    /**
     * Number of Cards necessary to form a Match
     *
     * @return
     */
    public void setNumOfCardsToMakeMatch(int numOfCardsToMakeMatch) {
        this.numOfCardsToMakeMatch = numOfCardsToMakeMatch;
    }

    /**
     * Gets the total number of cards in game
     *
     * @return number of cards
     */
    public int getNumberOfCards() {
        return numberOfCards;
    }

    /**
     * Gets the time the cards will be revealed before the game starts
     *
     * @return time the cards will be revealed
     */
    public int getTimeBoardRevealed() {
        return timeBoardRevealed;
    }

    /**
     * Sets the time the cards will be revealed before the game starts
     *
     */
    public void setTimeBoardRevealed(int timeBoardRevealed) {
        this.timeBoardRevealed = timeBoardRevealed;
    }
}
