package org.tjw.leetcode.algorithm.changlle30;

import java.util.Arrays;

public class LargestTimeforGivenDigits {

    public static void main(String[] args) {
        Solution solution  = new LargestTimeforGivenDigits().new Solution();
        System.out.println(solution.largestTimeFromDigits(new int[]{1,9,6,0}));
        System.out.println(solution.largestTimeFromDigits(new int[]{0,0,0,0}));
        System.out.println(solution.largestTimeFromDigits(new int[]{5,5,5,0}));
        System.out.println(solution.largestTimeFromDigits(new int[]{5,5,5,5}));
        System.out.println(solution.largestTimeFromDigits(new int[]{1,9,6,7}));
        System.out.println(solution.largestTimeFromDigits(new int[]{1,9,6,5}));
        System.out.println(solution.largestTimeFromDigits(new int[]{1,9,4,9}));
        System.out.println(solution.largestTimeFromDigits(new int[]{2,0,8,8}));
        System.out.println(solution.largestTimeFromDigits(new int[]{9,0,7,7}));

    }

    private static String EMPTY = "";
    private static String SEM = ":";
    private static StringBuilder builder = new StringBuilder(); // 快点
    private static int[][] hours = new int[12][2], minutes = new int[12][2];
    class Solution {
        public String largestTimeFromDigits(int[] A) {
            Arrays.sort(A);
            boolean noFind = true;
            builder.setLength(0);
            int h = 0, m = 0,hindex = 0, mindex = 0;
            for(int i = 0;i < 4;i ++) // 进行遍历，找出所有合法数字
                for(int j = 0;j < 4;j ++)
                    if(i != j) {
                        int temp = A[i] * 10 + A[j];
                        if(temp < 24) { // 判断hours
                            hours[hindex][0] = i; // 记住, 这里存放的是 索引
                            hours[hindex ++][1] = j;
                        }
                        if(temp < 60) {
                            minutes[mindex][0] = i;
                            minutes[mindex ++][1] = j;
                        }
                    }
            if(hindex == 0 || mindex == 0) return EMPTY; // 判断合法
            A:for(int i = hindex;i -- > 0;) {
                for(int j = mindex;j -- > 0;) {
                    if(minutes[j][0] != hours[i][0]
                            && minutes[j][0] != hours[i][1]
                            && minutes[j][1] != hours[i][0]
                            && minutes[j][1] != hours[i][1]) {
                        h = A[hours[i][0]] * 10 + A[hours[i][1]]; // 通过索引获取 A[] 值
                        m = A[minutes[j][0]] * 10 + A[minutes[j][1]];
                        noFind = false; // 找到了
                        break A;
                    }
                }
            }
            if(noFind) return EMPTY; // 没找到, 不合法
            return builder.append(String.format("%02d", h))
                    .append(SEM)
                    .append(String.format("%02d", m))
                    .toString();
        }
    }
}
