package org.tjw.leetcode.algorithm.changlle30;

import java.util.HashMap;
import java.util.Map;

public class JumpGameII {

    public static void main(String[] args) {
        Solution s = new JumpGameII().new Solution();

        System.out.println(s.jump(new int[]{0}));
        System.out.println(s.jump(new int[]{2,3,1,1,4}));
        System.out.println(s.jump(new int[]{1,2}));
        System.out.println(s.jump(new int[]{1,2,1,1,1}));
        System.out.println(s.jump(new int[]{2,1}));
        System.out.println(s.jump(new int[]{4,1,1,3,1,1,1}));
    }

    class Solution {
        public int jump(int[] nums) {
            int len = nums.length;
            int[] jump = new int[len];
            jump[len - 1] = len - 1;
            for(int i = len - 1; i -- > 0;) {
                int j = i + 1;
                int temp = nums[i] + i;
                while(j < len && temp > jump[j ++]);
                j --;
                if(temp == jump[j]) jump[i] = jump[j];
                else jump[i] = j;
            }

            int begin = 0, times = 0;
            while(true) {
                if(begin == len - 1) break;
                begin = jump[begin];
                times ++;
            }
            return times;
        }

//        public int recurtion(int[] nums, int len, int end, int index) {
//            if(minCache.containsKey(index)) return minCache.get(index);
//            int min = 1000000;
//            if(index == end) return 1;
//            for(int i = 1; i <= nums[index]; i ++) {
//                if(index + i < len)
//                    min = Math.min(min,recurtion(nums, len, end, index + i));
//                else break;
//            }
//            minCache.put(index, ++ min);
//            return min;
//        }
    }
}
