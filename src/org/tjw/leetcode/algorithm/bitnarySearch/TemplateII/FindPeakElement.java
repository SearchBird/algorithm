package org.tjw.leetcode.algorithm.bitnarySearch.TemplateII;

/**
 * A peak element is an element that is greater than its neighbors.
 *
 * Given an input array nums, where nums[i] ≠ nums[i+1],
 * find a peak element and return its index.
 *
 * The array may contain multiple peaks, in that case
 * return the index to any one of the peaks is fine.
 *
 * You may imagine that nums[-1] = nums[n] = -∞.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 * Example 2:
 *
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 1 or 5
 * Explanation: Your function can return either index number 1 where the peak element is 2,
 *              or index number 5 where the peak element is 6.
 */
public class FindPeakElement {

    public static void main(String[] args) {
        Solution solution = new FindPeakElement().new Solution();
        System.out.println(solution.findPeakElement(new int[]{1,2,1}));
        System.out.println(solution.findPeakElement(new int[]{4,3,1,2,3,5,8,7,6,5,4,3,2,1}));
    }

    class Solution {
        public String findPeakElement(int[] nums) {

            int left = 0;
            int right = nums.length - 1;

            while(left <= right){
                int mid = (left + right) >> 1;
                int midV = nums[mid];

                // 每次进来就比较左大还是右大
                boolean leftBigger = checkBound(mid - 1,nums) ? midV < nums[mid - 1] : false;
                boolean rightBigger = checkBound(mid + 1,nums) ? midV < nums[mid + 1] : false;

                // 比较左右都大就返回
                if(!leftBigger && !rightBigger) return mid + ":" + nums[mid];
                else if(rightBigger) left = mid + 1 ;
                else right = mid - 1;
            }
            return "";
        }

        public boolean checkBound(int val, int[] nums){
            if(val < 0 || val >= nums.length) return false;
            return true;
        }
    }
}
