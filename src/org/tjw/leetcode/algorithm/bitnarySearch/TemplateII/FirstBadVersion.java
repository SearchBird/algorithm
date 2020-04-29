package org.tjw.leetcode.algorithm.bitnarySearch.TemplateII;


/**
 * You are a product manager and currently leading a team to develop a
 * new product. Unfortunately, the latest version of your product fails
 * the quality check. Since each version is developed based on the previous
 * version, all the versions after a bad version are also bad.
 *
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the
 * first bad one, which causes all the following ones to be bad.
 *
 * You are given an API bool isBadVersion(version) which will return
 * whether version is bad. Implement a function to find the first bad version.
 * You should minimize the number of calls to the API.
 *
 * Example:
 *
 * Given n = 5, and version = 4 is the first bad version.
 *
 * call isBadVersion(3) -> false
 * call isBadVersion(5) -> true
 * call isBadVersion(4) -> true
 *
 * Then 4 is the first bad version.
 */
public class FirstBadVersion {

    private static int[] version = new int[]{4,3,1,2,3,5,-1,-1,-1,-1,-1};// index = 6

    public static void main(String[] args) {
        Solution solution = new FirstBadVersion().new Solution();
        System.out.println(solution.firstBadVersion(version.length - 1));
    }

    class Solution {
        public int firstBadVersion(int n) {
            int begin = 0;
            int last = n;

            while(begin <= last) {
                // 为了避免int数据溢出
                long index = ((long)begin + (long)last) >> 1;
                if(isBadVersion((int)index) && isBadVersion(begin))  return begin;
                if(isBadVersion((int)index)) last = (int)index;
                else begin = (int)index + 1;
            }
            return -1;
        }
    }

    private boolean isBadVersion(int index) {
        return version[index] == -1;
    }
}
