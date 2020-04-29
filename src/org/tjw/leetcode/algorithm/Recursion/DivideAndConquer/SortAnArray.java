package org.tjw.leetcode.algorithm.Recursion.DivideAndConquer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortAnArray {

    public static void main(String[] args) {
        Solution solution = new SortAnArray().new Solution();
        List<Integer> list = solution.sortArray(new int[]{2,3,1,1,4,5});
        List<Integer> list2 = solution.sortArray(new int[]{2});
        List<Integer> list3 = solution.sortArray(new int[]{3,2});
        List<Integer> list4 = solution.sortArray(new int[]{1,3,2});
        List<Integer> list5 = solution.sortArray(new int[]{3,2,2});
        System.out.println(list);
        System.out.println(list2);
        System.out.println(list3);
        System.out.println(list4);
        System.out.println(list5);
        System.out.println(3%2);
    }

    class Solution {

        private int[] arr;

        public List<Integer> sortArray(int[] nums) {
            arr = nums;
            sort(0, arr.length - 1);
            List<Integer> resultList = new ArrayList<>(arr.length);
            for (Integer s : arr) {
                resultList.add(s);
            }
            return resultList;
        }

        public int[] sort(int left, int right) {
            int[] leftArr = null;
            int[] rightArr = null;
            if(right > left) {
                int mid = (right + left) >> 1;
                int index = right - left;
                int[] sortArr = new int[index + 1];
                leftArr = sort(left, mid);
                rightArr = sort(mid + 1, right);

                int leftE = mid;
                int rightE = right;
                int rightB = mid + 1;
                while(leftE >= left && rightE >= rightB) {
                    if(arr[leftE] <= arr[rightE]) {
                        sortArr[index --] = arr[rightE --];
                    } else {
                        sortArr[index --] = arr[leftE --];
                    }
                }

                if(leftE >= left) {
                    while(leftE >= left) {
                        sortArr[index --] = arr[leftE --];
                    }
                } else if(rightE >= rightB) {
                    while(rightE >= rightB) {
                        sortArr[index --] = arr[rightE --];
                    }
                }

                int len = sortArr.length;
                while(len -- > 0) {
                    arr[left + len] = sortArr[len];
                }
            }
            return new int[]{left, right};
        }
    }
}
