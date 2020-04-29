package org.tjw.leetcode.algorithm.arr101;



public class DuplicateZeros {
    public static void main(String[] args) {
        Solution solution = new DuplicateZeros().new Solution();

        int[] arr = new int[]{
                0,0,0,0,0};
        solution.duplicateZeros(arr);
        for(int i = 0;i < arr.length;i ++) {
            System.out.print(arr[i] + ",");
        }
    }

    class Solution {
        public void duplicateZeros(int[] arr) {
            int len = arr.length;
            int begin = -1;
            int end = len;

            for(int i = 0;i < len && i < end;i ++) {
                if(arr[i] == 0 && i != end ) {
                    if(begin == -1) begin = i;
                    -- end;
                }
                if(i == end) end ++;
            }

            if(begin != -1 && end - begin - 1!= len - end)
                for(int i = end;i -- > begin;) {
                    if(arr[i] == 0) {
                        arr[-- len] = 0;
                        arr[-- len] = 0;
                    }
                    else arr[-- len] = arr[i];
                }
        }
    }
}
