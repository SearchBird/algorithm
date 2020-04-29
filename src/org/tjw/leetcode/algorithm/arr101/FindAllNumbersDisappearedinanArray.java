package org.tjw.leetcode.algorithm.arr101;

import java.util.ArrayList;
import java.util.List;

public class  FindAllNumbersDisappearedinanArray {
    public static void main(String[] args) {
        Solution solution = new FindAllNumbersDisappearedinanArray().new Solution();

        int[] arr = new int[]{
                1,1};
        System.out.print(solution.findDisappearedNumbers(arr));
    }

    class Solution {
        public List<Integer> findDisappearedNumbers(int[] nums) {
            List<Integer> list = new ArrayList<Integer>();
            int dd[] = new int[nums.length + 1];

            for(int i = nums.length;i -- > 0;) {
                dd[nums[i]] ++;
            }
            for(int i = nums.length + 1;i -- > 1;) {
                if(dd[i] == 0) list.add(i);
            }

            return list;
        }
    }
}
