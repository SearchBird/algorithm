package org.tjw.leetcode.algorithm.changlle30;

/**
 * 和Longest Common Subsequence一样意思，就是动态规划
 */
public class UncrossedLines {
    class Solution {
        public int maxUncrossedLines(int[] A, int[] B) {
            if(A == null || B == null) return 0;
            int Alen = A.length;
            int Blen = B.length;
            int[][] dp = new int[Alen + 1][Blen + 1];

            for(int i = Alen;i -- > 0;) {
                for(int j = Blen;j -- > 0;) {
                    if(A[i] == B[j]) dp[i][j] += 1 + dp[i + 1][j + 1];
                    else dp[i][j] += dp[i + 1][j] < dp[i][j + 1] ? dp[i][j + 1] : dp[i + 1][j];
                }
            }

            return dp[0][0];
        }
    }
}
