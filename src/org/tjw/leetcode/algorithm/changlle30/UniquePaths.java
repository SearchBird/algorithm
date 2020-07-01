package org.tjw.leetcode.algorithm.changlle30;

public class UniquePaths {

    public static void main(String[] args) {
        Solution solution = new UniquePaths().new Solution();


        System.out.println(solution.uniquePaths(3,2));
    }

    class Solution {
        public int uniquePaths(int m, int n) {
            int[][] dp = new int[m][n];
            for(int i = 0;i < m; i ++) dp[i][0] = 1;
            for(int i = 0;i < n; i ++) dp[0][i] = 1;

            for(int i = 1;i < n;i ++) {
                for(int j = 1;j < m ;j ++) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }

            return dp[m - 1][n - 1];
        }
    }
}
