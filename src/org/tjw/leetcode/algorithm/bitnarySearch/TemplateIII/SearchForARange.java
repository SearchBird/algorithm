package org.tjw.leetcode.algorithm.bitnarySearch.TemplateIII;

/**
 * Given an array of integers nums sorted in ascending order,
 * find the starting and ending position of a given target value.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * If the target is not found in the array, return [-1, -1].
 *
 * Example 1:
 *
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 *
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 */
public class SearchForARange {

    public static void main(String[] args) {
        int[] aa = searchRange(new int[]{1}, 1);
        int[] cc = searchRange(new int[]{2,2}, 1);
        int[] bb = searchRange(new int[]{1,2,3,3,3,3,4,5,9}, 3);
        System.out.println(aa[0]);
        System.out.println(aa[1]);
        System.out.println(bb[0]);
        System.out.println(bb[1]);
    }

    public static int[] searchRange(int[] nums, int target) {
        if(nums.length == 0) return new int[]{-1, -1};

        int left = 0;
        int right = nums.length - 1;

        int bf = -1;
        // 这里也不需要完全夹逼
        while(right > left) {
            bf = (right + left) >> 1;

            int bn = nums[bf];
            if(bn == target) right = bf;
            else if(bn > target) right = bf - 1;
            else left = bf + 1;
        }

        if(nums[left] == target) bf = left;
        else if(right != -1 && nums[right] == target) bf = right;
        else bf = -1;

        int e = -1;
        if(bf != -1) {
            left = bf;
            right = nums.length - 1;
            // 要注意这里是 left + 1, 间隔多 1, 因为 循环条件
            // left = e 可能导致死循环
            while(right > left + 1) {
                e = (right + left) >> 1;

                int en = nums[e];
                if(en == target) left = e;
                else right = e - 1;
            }

            if(nums[right] == target) e = right;
            else if(nums[left] == target) e = left;
        }


        return new int[]{bf, e};
    }
}
