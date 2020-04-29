package org.tjw.leetcode.algorithm.changlle30;

public class ContiguousArray {

    public static void main(String[] args) {
        Solution solution = new ContiguousArray().new Solution();
        // 问题1 出现错误答案
        int[] int01 = new int[]{0,0,1,1,0,1,0};
        int[] int02 = new int[]{0,1};
        int results = solution.findMaxLength(int01);
            System.out.print(results);
    }

    class Solution {

        int[] maps = new int[]{-1,1};

        public int findMaxLength(int[] nums) {
            if(nums == null ||nums.length < 2) return 0;

            int len = nums.length;
            int[] bigmap = new int[(len << 1) + 1];
            bigmap[len] = -1;
            int sum = 0;
            int index = 0;
            int result = 0;

            for(int i = 0;i < len; i ++) {
                sum += maps[nums[i]];
                index = sum + len;
                if(bigmap[index] != 0) {
                    result = Math.max(result, (i + 1 - (index == len ? 0 : bigmap[index])));
                } else {
                    bigmap[index] = i + 1;
                }
            }

            return result;
        }

        // 超时算法
        public int findMaxLength2(int[] nums) {
            if(nums == null ||nums.length < 2) return 0;

            int[] indexs = new int[nums.length];
            int[] results = new int[nums.length];
            int temp = 0;
            int len = nums.length;

            for(int i = 0;i < len;i ++) {
                temp = nums[i];
                nums[i] = 1;

                for(int j = 0;j <= i;j ++) {
                    indexs[j] ++;
                    nums[j] += maps[temp];
                    if(nums[j] == 1) results[j] = indexs[j];
                }
            }

            int max = 0;
            for(int i = results.length;i -- > 0;) {
                if(results[i] > max) max = results[i];
            }

            return max;
        }
    }

}
