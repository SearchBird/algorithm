package org.tjw.leetcode.algorithm.array;

import java.util.Arrays;

public class MaximumLengthofRepeatedSubarray {

    public static void main(String[] args) {
        Solution s = new MaximumLengthofRepeatedSubarray().new Solution();
        s.findLength(new int[]{1,2,3,2,1}, new int[]{3,2,1,4,7});
    }

    class Solution {

        public int findLength(int[] nums1, int[] nums2) {
            int max = 0, index = 0, pre = 0;
            int[] begin = new int[101], end = new int[101], next = new int[nums2.length];
            Arrays.fill(begin, -1);
            Arrays.fill(end, -1);
            Arrays.fill(next, -1);

            for(int i = 0, len = nums2.length; i < len; i ++) {
                if(begin[nums2[i]] == -1) {
                    begin[nums2[i]] = end[nums2[i]] = i;
                } else {
                    next[end[nums2[i]]] = i;
                    end[nums2[i]] = i;
                }
            }

            int dp[][] = new int[nums1.length][nums2.length];

            for(int i = 0, len1 = nums1.length; i < len1; i ++, pre = 0)
                for(int j = begin[nums1[i]]; j != -1; j = next[j]) {
                    dp[i][j] = i - 1 >= 0 && j - 1 >= 0 ? dp[i - 1][j - 1] + 1 : 1;
                    max = Math.max(max, dp[i][j]);
                }

            return max;
        }
    }
}
