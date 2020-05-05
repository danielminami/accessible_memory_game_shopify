package com.minamid.accessiblememorygame.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.util.DisplayMetrics;

import com.minamid.accessiblememorygame.model.BoardSize;

import okhttp3.internal.Util;

public class Utils {

    /**
     * This method returns the board column size
     *
     * @return the column size
     **/
    public static int calculateColumnSize(Activity activity, BoardSize boardSize) {

        Point screenWidth = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(screenWidth);
        return (int) (((double) screenWidth.x) / (Utils.getBoardSize(Config.getInstance().getNumberOfCards()) + Utils.getGridViewColumnSize(boardSize)));

//        float columnWidthDp = (((Config.getInstance().getPairsToMatchToCompleteGame() * 2) + (Config.getInstance().getNumOfCardsToMakeMatch())/2));
//        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
//        float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density;
//        return (int) ((screenWidthDp / columnWidthDp) + getGridViewColumnSize(boardSize));


    }

    public static int getGridViewSize(Activity activity, BoardSize boardSize) {
        int orientation = activity.getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            if (boardSize != null) {
                return calculateColumnSize(activity, boardSize);
            }
        } else {
            if (boardSize != null) {
                return calculateColumnSize(activity, boardSize);
            }
        }
        return 0;
    }

    public static double getGridViewColumnSize(BoardSize boardSize) {
        if (boardSize != null) {
            if (boardSize == BoardSize.SMALL_10) {
                return 1;
            } else if (boardSize == BoardSize.SMALL_12) {
                return 1.5;
            } else if (boardSize == BoardSize.SMALL_14) {
                return 2.5;
            } else if (boardSize == BoardSize.MEDIUM_10) {
                return 1;
            } else if (boardSize == BoardSize.MEDIUM_12) {
                return 1;
            } else if (boardSize == BoardSize.MEDIUM_14) {
                return 1.5;
            } else if (boardSize == BoardSize.LARGE_10) {
                return 2.5;
            } else if (boardSize == BoardSize.LARGE_12) {
                return 2;
            } else if (boardSize == BoardSize.LARGE_14) {
                return 2;
            }
        }
        return 0;
    }

    /**
     * This method returns the vertical spacing to be applied by the GridView depending on
     * the Board size.
     *
     * @param boardSize
     * @return
     */
    public static int getGridViewVerticalSpacing(BoardSize boardSize) {
        if (boardSize != null) {
            if (boardSize == BoardSize.SMALL_10) {
                return 0;
            } else if (boardSize == BoardSize.SMALL_12) {
                return 0;
            } else if (boardSize == BoardSize.SMALL_14) {
                return 0;
            } else if (boardSize == BoardSize.MEDIUM_10) {
                return 0;
            } else if (boardSize == BoardSize.MEDIUM_12) {
                return 20;
            } else if (boardSize == BoardSize.MEDIUM_14) {
                return 0;
            } else if (boardSize == BoardSize.LARGE_10) {
                return 0;
            } else if (boardSize == BoardSize.LARGE_12) {
                return 0;
            } else if (boardSize == BoardSize.LARGE_14) {
                return 20;
            }
        }
        return 0;
    }

    /**
     * This method returns the number of Columns to create the board.
     *
     * @param numberOfCards
     * @return number of board columns
     */
    public static int getBoardSize(int numberOfCards) {
        int numOfColumns = 0;
        BoardSize boardSize = BoardSize.getBoardSize(numberOfCards);

        if (boardSize != null) {
            if (boardSize == BoardSize.SMALL_10) {
                 numOfColumns = 4;
            } else if (boardSize == BoardSize.SMALL_12) {
                numOfColumns = 4;
            } else if (boardSize == BoardSize.SMALL_14) {
                numOfColumns = 4;
            } else if (boardSize == BoardSize.MEDIUM_10) {
                numOfColumns = 5;
            } else if (boardSize == BoardSize.MEDIUM_12) {
                numOfColumns = 6;
            } else if (boardSize == BoardSize.MEDIUM_14) {
                numOfColumns = 6;
            } else if (boardSize == BoardSize.LARGE_10) {
                numOfColumns = 5;
            } else if (boardSize == BoardSize.LARGE_12) {
                numOfColumns = 6;
            } else if (boardSize == BoardSize.LARGE_14) {
                numOfColumns = 7;
            }
        }
        return numOfColumns;
    }

}
