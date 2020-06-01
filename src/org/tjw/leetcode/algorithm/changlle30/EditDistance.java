package org.tjw.leetcode.algorithm.changlle30;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EditDistance {


    public static void main(String[] args) {

        Solution solution = new EditDistance().new Solution();

        String ss01 = "horse";
        String ss02 = "ros";


        System.out.println(solution.minDistance(ss01,ss02));

    }

    class Solution {
        public int minDistance(String word1, String word2) {
            int len01 = word1.length();
            int len02 = word2.length();
            char[] chars1 = word1.toCharArray();
            char[] chars2 = word2.toCharArray();
            // A:根据算法需要把所有字符遍历需要长度+1
            int[][] dp = new int[len01 + 1][len02 + 1];

            // 记忆化搜索
            // 第一列增加操作
            for(int i = 0;i <= len01;i ++) {
                dp[i][0] = i;
            }
            // 第一行删除操作
            for(int i = 0;i <= len02;i ++) {
                dp[0][i] = i;
            }

            for(int i = 1; i <= len01; i++) {
                for(int j = 1; j <= len02; j++) {
                    // 根据A: 操作数 = 判断上一行列字符，如果相等为0，不进行修改
                    int op = chars1[i - 1] == chars2[j - 1] ? 0 : 1;
                    // 选择最小操作数
                    dp[i][j] = Math.min(op + dp[i - 1][j - 1],
                            Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
                }
            }
            return dp[len01][len02];
        }
    }
}
