package org.tjw.leetcode.algorithm.changlle30;

import java.util.*;

public class FourIntSum {

    public static void main(String[] args) {
        Solution s = new FourIntSum().new Solution();

        System.out.println(s.fourSum(new int[]{-3,-1,0,2,4,5}, 0));
    }

    private static int[] arr = new int[4];
    private static StringBuilder builder = new StringBuilder();
    class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            if(nums == null || nums.length < 4) return Collections.EMPTY_LIST;
            Arrays.sort(nums);
            int len = nums.length, top = 0;
            int[][] twoSumArr = new int[len * len][3];
            for(int i = 0; i < len; i ++) {
                for(int j = i + 1; j < len; j ++) {
                    twoSumArr[top][0] = nums[i] + nums[j];
                    twoSumArr[top][1] = i;
                    twoSumArr[top ++][2] = j;
                }
            }

            List<List<Integer>> res = new ArrayList<List<Integer>>();
            Map<Integer, List<Integer>> cacheListMap = new HashMap<>();
            Set<String> cacheData = new HashSet<>();
            for(int i = 0; i < top; i ++) {
                if(cacheListMap.containsKey(twoSumArr[i][0])) {
                    List<Integer> list = cacheListMap.get(twoSumArr[i][0]);
                    for(Integer index : list) {
                        if(twoSumArr[index][1] != twoSumArr[i][1]
                                && twoSumArr[index][2] != twoSumArr[i][1]
                                && twoSumArr[index][1] != twoSumArr[i][2]
                                && twoSumArr[index][2] != twoSumArr[i][2]) {
                            arr[0] = nums[twoSumArr[index][1]];
                            arr[1] = nums[twoSumArr[index][2]];
                            arr[2] = nums[twoSumArr[i][1]];
                            arr[3] = nums[twoSumArr[i][2]];
                            Arrays.sort(arr);
                            for (int arrI : arr) builder.append(arrI);
                            String str = builder.toString();
                            builder.setLength(0);
                            if (!cacheData.contains(str)) {
                                cacheData.add(str);
                                List<Integer> resList = new ArrayList<Integer>();
                                for (int arrI : arr) resList.add(arrI);
                                res.add(resList);
                            }
                        }
                    }
                }
                List<Integer> mapList = null;
                if(cacheListMap.containsKey(target - twoSumArr[i][0])) mapList = cacheListMap.get(target - twoSumArr[i][0]);
                else mapList = new ArrayList<>();
                mapList.add(i);
                cacheListMap.put(target - twoSumArr[i][0], mapList);
            }
            return res;
        }
    }
}
