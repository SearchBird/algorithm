package org.tjw.leetcode.algorithm.array;

import java.util.Arrays;

public class SmallestRangeII {

    public static void main(String[] args) {
        Solution s = new SmallestRangeII().new Solution();
        System.out.println(s.smallestRangeII(new int[]{1,3,6}, 3));
        System.out.println(s.smallestRangeII(new int[]{1,2,6}, 3));
        System.out.println(s.smallestRangeII(new int[]{1,2,5,6,9}, 3));
        System.out.println(s.smallestRangeII(new int[]{7,8,8}, 5)); // 1
        System.out.println(s.smallestRangeII(new int[]{3,1,10}, 4)); // 2
        System.out.println(s.smallestRangeII(new int[]{4,1,8,10}, 3)); // 3
        System.out.println(s.smallestRangeII(new int[]{9,10,5,9}, 5)); // 5
        System.out.println(s.smallestRangeII(new int[]{4,8,2,7,2}, 5)); // 6
        System.out.println(s.smallestRangeII(new int[]{7,8,8,5,2}, 4)); // 5
    }

    class Solution {
        public int smallestRangeII(int[] A, int K) {
            Arrays.sort(A);
            int n = A.length, res = A[n - 1] - A[0];
            int left = A[0] + K, right = A[n - 1] - K;
            int high = 0 , low = 0;
            for (int i = 0; i < n - 1; ++i) {
                high = Math.max(right, A[i] + K);
                low = Math.min(left, A[i + 1] - K);
                res = Math.min(res, high - low);
            }
            return res;
        }
    }
}
