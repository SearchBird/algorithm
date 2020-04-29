package org.tjw.leetcode.algorithm.arr101;

public class MergeSortedArray {

    public static void main(String[] args) {
        Solution solution = new MergeSortedArray().new Solution();

        int[] a = new int[]{2,0};
        int[] b = new int[]{1};

        solution.merge(a,1,b,1);

        for(int i : a)
        System.out.print(i + ",");
    }

    class Solution {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            if(m > 0 && n > 0) {

                // 注意长度
                int total = n + m - 1;
                n = n - 1;
                m = m - 1;
                while(m >= 0 || n >= 0) {
                    // 要注意n >= 0，不然下标越界
                    if(m < 0 || n >= 0 && nums1[m] < nums2[n]) {
                        nums1[total --] = nums2[n --];
                    } else {
                        nums1[total --] = nums1[m --];
                    }
                }
            } else if(n > 0) {
                while(n -- > 0) {
                    nums1[n] = nums2[n];
                }
            }
        }
    }
}
