package org.tjw.codewar.Trainning;

/**
 * 生命细胞
 */
public class ConwayLife {

    public static void main(String[] args) {

        int[][] test = {
                {1,1,1,0,0,0,1,0},
                {1,0,0,0,0,0,0,1},
                {0,1,0,0,0,1,1,1}};
        int[][] cells = ConwayLife.getGeneration(test, 1);

        for(int i = 0;i < cells.length;i ++) {
            for(int j = 0;j < cells[0].length;j ++) {
                System.out.print(cells[i][j] + ",");
            }
            System.out.println();
        }
    }

    // empty_live
    private static int[][] temp_e;
    // save_live
    private static int[][] temp_s;
    // expend_flag
    private static boolean eF;

    public static int[][] getGeneration(int[][] cells, int generations) {
        // 因为codewar创建一个类就不断测试问题，所以每次进来都把开关弄成true
        eF = true;
        temp_s = cells;

        for(int g = generations;g -- > 0;) {

            if(eF) {
                expends(temp_s);
                for(int x = temp_s.length;x -- > 0;) {
                    for(int y = temp_s[0].length;y -- > 0;) {
                        temp_e[x + 1][y + 1] = temp_s[x][y];
                    }
                }
                temp_s = temp_e;
                temp_e = new int[temp_s.length][temp_s[0].length];
            }


            for(int x = temp_s.length;x -- > 0;) {
                for(int y = temp_s[0].length;y -- > 0;) {
                    int count = checkLife(temp_s, x, y);
                    if(chekcLive(temp_s[x][y], count)) {
                        if(x == 0 || y == 0 || x == temp_s.length - 1 || y == temp_s[0].length - 1) eF = true;
                        temp_e[x][y] = 1;
                    }
                    else temp_e[x][y] = 0;
                }
            }

            int[][] temp_m = temp_s;
            temp_s = temp_e;
            temp_e = temp_m;
        }
        temp_s = cutOff(temp_s);

        return temp_s;
    }

    public static int[][] cutOff(int[][] temp_s) {
        int xtop = 0;
        int xdow = 0;
        int ytop = 0;
        int ydow = 0;

        A:for(int i = 0;i < temp_s.length;i ++) {
            for(int j = 0;j < temp_s[0].length;j ++ ) {
                if(temp_s[i][j] != 0) {
                    xtop = i;
                    break A;
                }
            }
        }

        A:for(int j = 0;j < temp_s[0].length;j ++ ) {
            for(int i = 0;i < temp_s.length;i ++) {
                if(temp_s[i][j] != 0) {
                    ytop = j;
                    break A;
                }
            }
        }

        A:for(int j = temp_s[0].length;j -- > 0;) {
            for(int i = temp_s.length;i -- > 0;) {
                if(temp_s[i][j] != 0) {
                    ydow = j;
                    break A;
                }
            }
        }

        A:for(int i = temp_s.length;i -- > 0;) {
            for(int j = temp_s[0].length;j -- > 0;) {
                if(temp_s[i][j] != 0) {
                    xdow = i;
                    break A;
                }
            }
        }

        temp_e = new int[xdow - xtop + 1][ydow - ytop + 1];
        for(int i = xtop;i <= xdow;i ++) {
            for (int j = ytop;j <= ydow;j ++) {
                temp_e[i - xtop][j - ytop] = temp_s[i][j];
            }
        }
        temp_s = temp_e;
        return temp_s;
    }

    public static void expends(int[][] temp_s) {
        temp_e = new int[temp_s.length + 2][temp_s[0].length + 2];
        eF = false;
    }

    // 检查生命现象
    public static boolean chekcLive(int val, int count) {
        if(count > 3 || count < 2) return false;
        else if(count == 3 && val == 0) return true;
        else if(count < 4 && count > 1 && val != 0) return true;
        else return false;
    }

    // 统计活细胞
    public static int checkLife(int[][] temp, int x, int y) {
        int count = 0;
        int x1 = x - 1;
        int x2 = x + 1;
        int y1 = y - 1;
        int y2 = y + 1;

        if(x1 >= 0) {
            count += temp[x1][y];
            if(y1 >= 0) {
                count += temp[x1][y1];
            }
            if(y2 < temp[0].length) {
                count += temp[x1][y2];
            }
        }
        if(x2 < temp.length) {
            count += temp[x2][y];
            if(y1 >= 0) {
                count += temp[x2][y1];
            }
            if(y2 < temp[0].length) {
                count += temp[x2][y2];
            }
        }

        if(y1 >= 0) {
            count += temp[x][y1];
        }
        if(y2 < temp[0].length) {
            count += temp[x][y2];
        }

        return count;
    }
}