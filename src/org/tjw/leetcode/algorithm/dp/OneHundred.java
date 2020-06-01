package org.tjw.leetcode.algorithm.dp;

import java.util.List;

public class OneHundred {

    private static int[] moneys = new int[]{5,20,70};

    public static void main(String[] args) {
        OneHundred oneHundred = new OneHundred();
        //oneHundred.dp(100);
        System.out.println(oneHundred.recurtion(80) - 1);
    }

    // 根据 min(money) = min(money - moneys[i]) + 1 写出递归
    public int recurtion(int money) {
        // 计算到为0的返回
        if(money != 0) {
            // min下一项统计数最小值 min = min(money - moneys[i])
            int min = Integer.MAX_VALUE;
            for(int i = 0;i < moneys.length;i ++) {
                // 将可行的元素放行
                if(money - moneys[i] >= 0) {
                    // 往下递归钱数量和统计数
                    int temp = recurtion(money - moneys[i]);
                    // 获取c(money - moneys[i])最小统计数
                    min = min < temp ? min : temp;
                }
            }

            // 返回c(money - moneys[i]) + 1
            if(min != Integer.MAX_VALUE) return min + 1;
            // 返回这个最大值是无效的数字
            else return min;
        } else return 1;

    }

    public List<Integer> dp(int money) {
        int[] package1 = new int[money + 1];

        for(int i = 100;i -- > 0;) {
            for(int j = 0;j < moneys.length;j ++) {

            }
        }
        return null;
    }
}
