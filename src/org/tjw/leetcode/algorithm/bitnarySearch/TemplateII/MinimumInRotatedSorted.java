package org.tjw.leetcode.algorithm.bitnarySearch.TemplateII;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown
 * to you beforehand.
 *
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 *
 * Find the minimum element.
 *
 * You may assume no duplicate exists in the array.
 *
 * Example 1:
 *
 * Input: [3,4,5,1,2]
 * Output: 1
 * Example 2:
 *
 * Input: [4,5,6,7,0,1,2]
 * Output: 0
 */
public class MinimumInRotatedSorted {

    public static void main(String[] args) {
        Solution solution = new MinimumInRotatedSorted().new Solution();
        System.out.println(solution.findMin(new int[]{1,2}));
        System.out.println(solution.findMin(new int[]{5,1,2,3,4}));
    }

    class Solution {
        // 反过来找唯一峰值，因为找最小值，意味着存在一个峰值，使他两边的值都小于他
        public int findMin(int[] nums) {
            int left = 0;
            int right = nums.length - 1;
            int length = nums.length;

            // 这里排除了所有有序的
            if(nums[length - 1] > nums[left]) return nums[left];

            int mid = -1;
            while(right >= left) {
                mid = (left + right) >> 1;

                int bound = 0;
                if(mid - 1 < 0) bound = 1;
                else if(mid + 1 >= length) bound = 2;

                if(bound == 1) return nums[length - 1];
                else if(bound == 2) return nums[0];

                boolean leftBigger = nums[mid - 1] > nums[mid];
                boolean rightBigger = nums[mid] < nums[mid + 1];

                if(leftBigger) return nums[mid];
                else if(!leftBigger && !rightBigger) return nums[mid + 1];
                // 主要是这里 right = mid 不用 -1
                else if(nums[mid] < nums[left] && nums[mid] < nums[right]) right = mid;
                else left = mid + 1;
            }

            return mid;
        }
    }
}


