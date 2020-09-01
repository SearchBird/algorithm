package org.tjw.leetcode.algorithm.bitnarySearch.other;

import java.util.Arrays;
import java.util.HashMap;

public class FindRightInterval {

    public static void main(String[] args) {
        Solution s = new FindRightInterval().new Solution();

        int[] res = s.findRightInterval(new int[][]{{3,4},{2,3},{1,2}});
        for(int i : res) System.out.print(i + " ");
        System.out.println();

    }

    class Solution {
        public int[] findRightInterval(int[][] intervals) {
            int len = intervals.length, indexSite = len - 1; int[] res = new int[len], index = new int[len];
            // cache
            HashMap<Integer, Integer> cache = new HashMap<Integer, Integer>();
            for(int i = 0;i < len;i ++) {
                cache.put(intervals[i][0], i);
                index[i] = intervals[i][0];
            }
            Arrays.sort(index);

            A:for(int i = 0;i < len;i ++) {
                int temp = intervals[i][1];
                int left = 0, right = indexSite;
                while(right > left) { // 用二分法
                    int mid = left + right >> 1;
                    if(index[mid] < temp) left = mid + 1;
                    else if(index[mid] > temp) right = mid - 1;
                    else {
                        res[i] = cache.get(index[mid]);
                        continue A;
                    }
                }
                if(index[right] >= temp) res[i] = cache.get(index[right]);
                else if(right + 1 < len) res[i] = cache.get(index[right + 1]);
                else  res[i] = -1;
            }

            return res;
        }
    }
}
