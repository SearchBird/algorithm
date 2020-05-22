package org.tjw.luogu.province;

import java.util.Scanner;

/**
 * P1002
 */
public class P1002_House {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] input = new int[4];
        for(int i = 0;i < 4;i++) {
            input[i] = sc.nextInt();
        }
        addBad(input[0], input[1], input[2], input[3]);
        //System.out.println(dfs(input[0], input[1]));
        System.out.println(dp(input[0], input[1]));
        //System.out.print(dfs(20, 20, 3, 3));
    }


    static int[][] bad = new int[9][2];
    static int badindex = -1;
    static int badcount = -1;
    static boolean needCheck = true;

    public static long dp(int bx, int by) {
        // 得要用long
        long[][] dp = new long[bx + 1][by + 1];

        // 如果验证到了就跳出，因为控制点之后的点是无法到达的
        for(int i = 0;i <= bx;i++) {
            if(check( i,0, bx, by)) dp[i][0] = 1;
            else break;
        }

        for(int i = 0;i <= by;i++) {
            if(check( 0,i, bx, by)) dp[0][i] = 1;
            else break;
        }

        // 进行dp一下
        for(int i = 1;i <= bx;i++) {
            for(int j = 1;j <= by;j++) {
                // 如果控制点验证完了，就不需要进入验证了，所以使用了快速与
                if(needCheck && !check( i,j, bx, by)) continue;
                // 将上左两个点的数相加，因为该处保留了路径数
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[bx][by];
    }

    private static boolean check(int x, int y, int bx, int by) {
        for(int i = 0;i <= badindex;i ++) {
            if(x == bad[i][0] && y == bad[i][1]) {
                badcount ++;
                if(badcount == badindex) needCheck = false;
                return false;
            }
            // dfs需要
            if(x > bx || y > by) return false;
        }
        return true;
    }

    private static void addBad(int bx, int by, int xh, int yh) {
        bad[++ badindex] = new int[]{xh, yh};
        if(xh - 1 >= 0) {
            if(yh - 2 >= 0) bad[++ badindex] = new int[]{xh - 1, yh - 2};
            if(yh + 2 <= by) bad[++ badindex] = new int[]{xh - 1, yh + 2};
        }
        if(xh - 2 >= 0) {
            if(yh - 1 >= 0) bad[++ badindex] = new int[]{xh - 2, yh - 1};
            if(yh + 1 <= by) bad[++ badindex] = new int[]{xh - 2, yh + 1};
        }
        if(xh + 2 <= bx) {
            if(yh - 1 >= 0) bad[++ badindex] = new int[]{xh + 2, yh - 1};
            if(yh + 1 <= by) bad[++ badindex] = new int[]{xh + 2, yh + 1};
        }
        if(xh + 1 <= bx) {
            if(yh - 2 >= 0) bad[++ badindex] = new int[]{xh + 1, yh - 2};
            if(yh + 2 <= by) bad[++ badindex] = new int[]{xh + 1, yh + 2};
        }
    }


}