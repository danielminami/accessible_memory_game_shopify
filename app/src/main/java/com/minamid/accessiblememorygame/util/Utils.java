package com.minamid.accessiblememorygame.util;

import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

import com.minamid.accessiblememorygame.model.BoardSize;

public class Utils {

    /**
     * This method is used to dynamically decide how many cards will be placed
     * for screen line.
     *
     * @return the number of columns of the board
     *
     * Credits: https://stackoverflow.com/a/38472370
     */
    public static int calculateNoOfColumns(Context context) {

        float columnWidthDp = 85 - (((Config.getPairsToMatch() * 2) + (Config.getNumOfMatchesPerGame())/2));

        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density;
        return (int)(screenWidthDp / columnWidthDp + 0.5);
    }

    public static int[] getBoardSize(Context context, int numberOfCards) {
        int orientation = context.getResources().getConfiguration().orientation;
        int[] pairValue = new int[2];
        BoardSize boardSize = BoardSize.getBoardSize(numberOfCards);
        if (boardSize != null) {

            if (boardSize == BoardSize.SMALL_10) {
                pairValue[0] = 5;
                pairValue[1] = 4;
            } else if (boardSize == BoardSize.SMALL_12) {
                pairValue[0] = 6;
                pairValue[1] = 4;
            } else if (boardSize == BoardSize.SMALL_14) {
                pairValue[0] = 7;
                pairValue[1] = 4;
            } else if (boardSize == BoardSize.MEDIUM_10) {
                pairValue[0] = 6;
                pairValue[1] = 5;
            } else if (boardSize == BoardSize.MEDIUM_12) {
                pairValue[0] = 6;
                pairValue[1] = 6;
                return pairValue;
            } else if (boardSize == BoardSize.MEDIUM_14) {
                pairValue[0] = 7;
                pairValue[1] = 6;
            } else if (boardSize == BoardSize.LARGE_10) {
                pairValue[0] = 8;
                pairValue[1] = 5;
            } else if (boardSize == BoardSize.LARGE_12) {
                pairValue[0] = 8;
                pairValue[1] = 6;
            } else if (boardSize == BoardSize.LARGE_14) {
                pairValue[0] = 8;
                pairValue[1] = 7;
            }

            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                return pairValue;
            } else {
                return new int[]{pairValue[1], pairValue[0]};
            }

        } else {
            return null;
        }
    }

}
