package org.tjw.codewar.Trainning.kyu4.QRCode;

import org.tjw.codewar.Trainning.kyu4.QRCode.QREnum.QRVersionEnum;

public class QRPlacerHelper {


    public static boolean isUpRightTimingArea(int x, int y, int[][] upRightTimingArea) {
        return x <= upRightTimingArea[0][1]
                && x >= upRightTimingArea[0][0]
                && y <= upRightTimingArea[1][1]
                && y >= upRightTimingArea[1][0];
    }

    public static boolean isAcrossTimingArea(int x, int y, int[][] acrossTimingArea) {
        return x <= acrossTimingArea[0][1]
                && x >= acrossTimingArea[0][0]
                && y <= acrossTimingArea[1][1]
                && y >= acrossTimingArea[1][0];
    }

    public static boolean isLimitArea(int x, int y, int[][][] finderArea) {
        if(x == finderArea.length || x < 0 || y == finderArea.length || y < 0) return true;
        for(int i = 0, len = finderArea.length; i < len; i ++)
            if(x >= finderArea[i][0][0]
                    && x <= finderArea[i][0][1]
                    && y >= finderArea[i][1][0]
                    && y <= finderArea[i][1][1]) return true;
        return false;
    }

    public static boolean isV7LimitArea(int x, int y, int[][][] v7UpRightLimitArea) {
        for(int i = 0; i < 2; i ++)
            if(x <= v7UpRightLimitArea[i][0][1]
            && x >= v7UpRightLimitArea[i][0][0]
            && y <= v7UpRightLimitArea[i][1][1]
            && y >= v7UpRightLimitArea[i][1][0]) return true;
        return false;
    }

    public static int[][][] calculateV7LimitArea(int len, QRVersionEnum versionEnum) {
        if(versionEnum.getVersion() < 7) return null;
        int[][][] v7LimitArea = new int[][][]{{{6, 6},{len - 11,len - 9}}, // 右上 信息 + timing
                                              {{len - 11,len - 11},{0,5}}};
        return v7LimitArea;
    }

    public static int[][][] calculateTimingArea(int len) {
        int[][][] timingArea = new int[][][]{{{6, 6}, {9, len - 9}},
                {{9, len - 9}, {6, 6}}};
        return timingArea;
    }

    public static int[][][] calculateFinderArea(int len, QRVersionEnum versionEnum) {
        int finderNum = 8;
        // 限制区间
        int[][][] finderArea = new int[][][]{{{0,8},{8,8}}, // 左上
                {{8,8},{0,8}},
                {{len - 8,len - 1},{8,8}}, // 左下
                {{len - 8,len - 8},{0,8}},
                {{0,8},{len - 8,len - 8}}, // 右上
                {{8,8},{len - 8,len - 1}},
                {{len - 11,len - 11},{0,5}}, // 左下 信息
                {{len - 11,len - 9},{5,5}},
        };
        if(versionEnum.getVersion() < 7) finderNum = 6;
        int[][][] finderAreaRes = new int[finderNum][2][2];
        for(int i = 0; i < finderNum; i ++) finderAreaRes[i] = finderArea[i];
        return finderAreaRes;
    }
}
