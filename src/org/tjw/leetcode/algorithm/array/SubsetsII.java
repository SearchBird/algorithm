package org.tjw.leetcode.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsII {

    public static void main(String[] args) {
        Solution s = new SubsetsII().new Solution();
        System.out.println(s.subsetsWithDup(new int[]{1,2,2}));
    }

    class Solution {
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            Arrays.sort(nums);
            List<List<Integer>> res = new ArrayList<>();
            for(int i = 0, len = nums.length; i <= len; i ++)
                recurtion(nums, new int[nums.length], 0, i, 0, new boolean[nums.length], res);
            return res;
        }

        public void recurtion(int[] nums, int[] tmpArr, int idx, int size, int begin, boolean[] visited, List<List<Integer>> res) {
            if(size == idx) {
                boolean flag = true;
                Arrays.sort(tmpArr,0, size);
                A:for(List<Integer> l : res) {
                    if(l.size() == size)
                        for(int i = 0; i < size; i ++) {
                            if(l.get(i) != tmpArr[i]) {
                                continue A;
                            }
                        }
                    else continue  A;
                    flag = false;
                }
                if(flag) {
                    List<Integer> list = new ArrayList<>();
                    for(int j = 0; j < size; j ++) list.add(tmpArr[j]);
                    res.add(list);
                }
                return;
            }
            for(int i = begin, len = nums.length; i < len; i ++) {
                if(!visited[i]) {
                    visited[i] = true;
                    tmpArr[idx] = nums[i];
                    recurtion(nums, tmpArr, idx + 1, size, i + 1, visited, res);
                    visited[i] = false;
                }
            }
        }
    }
}
