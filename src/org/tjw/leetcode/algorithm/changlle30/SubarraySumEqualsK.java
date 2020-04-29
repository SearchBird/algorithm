package org.tjw.leetcode.algorithm.changlle30;


import java.util.HashMap;
import java.util.HashSet;

public class SubarraySumEqualsK {
    public static void main(String[] args) {
        Solution solution = new SubarraySumEqualsK().new Solution();

        int[] arr = new int[]{1,2,3,4,5,6,7,1,23,21,3,1,2,1,1,1,1,1,12,2,3,2,3,2,2}; // 12
        int[] arr2 = new int[]{100,1,2,3,4}; // 6
        int[] arr3 = new int[]{28,54,7,-70,22,65,-6};// 100
        int[] arr4 = new int[]{1,1,1}; // 2
        int[] arr5 = new int[]{0,0,0,0,0,0,0,0,0,0}; // 0
        int rtA = solution.subarraySum(arr5, 0);
        System.out.println(rtA);
    }
    class Solution {
        public int subarraySum(int[] nums, int k) {
            int len = nums.length;
            HashMap<Integer, Integer> map = new HashMap<>();
            int sum = 0;
            int count = 0;

            for (int i = 0; i < len; i++) {
                sum += nums[i];
                int d = sum - k;
                if(sum == k) count ++;
                if(map.containsKey(d)) count += map.get(d);
                map.put(sum, map.getOrDefault(sum,0) + 1);
            }

            return count;
        }
    }
}
