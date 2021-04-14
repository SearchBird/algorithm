package org.tjw.leetcode.algorithm.array;

public class ArithmeticSlices {

    public static void main(String[] args) {
        Solution s = new ArithmeticSlices().new Solution();
        System.out.println(s.numberOfArithmeticSlices(new int[]{1,2,3,4}));
        System.out.println(s.numberOfArithmeticSlices(new int[]{1, 3, 5, 7, 9}));
        System.out.println(s.numberOfArithmeticSlices(new int[]{1, 1, 2, 5, 7}));
        System.out.println(s.numberOfArithmeticSlices(new int[]{1,2,3,8,9,10}));
    }

    class Solution {
        public int numberOfArithmeticSlices(int[] A) {
            if(A.length < 3) return 0;
            int len = A.length, count = 0, temp = 2, dis = A[1] - A[0];
            boolean flag;
            for(int i = 2;i < len;) {
                flag = true;
                while(i < len && A[i] - A[i - 1] == dis) {
                    i ++; temp ++; flag = false;
                }
                if(!flag) count += (temp - 1) * (temp - 2) >> 1;
                if(i < len) {
                    dis = A[i] - A[i - 1]; temp = 2;
                }
                i ++;
            }
            return count;
        }
    }
}
