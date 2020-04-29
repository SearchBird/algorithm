package org.tjw.leetcode.algorithm.bitnarySearch.TemplateIII;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a sorted array, two integers k and x,
 * find the k closest elements to x in the array.
 * The result should also be sorted in ascending order.
 * If there is a tie, the smaller elements are always preferred.
 *
 * Example 1:
 * Input: [1,2,3,4,5], k=4, x=3
 * Output: [1,2,3,4]
 * Example 2:
 * Input: [1,2,3,4,5], k=4, x=-1
 * Output: [1,2,3,4]
 * Note:
 * The value k is positive and will always be smaller than the length of the sorted array.
 * Length of the given array is positive and will not exceed 10^4
 * Absolute value of elements in the array and x will not exceed 10^4
 */
public class FindKClosestElements {

    public static void main(String[] args) {
        System.out.println(findClosestElements(new int[]{1,2,3,4,5}, 4, 3));
        System.out.println(findClosestElements(new int[]{1,1,10,10,10,10}, 1, 9));
        System.out.println(findClosestElements(new int[]{1}, 1, 1));
        System.out.println(findClosestElements(new int[]{1,2}, 1, 1));
        System.out.println(findClosestElements(new int[]{0,0,1,2,3,3,4,7,7,8}, 3, 5));
        System.out.println(findClosestElements(new int[]{0,0,0,1,3,5,6,7,8,8}, 2, 2));
    }

    public static List<Integer> findClosestElements(int[] arr, int k, int x) {

        int left = 0;
        int right = arr.length - 1;
        int capacity = (int)(k / 0.6);

        if(x < arr[0]) return pickup(left, k - 1, capacity, arr);
        else if (x > arr[arr.length - 1]) return pickup(right - k + 1, right, capacity, arr);
        else {
            int site;
            int length = arr.length;
            while(right > left + 1) {
                site = (right + left) >> 1;
                int t = arr[site];
                if(t == x) break;
                else if(t < x) left = site;
                else right = site;
            }

            // 一定要留意 = 这个边界
            if(arr[right] - x >= x - arr[left]) right = left;
            else left = right;
            for(int i = k - 1;i > 0;i --) {
                int leftD = isBound(left - 1, length) ? Integer.MAX_VALUE : (x - arr[left - 1]);
                int rightD = isBound(right + 1, length) ? Integer.MAX_VALUE : (arr[right + 1] - x);

                // 一定要留意 = 这个边界
                if(leftD <= rightD) left -- ;
                else right ++;
            }

        }

        return pickup(left, right, capacity, arr);
    }

    public static boolean isBound(int index, int length) {
        return index < 0 || index >=  length;
    }

    // 不用扩容
    public static List<Integer> pickup(int begin, int end, int c, int[] arr) {
        List<Integer> list = new ArrayList<>(c);
        for(int i = begin;i <= end;i ++) {
            list.add(arr[i]);
        }
        return list;
    }
}
