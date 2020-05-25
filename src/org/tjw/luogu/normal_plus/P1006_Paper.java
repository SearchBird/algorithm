package org.tjw.luogu.normal_plus;

import java.util.Scanner;

public class P1006_Paper {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();

        int[][] arr = new int[x << 1 + 1][y << 1 + 1];

        for(int i = 1;i <= x;i ++) {
            for(int j = 1;j <= y;j ++) {
                arr[i][j] = sc.nextInt();
            }
        }
        /** 3 3
         0 3 9
         2 8 5
         5 7 0
         */
        System.out.print(dp(x,y,arr));
    }

    public static int dp(int x, int y,int[][] arr) {
        int[][][] favor = new int[x << 1][y << 1][x << 1];

        for(int i = 1;i <= x;i++) {
            for(int j = 1;j <= y;j ++) {
                for(int k = 1;k <= x;k ++) {
                    int d = i + j - k;
                    if(d < 0) break;
                    if(i != k && j != d) {
                        // i - 1的维度下,进行k 和 k - 1的比较favor[i - 1][j][k] favor[i - 1][j][k - 1]
                        // j - 1的维度下,进行k 和 k - 1的比较favor[i - 1][j][k] favor[i - 1][j][k - 1]
                        // 然后两者再进行最大值比较
                        int temp = Math.max(Math.max(favor[i - 1][j][k], favor[i - 1][j][k - 1]),
                                Math.max(favor[i][j - 1][k], favor[i][j - 1][k - 1]));

                        // 将 favor[i][j][k] 当前和 上面比较得出的以及arr原来维度的相加
                        favor[i][j][k] = Math.max(favor[i][j][k], temp + arr[i][j] + arr[k][d]);
                    }
                }
            }
        }

        // 返回 i j k != j的维度
        return favor[x][y][x - 1];
    }
}
