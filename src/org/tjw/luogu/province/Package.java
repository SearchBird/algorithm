package org.tjw.luogu.province;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Package {

    private static int[] moneys = new int[]{0,2,3,4,35,5,20,70};

    public static void main(String[] args) {
        Package mypackage = new Package();

        // 01背包
        System.out.println("二维01背包最大总价值:" + mypackage.package01_01(80));
        System.out.println("滚动01背包最大总价值:" + mypackage.package01_02(80));
        System.out.println("一维01背包最大总价值:" + mypackage.package01_03(80));

        // 一百元数量，需要最少张钞票
        //oneHundred.dp(100);
        System.out.println(mypackage.recurtion(20) - 1);
    }

    // 根据 min(money) = min(money - moneys[i]) + 1 写出递归
    public int recurtion(int money) {
        // 计算到为0的返回
        if(money != 0) {
            // min下一项统计数最小值 min = min(money - moneys[i])
            int min = Integer.MAX_VALUE;
            for(int i = 1;i < moneys.length;i ++) {
                // 将可行的元素放行
                if(money - moneys[i] >= 0) {
                    // 往下递归钱数量和统计数
                    int temp = recurtion(money - moneys[i]);
                    // 获取c(money - moneys[i])最小统计数
                    if(min > temp) {
                        min = temp;
                    }
                }
            }
            // 返回c(money - moneys[i]) + 1
            if(min != Integer.MAX_VALUE) {
                return min + 1;
            }
                // 返回这个最大值是无效的数字
            else return min;
        } else return 1;

    }

    /**
     * 滚动数组，一维实现01背包
     */
    public int package01_01(int capital) {

        int[][] dp = new int[2][capital];
        for(int i = moneys[0];i < capital;i ++) {
            dp[0][i] = moneys[0];
        }

        int k = 0;
        for(int i = 1;i < moneys.length;i ++) {
            for(int j = 1;j < capital;j ++) {
                k = i & 1;// 相当于 k = i % 2;
                dp[k][j] = dp[k ^ 1][j];
                // 如果有超过容量的物体时候，就出事了
                if(j >= moneys[i])
                    dp[k][j] = Math.max(dp[k][j], dp[k ^ 1][j - moneys[i]] + moneys[i]);
            }
        }
        return dp[k][capital - 1];
    }

    /**
     * 二维实现01背包
     */
    public int package01_02(int capital) {

        int[][] dp = new int[moneys.length][capital + 1];
        for(int i = moneys[1];i <= capital;i ++) {
            dp[0][i] = moneys[0];
        }

        for(int i = 2;i < moneys.length;i ++) {
            for(int j = 1;j <= capital;j ++) {
                dp[i][j] = dp[i - 1][j];
                // 如果有超过容量的物体时候，就出事了
                if(j >= moneys[i])
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - moneys[i]] + moneys[i]);
            }
        }
        return dp[moneys.length - 1][capital];
    }

    public int package01_03(int capital) {
        int[] dp = new int[capital + 1];

        for(int i = 2;i < moneys.length;i ++) {
            for(int j = capital;j >= moneys[i];j --) {
                dp[j] = Math.max(dp[j], dp[j - moneys[i]] + moneys[i]);
            }
        }
        return dp[capital];
    }
}
