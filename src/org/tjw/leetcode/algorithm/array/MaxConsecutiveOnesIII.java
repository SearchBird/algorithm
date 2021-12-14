package org.tjw.leetcode.algorithm.array;

import java.util.ArrayList;
import java.util.List;

public class MaxConsecutiveOnesIII {

    public static void main(String[] args) {
        Solution s = new MaxConsecutiveOnesIII().new Solution();
        System.out.println(s.longestOnes2(new int[]{0,1,0,1,0,1,0,1,0,1}, 3));
        System.out.println(s.longestOnes(new int[]{1,1,1,0,0,0,1,1,1,1,0}, 2));
        System.out.println(s.longestOnes(new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1}, 3));
        System.out.println(s.longestOnes2(new int[]{1,1,1,1,1,1,1,1,1,1,0,0,0,0}, 1));
    }

    class Solution {

        public int longestOnes2(int[] A, int K) {
            //1:10
            int N = A.length;
            int i = 0;
            for (int j=0; j<N; j++) {
                K -= 1 - A[j];
                if (K < 0)
                    K += 1 - A[i++];
            }
            return A.length - i;
        }

        public int longestOnes(int[] nums, int k) {
            List<Integer> cache = new ArrayList<>();
            int max = 0, left = -1, right = -1, tmp = k, zeroIndex = 0;
            for(int i = 0, len = nums.length; i < len; i ++) {
                if(nums[i] == 0) {
                    cache.add(i);
                    if(tmp > 0) {
                        right = i;
                        tmp --;
                    } else {
                        left = cache.get(zeroIndex ++);
                        right ++;
                        while(right < len && nums[right] != 0) right ++;
                        int temp = right + 1;
                        while(temp < len && nums[temp]  == 1) temp ++;
                        right += temp - right - 1;
                    }
                }
                if(right == -1 || left == -1) max = i + 1;
                else max = Math.max(max, right - left);
            }
            return max;
        }
    }
}
