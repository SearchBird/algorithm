package org.tjw.leetcode.algorithm.changlle30;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PermutationsII {

    public static void main(String[] args) {
        Solution s = new PermutationsII().new Solution();

        int[] arr = new int[]{1,1,2};
        System.out.println(s.permuteUnique(arr));
    }

    class Solution {
        public List<List<Integer>> permuteUnique(int[] nums) {
            return recurtion(nums, new boolean[nums.length], 0, nums.length, new ArrayList<List<Integer>>(), new int[nums.length]);
        }

        public List<List<Integer>> recurtion(int[] nums, boolean[] used, int index, int len, List<List<Integer>> res, int[] temp) {
            if(index == len) {
                List<Integer> list = new ArrayList<Integer>();
                for(int i : temp) list.add(i);
                res.add(list);
                return res;
            }
            HashSet<Integer> set = new HashSet<Integer>();
            for(int i = 0;i < len;i ++) {
                if(!used[i] && !set.contains(nums[i])) {
                    used[i] = true;
                    set.add(nums[i]);
                    temp[index] = nums[i];
                    recurtion(nums, used, index + 1, len, res, temp);
                    used[i] = false;
                }
            }
            return res;
        }
    }
}
