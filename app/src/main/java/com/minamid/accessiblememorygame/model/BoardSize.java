package com.minamid.accessiblememorygame.model;

public enum BoardSize {
    SMALL_10,
    SMALL_12,
    SMALL_14,
    MEDIUM_10,
    MEDIUM_12,
    MEDIUM_14,
    LARGE_10,
    LARGE_12,
    LARGE_14;

    public static BoardSize getBoardSize(int boardSize) {
        switch (boardSize) {
            case 20:
                return BoardSize.SMALL_10;
            case 24:
                return BoardSize.SMALL_12;
            case 28:
                return BoardSize.SMALL_14;
            case 30:
                return BoardSize.MEDIUM_10;
            case 36:
                return BoardSize.MEDIUM_12;
            case 42:
                return BoardSize.MEDIUM_14;
            case 40:
                return BoardSize.LARGE_10;
            case 48:
                return BoardSize.LARGE_12;
            case 56:
                return BoardSize.LARGE_14;
                default:
                    return null;
        }
    }
}
