package org.tjw.leetcode.algorithm.changlle30;

import org.tjw.leetcode.algorithm.dfs.WordSearchII;

public class TotalHammingDistance {
    public static void main(String[] args) {
        Solution solution = new TotalHammingDistance().new Solution();

        int[] arr = new int[]{4,14,4,14};// 0100  1110  0100  1110
        System.out.println(solution.totalHammingDistance(arr));
    }
    class Solution {
        public int totalHammingDistance(int[] nums) {
            int len = nums.length;
            int count = 0;

            for(int i = 0;i < 32;i ++) {
                // 把每个位元的 1 统计出来
                int total = 0;
                for(int j = 0;j < len;j ++)
                    total += (nums[j] >> i) & 1;
                // (L - N) * N
                count += (len - total) * total;
            }
            return count;
        }
    }
}
