package org.tjw.codewar.Trainning.kyu4.QRCode;

import org.tjw.codewar.Trainning.kyu4.QRCode.QREnum.*;

public class QRPlacer {

    private static int[][] finder = new int[][]{
            {1, 1, 1, 1, 1, 1, 1},
            {1, 2, 2, 2, 2, 2, 1},
            {1, 2, 1, 1, 1, 2, 1},
            {1, 2, 1, 1, 1, 2, 1},
            {1, 2, 1, 1, 1, 2, 1},
            {1, 2, 2, 2, 2, 2, 1},
            {1, 1, 1, 1, 1, 1, 1}},
                            alignment = new int[][]{  /// 版本2以上使用
            {1, 1, 1, 1, 1},
            {1, 2, 2, 2, 1},
            {1, 2, 1, 2, 1},
            {1, 2, 2, 2, 1},
            {1, 1, 1, 1, 1}};

    // 放数据 <- / 上 <- \ 下
    private static int[][] dirs = {{0, -1}, {-1, 1}, {0, -1}, {1, 1}};

    /**
     * 填充二维码, 1 黑色, 2 白色
     */
    public static int[][] createQRPlace(String bitText, QRVersionEnum versionEnum, QRErrorEnum errorEnum, QRMaskPattern maskPattern) {
        int len = (versionEnum.getVersion() - 1) * 4 + 21;
        int[][] qrPlace = new int[len][len];
        DrawUtil util = new DrawUtil();

        placeFinder(qrPlace, len);
        placeSeparators(qrPlace, len);
        if(versionEnum.getVersion() > 1) placeAlignment(qrPlace, versionEnum);
        placeTiming(qrPlace);
        placeDarkAndReserve(qrPlace, versionEnum);
        placeFormat(qrPlace, errorEnum, maskPattern);
        placeData(qrPlace, bitText, maskPattern);
        if(versionEnum.getVersion() >= 7) placeVersion(qrPlace, versionEnum);

        return qrPlace;
    }

    private static void placeVersion(int[][] qrPlace, QRVersionEnum versionEnum) {
        QRVersionInfo info = QRVersionInfo.getEnum(versionEnum.getVersion());
        char[] chs = info.getVersionInfoStr().toCharArray();
        int begin = 0, end = chs.length, len = qrPlace.length,
                x1 = len - 9, y1 = 5, x2 = 5, y2 = len - 9;
        while(begin < end) {
            if(isVersionLimit(x1, len)) {
                x1 = len - 9;
                y2 = len - 9;

                y1 --;
                x2 --;
            }

            qrPlace[x1][y1] = chs[begin] == '0' ? 2 : 1;
            qrPlace[x2][y2] = chs[begin ++] == '0' ? 2 : 1;

            x1 --;
            y2 --;
        }
    }

    private static boolean isVersionLimit(int x, int len) {
        return x > len - 9 || x < len - 11;
    }

    private static void placeFormat(int[][] qrPlace, QRErrorEnum errorEnum, QRMaskPattern maskPattern) {
        String formatInfoBits = QRFormatAndVersionInfo.formatInfo(errorEnum, maskPattern);
        int len = qrPlace.length;
        fillWithXY(8, 0, formatInfoBits.charAt(0), qrPlace);
        fillWithXY(8, 1, formatInfoBits.charAt(1), qrPlace);
        fillWithXY(8, 2, formatInfoBits.charAt(2), qrPlace);
        fillWithXY(8, 3, formatInfoBits.charAt(3), qrPlace);
        fillWithXY(8, 4, formatInfoBits.charAt(4), qrPlace);
        fillWithXY(8, 5, formatInfoBits.charAt(5), qrPlace);
        fillWithXY(8, 7, formatInfoBits.charAt(6), qrPlace);
        fillWithXY(8, 8, formatInfoBits.charAt(7), qrPlace);

        fillWithXY(7, 8, formatInfoBits.charAt(8), qrPlace);
        fillWithXY(5, 8, formatInfoBits.charAt(9), qrPlace);
        fillWithXY(4, 8, formatInfoBits.charAt(10), qrPlace);
        fillWithXY(3, 8, formatInfoBits.charAt(11), qrPlace);
        fillWithXY(2, 8, formatInfoBits.charAt(12), qrPlace);
        fillWithXY(1, 8, formatInfoBits.charAt(13), qrPlace);
        fillWithXY(0, 8, formatInfoBits.charAt(14), qrPlace);

        // 右上和左下
        fillWithXY(len - 1, 8, formatInfoBits.charAt(0), qrPlace);
        fillWithXY(len - 2, 8, formatInfoBits.charAt(1), qrPlace);
        fillWithXY(len - 3, 8, formatInfoBits.charAt(2), qrPlace);
        fillWithXY(len - 4, 8, formatInfoBits.charAt(3), qrPlace);
        fillWithXY(len - 5, 8, formatInfoBits.charAt(4), qrPlace);
        fillWithXY(len - 6, 8, formatInfoBits.charAt(5), qrPlace);
        fillWithXY(len - 7, 8, formatInfoBits.charAt(6), qrPlace);

        fillWithXY(8, len - 1, formatInfoBits.charAt(14), qrPlace);
        fillWithXY(8, len - 2, formatInfoBits.charAt(13), qrPlace);
        fillWithXY(8, len - 3, formatInfoBits.charAt(12), qrPlace);
        fillWithXY(8, len - 4, formatInfoBits.charAt(11), qrPlace);
        fillWithXY(8, len - 5, formatInfoBits.charAt(10), qrPlace);
        fillWithXY(8, len - 6, formatInfoBits.charAt(9), qrPlace);
        fillWithXY(8, len - 7, formatInfoBits.charAt(8), qrPlace);
        fillWithXY(8, len - 8, formatInfoBits.charAt(7), qrPlace);
    }

    private static void fillWithXY(int x, int y, char c, int[][] qrPlace) {
        qrPlace[x][y] = c == '0' ? 2 : 1;
    }

    private static void placeData(int[][] qrPlace, String bitText, QRMaskPattern maskPattern) {
        int begin = 0;
        char[] chs = bitText.toCharArray();
        int x = qrPlace.length - 1, y = qrPlace.length - 1, dir = 0, extDir = 0;

        while(y >= 0) {
            if(isLimit(x, y, qrPlace.length, qrPlace[0].length)) {
                dir ^= 1;
                x -= dirs[dir + extDir][0]; // 回退
                y -= dirs[dir + extDir][1];
                y -= 1;
                if(y < 0) break;
                dir ^= 1; // 换方向
                extDir ^= 2;
            }
            if(y == 6) y -= 1;
            if(qrPlace[x][y] == 0) {
                switch (chs[begin ++]) { // 放置 原来颜色 0-白(1) 1-黑(2), 遮罩就反过来
                    case '0' : qrPlace[x][y] = QRUtil.maskCalculate(maskPattern, x, y) ? 1 : 2; break;
                    case '1' : qrPlace[x][y] = QRUtil.maskCalculate(maskPattern, x, y) ? 2 : 1; break;
                }
            }
            x += dirs[dir + extDir][0];
            y += dirs[dir + extDir][1];
            dir ^= 1;
        }
    }

    private static boolean isLimit(int x, int y, int xlen, int ylen) {
        return x < 0 || x == xlen || y < 0 || y == ylen;
    }

    /**
     * 添加暗区和保留区
     */
    private static void placeDarkAndReserve(int[][] qrPlace, QRVersionEnum versionEnum) {
        qrPlace[4 * versionEnum.getVersion() + 9][8] = 1;

        // 格式保留
        qrPlace[8][8] = 3;
        for(int i = 0, len = qrPlace.length; i < 8; i ++) {
            if(qrPlace[i][8] == 0) qrPlace[i][8] = 3;
            if(qrPlace[8][i] == 0) qrPlace[8][i] = 3;
            if(qrPlace[len - 1 - i][8] == 0) qrPlace[len - 1 - i][8] = 3;
            qrPlace[8][len - 1 - i] = 3;
        }

        // 信息保留
        if(versionEnum.getVersion() >= 7) {
            for(int i = 0, len = qrPlace.length; i < 6; i ++) {
                qrPlace[len - 9][i] = 3;
                qrPlace[len - 10][i] = 3;
                qrPlace[len - 11][i] = 3;
                qrPlace[i][len - 9] = 3;
                qrPlace[i][len - 10] = 3;
                qrPlace[i][len - 11] = 3;
            }
        }
    }

    /**
     * 计时模块, 黑白相间
     */
    private static void placeTiming(int[][] qrPlace) {
        // 横
        for(int i = 8, len = qrPlace.length, color = 0; i < len; i ++, color ^= 1)
            if(qrPlace[6][i] == 0) qrPlace[6][i] = color + 1;

        // 竖
        for(int i = 8, len = qrPlace.length, color = 0; i < len; i ++, color ^= 1)
            if(qrPlace[i][6] == 0) qrPlace[i][6] = color + 1;
    }

    /**
     * 对齐定位
     */
    private static void placeAlignment(int[][] qrPlace, QRVersionEnum versionEnum) {
        QRAlignmentEnum alignmentEnum = QRAlignmentEnum.getQRAlignmentEnum(versionEnum);
        for(int i = 0, site[] = alignmentEnum.getSite(), size = site.length; i < size; i ++) {
            for(int j = 0; j < size; j ++) {
                if(qrPlace[site[i]][site[j]] != 0) continue;
                // alignment
                for(int ii = site[i] - 2, endi = site[i] + 2; ii <= endi; ii ++)
                    for(int jj = site[j] - 2, endj = site[j] + 2; jj <= endj; jj ++)
                        qrPlace[ii][jj] = alignment[ii - site[i] + 2][jj - site[j] + 2];
            }
        }
    }

    /**
     * 加分割器
     */
    private static void placeSeparators(int[][] qrPlace, int len) {
        for(int i = 0; i < 8; i ++) {
            // 左上
            qrPlace[7][i] = 2;
            qrPlace[i][7] = 2;
            // 右上
            qrPlace[7][len - 1 - i] = 2;
            qrPlace[i][len - 8] = 2;
            // 左下
            qrPlace[len - 8][i] = 2;
            qrPlace[len - 1 - i][7] = 2;
        }
    }

    /**
     * 定位器
     */
    private static void placeFinder(int[][] qrPlace, int len) {
        drawFinder(qrPlace, 0, 0);
        drawFinder(qrPlace, len - 7, 0);
        drawFinder(qrPlace, 0, len - 7);
    }

    private static void drawFinder(int[][] qrPlace, int x, int y) {
        for(int i = x, lenx = x + 7; i < lenx; i ++)
            for(int j = y, leny = y + 7; j < leny; j ++)
                qrPlace[i][j] = finder[i - x][j - y];
    }
}
