package org.tjw.leetcode.algorithm.changlle30;

public class SingleElementinaSortedArray {
    public static void main(String[] args) {
        Solution solution = new SingleElementinaSortedArray().new Solution();

        int[] arr = new int[]{1,1,2,3,3,4,4,8,8}; // 2
        int[] arr2 = new int[]{1,1,3,3,4,4,5,8,8}; // 5
        int[] arr3 = new int[]{3,3,7,7,10,11,11}; // 10
        int[] arr4 = new int[]{3,3,4,7,7,11,11}; // 4

        int[] arr5 = new int[]{1,1,2}; // 2
        int[] arr6 = new int[]{1,1,2,2,3}; // 2

        int[] arr8 = new int[]{1,2,2}; // 2
        int[] arr7 = new int[]{1,2,2,3,3}; // 2

        System.out.println(solution.singleNonDuplicate(arr));
        System.out.println(solution.singleNonDuplicate(arr2));
        System.out.println(solution.singleNonDuplicate(arr3));
        System.out.println(solution.singleNonDuplicate(arr4));
        System.out.println(solution.singleNonDuplicate(arr5));
        System.out.println(solution.singleNonDuplicate(arr6));
        System.out.println(solution.singleNonDuplicate(arr7));
        System.out.println(solution.singleNonDuplicate(arr8));
    }
    class Solution {
        public int singleNonDuplicate(int[] nums) {
            if(nums.length < 2 || nums[0] != nums[1]) return nums[0];
            int left = 0;
            int right = nums.length - 1;
            while(right > left) {
                int mid = left + right >> 1;
                boolean odd = mid % 2 != 0;
                if(nums[mid + 1] == nums[mid]) {
                    if(odd) right = mid - 1;
                    else left = mid + 1;
                } else if(nums[mid - 1] == nums[mid]) {
                    if(odd) left = mid + 1;
                    else right = mid - 1;
                } else return nums[mid];
            }
            return nums[left];
        }
    }
}
