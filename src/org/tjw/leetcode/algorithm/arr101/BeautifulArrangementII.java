package org.tjw.leetcode.algorithm.arr101;


public class BeautifulArrangementII {

    public static void main(String[] args) {
        Solution s = new BeautifulArrangementII().new Solution();
        int[] res = s.constructArray(40, 39);
        for(int i : res) System.out.print(i + " ");
    }

    class Solution {
        public int[] constructArray(int n, int k) {
            int[] res = new int[n];
            int index = 0, begin = 1, end = n;
            while(end >= begin) {
                if(k > 1) res[index ++] = (k -- & 1) == 0 ? end -- : begin ++;
                else res[index ++] = begin ++;
            }
            return res;
        }
    }
}
