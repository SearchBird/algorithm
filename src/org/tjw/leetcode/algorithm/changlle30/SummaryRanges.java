package org.tjw.leetcode.algorithm.changlle30;

import java.util.ArrayList;
import java.util.List;

public class SummaryRanges {

    public static void main(String[] args) {
        Solution solution = new SummaryRanges().new Solution();

        int nums[] = new int[]{1,2,33};
        System.out.println(solution.summaryRanges(nums));

    }

    private final static String line = "->";
    private final static StringBuilder builder = new StringBuilder();
    class Solution {
        public List<String> summaryRanges(int[] nums) {
            List<String> res = new ArrayList<String>();
            int len = nums.length, begin = 0, end = 0;
            for(int i = 0;i < len;i ++) {
                if(nums[i] - nums[end] < 2) {
                    end = i;
                } else {
                    if(begin == end) res.add(String.valueOf(nums[end]));
                    else {
                        res.add(builder.append(nums[begin]).append(line).append(nums[end]).toString());
                        builder.setLength(0);
                    }
                    end = begin = i;
                }
            }
            if(nums.length != 0) {
                if(begin == end) res.add(String.valueOf(nums[end]));
                else {
                    res.add(builder.append(nums[begin]).append(line).append(nums[end]).toString());
                    builder.setLength(0);
                }
            }
            return res;
        }
    }
}
