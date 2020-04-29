package org.tjw.leetcode.algorithm.arr101;

public class ValidMountainArray {
    public static void main(String[] args) {
        Solution solution = new ValidMountainArray().new Solution();

        int[] arr = new int[]{
                0,3,2,1};
        System.out.print(solution.validMountainArray(arr));
    }

    class Solution {
        public boolean validMountainArray(int[] A) {
            if(A == null || A.length < 3) return false;

            boolean up = true;
            int temp = A[A.length - 1];
            for(int i = A.length - 1;i -- > 0;) {
                int d = A[i] - temp;
                if((d > 0 && !up) || (d < 0 && i == A.length - 2)
                        || d == 0) return false;
                else if(d < 0 && up) up = false;
                else if(i == 0 && up) return false;
                temp = A[i];
            }
            return true;
        }
    }
}
