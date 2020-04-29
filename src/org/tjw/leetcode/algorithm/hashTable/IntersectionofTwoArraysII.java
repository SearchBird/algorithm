package org.tjw.leetcode.algorithm.hashTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given two arrays, write a function to compute their intersection.
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2,2]
 * Example 2:
 *
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [4,9]
 * Note:
 *
 * Each element in the result should appear as many times as it shows
 * in both arrays.
 * The result can be in any order.
 * Follow up:
 *
 * What if the given array is already sorted? How would you optimize your algorithm?
 * What if nums1's size is small compared to nums2's size?
 * Which algorithm is better?
 * What if elements of nums2 are stored on disk, and the memory
 * is limited such that you cannot load all elements into the memory at once?
 *
 * 坑爹的题目，我还以为找出一条最长的相交链，结果是只是找重复，而且不用管排序
 */
public class IntersectionofTwoArraysII {

    public static void main(String[] args) {
        Solution solution = new IntersectionofTwoArraysII().new Solution();
        // 问题1 出现错误答案
        int[] int01 = new int[]{4,9,5};
        int[] int02 = new int[]{9,4,9,8,4};

        // 问题2 出现错误答案
        int[] int011 = new int[]{1,2};
        int[] int022 = new int[]{1,1};

        // 问题3 还得要任意整数
        int[] int0111 = new int[]{-11111,2};
        int[] int0222 = new int[]{1,1,2};

        int[] results = solution.intersect(int011, int022);
        for(int aa : results)
            System.out.print(aa + ",");
    }
    class Solution {
        public int[] intersect(int[] nums1, int[] nums2) {
            // 问题2 需要<=不然长度相等没法计算
            int[] shoter = nums1.length <= nums2.length ? nums1 : nums2;
            // 问题1 注意这里反过来
            int[] longer = nums1.length > nums2.length ? nums1 : nums2;
            ArrayList<Integer> inter = new ArrayList<Integer>();
            HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

            for(int i = shoter.length;i -- > 0;) {
                map.put(shoter[i], map.getOrDefault(shoter[i], 0) + 1);
            }

            for(int i = longer.length;i -- > 0;) {
                int ll = longer[i];
                if(map.containsKey(ll)
                && map.get(ll) != 0) {
                    map.put(ll, map.get(ll) - 1);
                    inter.add(ll);
                }
            }

            int[] result = new int[inter.size()];
            for(int i = inter.size();i -- > 0;) {
                result[i] = inter.get(i);
            }
            // 基于单线程下 使用stream转数组效率不行，对大list中进行线程池处理可以使用到提升时间
            // 流式编程
            return inter.stream().mapToInt(Integer::valueOf).toArray();
        }

    }
}
