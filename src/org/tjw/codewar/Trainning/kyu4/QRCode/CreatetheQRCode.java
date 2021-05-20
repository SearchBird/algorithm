package org.tjw.codewar.Trainning.kyu4.QRCode;
public class CreatetheQRCode {
    // 可用空格 208, qr 里面 0 是 白 1 是 黑
    private static int [][] qrcode =
                      new int[][]{{1,1,1,1,1,1,1,0,1, 0,0,0,0, 0,1,1,1,1,1,1,1},
                                  {1,0,0,0,0,0,1,0,0, 0,0,0,0, 0,1,0,0,0,0,0,1},
                                  {1,0,1,1,1,0,1,0,0, 0,0,0,0, 0,1,0,1,1,1,0,1},
                                  {1,0,1,1,1,0,1,0,1, 0,0,0,0, 0,1,0,1,1,1,0,1},
                                  {1,0,1,1,1,0,1,0,0, 0,0,0,0, 0,1,0,1,1,1,0,1},
                                  {1,0,0,0,0,0,1,0,0, 0,0,0,0, 0,1,0,0,0,0,0,1},

                                  {1,1,1,1,1,1,1,0,1, 0,1,0,1, 0,1,1,1,1,1,1,1},

                                  {0,0,0,0,0,0,0,0,0, 0,0,0,0, 0,0,0,0,0,0,0,0},
                                  {0,0,1,0,1,1,1,0,1, 0,0,0,0, 1,0,0,0,1,0,0,1},

                                  {0,0,0,0,0,0 ,0, 0,0, 0,0,0,0, 0,0,0,0,0,0,0,0},
                                  {0,0,0,0,0,0 ,1, 0,0, 0,0,0,0, 0,0,0,0,0,0,0,0},
                                  {0,0,0,0,0,0 ,0, 0,0, 0,0,0,0, 0,0,0,0,0,0,0,0},
                                  {0,0,0,0,0,0 ,1, 0,0, 0,0,0,0, 0,0,0,0,0,0,0,0},

                                  {0,0,0,0,0,0,0,0,1, 0,0,0,0, 0,0,0,0,0,0,0,0},
                                  {1,1,1,1,1,1,1,0,0, 0,0,0,0, 0,0,0,0,0,0,0,0},
                                  {1,0,0,0,0,0,1,0,1, 0,0,0,0, 0,0,0,0,0,0,0,0},
                                  {1,0,1,1,1,0,1,0,1, 0,0,0,0, 0,0,0,0,0,0,0,0},
                                  {1,0,1,1,1,0,1,0,0, 0,0,0,0, 0,0,0,0,0,0,0,0},
                                  {1,0,1,1,1,0,1,0,1, 0,0,0,0, 0,0,0,0,0,0,0,0},
                                  {1,0,0,0,0,0,1,0,0, 0,0,0,0, 0,0,0,0,0,0,0,0},
                                  {1,1,1,1,1,1,1,0,0, 0,0,0,0, 0,0,0,0,0,0,0,0}};
    private static int[] revAlp = new int[256],
                         orgAlp = new int[]{0, 43, 139, 206, 78, 43, 239, 123, 206, 214, 147, 24, 99, 150, 39, 243, 163, 136},
                         alp = new int[]
            {-1, 0, 1, 25, 2, 50, 26, 198, 3, 223, 51, 238, 27, 104, 199, 75, 4, 100, 224, 14, 52, 141, 239,
             129, 28, 193, 105, 248, 200, 8, 76, 113, 5, 138, 101, 47, 225, 36, 15, 33, 53, 147, 142, 218, 240, 18, 130,
             69, 29, 181, 194, 125, 106, 39, 249, 185, 201, 154, 9, 120, 77, 228, 114, 166, 6, 191, 139, 98, 102, 221, 48,
             253, 226, 152, 37, 179, 16, 145, 34, 136, 54, 208, 148, 206, 143, 150, 219, 189, 241, 210, 19, 92, 131, 56, 70, 64, 30, 66, 182, 163, 195,
             72, 126, 110, 107, 58, 40, 84, 250, 133, 186, 61, 202, 94, 155, 159, 10, 21, 121, 43, 78, 212, 229, 172, 115, 243, 167, 87, 7, 112, 192,
             247, 140, 128, 99, 13, 103, 74, 222, 237, 49, 197, 254, 24, 227, 165, 153, 119, 38, 184, 180, 124, 17, 68, 146, 217, 35, 32, 137, 46, 55,
             63, 209, 91, 149, 188, 207, 205, 144, 135, 151, 178, 220, 252, 190, 97, 242, 86, 211, 171, 20, 42, 93, 158, 132, 60, 57, 83, 71, 109, 65, 162,
             31, 45, 67, 216, 183, 123, 164, 118, 196, 23, 73, 236, 127, 12, 111, 246, 108, 161, 59, 82, 41, 157, 85, 170, 251, 96, 134, 177, 187, 204, 62,
             90, 203, 89, 95, 176, 156, 169, 160, 81, 11, 245, 22, 235, 122, 117, 44, 215, 79, 174, 213, 233, 230, 231, 173, 232, 116, 214, 244, 234, 168, 80, 88, 175};
    private static String[] seqStr = new String[]{"11101100", "00010001"};
    // 解码方向
    private static int[][] dir = {{0, -1}, {-1, 1}, {0, -1}, {1, 1}};
    // 限制区域
    private static int xline = 6, yline = 6, yLeftLine = 8,
                       block[][][] = {{{0, 8}, {13, 20}, {0, 8}},
                                      {{0, 8}, {0, 8},   {13, 20}}};

    static {
        for(int i = 1; i < 256; i ++) revAlp[alp[i]] = i;
    }

    public static void main(String[] args) throws Throwable {
        int[][] res = createQrCode("t"); // 7字符以内

        System.out.println();
        for(int i = 0; i < res.length; i ++) {
            for(int j = 0; j < res[0].length; j ++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }
        new DrawUtil().new QRDraw(res);
    }

    public static int[][] createQrCode(String text) {
        StringBuilder builder = new StringBuilder();
        builder.append("0100")
               .append(String.format("%08d",Integer.valueOf(Integer.toBinaryString(text.length()))));

        for(char ch : text.toCharArray())
            builder.append(String.format("%08d", Integer.valueOf(Integer.toBinaryString((int)ch))));

        builder.append(getLength(builder.length()));

        int cur = 0;
        while(builder.length() < 72) {
            builder.append(seqStr[cur]);
            cur ^= 1;
            if (builder.length() > 72) builder.setLength(72);
        }

        int[] group = new int[18];
        for(int i = 0; i < 9; i ++) group[i] = converBitToInt(builder.substring(i * 8, i * 8 + 8));

        for(int times = 0, begin = 0, end = 9, newMessage[] = new int[18]; times < 9; times ++) {
            // 多项式指数
            if(group[begin] == 0) { // 拿第一个, 非0
                begin ++; continue;
            }
            int add = alp[group[begin]];
            for(int i = 0; i < orgAlp.length; i ++)
                newMessage[i] = revAlp[(orgAlp[i] + add) % 255];

            for(int i = 0; i < 18; i ++)
                group[i] = begin < end ? newMessage[i] ^ group[begin ++] : newMessage[i];

            begin = 1;
            end = 18;
        }

        for(int i = 1; i < 18; i ++) {
            builder.append(String.format("%08d", Integer.valueOf(Integer.toBinaryString(group[i]))));
        }

        // 根据 01 从 右下角填充
        char[] chs = builder.toString().toCharArray();
        boolean begin = true;
        int x = qrcode.length - 1, y = qrcode[0].length - 1;
        for(int i = 0, len = builder.length(), d = 0, cd = 0; i < len;) {
            if(begin) {
                if(y == yline) y --;
                else if(y == yLeftLine) x = 12;
                fillColor(x, y, chs[i]); begin = false; i ++; continue;
            }
            int xd = x + dir[d + cd][0], yd = y + dir[d + cd][1];

            if(isRightBlock(xd, yd, qrcode.length)) { // 边界处理
                cd ^= 2; d ^= 1; y --; begin = true; continue;
            } else if(xd == xline) xd += dir[d + cd][0];

            fillColor(xd, yd, chs[i]);
            x = xd;
            y = yd;
            d = d ^ 1;
            i ++;
        }

        return qrcode;
    }

    private static void fillColor(int x, int y, char ch) {
        if((x + y & 1) == 0) // 偶数反色
            qrcode[x][y] = ch == '1' ? 0 : 1;
        else
            qrcode[x][y] = ch == '1' ? 1 : 0;
    }

    private static boolean isRightBlock(int x, int y, int qx) {
        if(x < 0 || x == qx) return true;
        for(int i = 0; i < 3; i ++)
            if(x >= block[0][i][0] && x <= block[0][i][1] && y >= block[1][i][0] && y <= block[1][i][1]) return true;
        return false;
    }

    private static int converBitToInt(String bits) {
        int total = 0, len = bits.length();
        for(int i = 0; i < len; i ++) {
           char ch = bits.charAt(7 - i);
           if(ch != '0') {
               total += 1 << i;
           }
        }
        return total;
    }

    private static String getLength(int len) {
        if (len < 69) return "0000";
        else if (len == 69) return "000";
        else if (len == 70) return  "00";
        else if (len == 71)  return  "0";
        else return "";
    }
}
