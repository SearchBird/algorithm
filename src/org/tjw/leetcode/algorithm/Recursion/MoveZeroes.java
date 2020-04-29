package org.tjw.leetcode.algorithm.Recursion;

public class MoveZeroes {
    public static void main(String[] args) {
        Solution solution = new MoveZeroes().new Solution();
        int[] arr = new int[]{0,1,0,3,12};
        solution.moveZeroes(arr);

        for(int i : arr) {
            System.out.print(i + " ");
        }

    }

    class Solution {
        int[] copyarr;
        int copyi;

        public void moveZeroes(int[] nums) {
            if(nums == null || nums.length == 0) return;
            copyarr = new int[nums.length];
            recurtion(nums,nums.length - 1);
            for(int i = copyarr.length;i -- > 0;) {
                nums[i] = copyarr[i];
            }

            for(int i : copyarr) {
                System.out.print(i + " ");
            }
        }

        public void recurtion(int[] nums, int index) {
            if(index != 0) recurtion(nums,index - 1);
            if(nums[index] != 0) copyarr[copyi ++] = nums[index];
        }
    }
}
