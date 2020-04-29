package org.tjw.leetcode.algorithm.Recursion.RecurrenceRelation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PascalTriangle {

    public static void main(String[] args) {
        Solution solution = new PascalTriangle().new Solution();
        List<List<Integer>> lists = solution.generate(45);
        for(int i = 0;i < lists.size();i ++) {
            System.out.println(lists.get(i));
        }
    }

    class Solution {
        private int n = 0;
        private int init = 1;
        List<List<Integer>> list = null;

        public List<List<Integer>> generate(int numRows) {
            if(numRows <= 0) return new ArrayList<List<Integer>>();

            n = numRows;
            list = new ArrayList<List<Integer>>(numRows);

            // 第一个自己手动加入
            List<Integer> intList = new ArrayList<Integer>(1);
            intList.add(1);
            list.add(intList);
            // 第一个自己手动加入

            genHelper(intList);
            return list;
        }

        public void genHelper(List<Integer> pre) {
            if(init ++ >= n) return;
            // 比较麻烦的地方就是Integer需要初始化, list里面不能弄进去int
            Integer[] intArr = new Integer[init];
            for (int i = 0; i < intArr.length; i++) {
                intArr[i] = new Integer(0);
            }

            // 这里是计算杨辉三角的核心
            for(int i = 0;i < pre.size();i ++) {
                intArr[i] = intArr[i] + pre.get(i);
                intArr[i + 1] = intArr[i + 1] + pre.get(i);
            }
            List<Integer> intList = Arrays.asList(intArr);
            list.add(intList);
            // 这里是计算杨辉三角的核心

            genHelper(intList);
        }
    }
}
