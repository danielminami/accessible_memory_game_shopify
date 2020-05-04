package com.minamid.accessiblememorygame.util;

import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

import com.minamid.accessiblememorygame.model.BoardSize;

public class Utils {

    /**
     * This method returns the board column size
     *
     * @return the column size
     **/
    public static int calculateColumnSize(Context context, BoardSize boardSize) {

        float columnWidthDp = (((Config.getInstance().getPairsToMatchToCompleteGame() * 2) + (Config.getInstance().getNumOfCardsToMakeMatch())/2));

        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density;
        return (int) ((screenWidthDp / columnWidthDp) + getGridViewColumnSize(boardSize));

    }

    public static int getGridViewSize(Context context, BoardSize boardSize) {
        int orientation = context.getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            if (boardSize != null) {
                return calculateColumnSize(context, boardSize);
            }
        } else {
            if (boardSize != null) {
                return calculateColumnSize(context, boardSize);
            }
        }
        return 0;
    }

    public static int getGridViewColumnSize(BoardSize boardSize) {
        if (boardSize != null) {
            if (boardSize == BoardSize.SMALL_10) {
                return 120;
            } else if (boardSize == BoardSize.SMALL_12) {
                return 120;
            } else if (boardSize == BoardSize.SMALL_14) {
                return 100;
            } else if (boardSize == BoardSize.MEDIUM_10) {
                return 100;
            } else if (boardSize == BoardSize.MEDIUM_12) {
                return 90;
            } else if (boardSize == BoardSize.MEDIUM_14) {
                return 90;
            } else if (boardSize == BoardSize.LARGE_10) {
                return 80;
            } else if (boardSize == BoardSize.LARGE_12) {
                return 80;
            } else if (boardSize == BoardSize.LARGE_14) {
                return 70;
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
