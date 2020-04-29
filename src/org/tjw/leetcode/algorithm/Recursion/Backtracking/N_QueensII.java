package org.tjw.leetcode.algorithm.Recursion.Backtracking;

/**
 * The n-queens puzzle is the problem of placing n queens
 * on an n×n chessboard such that no two queens attack each other.
 *
 *
 *
 * Given an integer n, return the number of distinct solutions
 * to the n-queens puzzle.
 *
 * Example:
 *
 * Input: 4
 * Output: 2
 * Explanation: There are two distinct solutions to the
 * 4-queens puzzle as shown below.
 * [
 *  [".Q..",  // Solution 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // Solution 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 */
public class N_QueensII {

    public static void main(String[] args) {
        Solution solution = new N_QueensII().new Solution();
        System.out.println(solution.totalNQueens(15));
    }

    class Solution {
        private int num;
        private int xTop = 0;
        private int total = 0;
        public int totalNQueens(int n) {
            if(n == 0) return 0;
            num = n;
            int[] xArr = new int[n];
            for(int a = 0;a < n; a ++) {
                xTop = 0;
                xArr[0] = a;
                test(1, xArr);
            }
            return total;
        }

        public void test(int index, int[] xArr) {
            if(index < num) {
                int b = 0;
                A:while(b < num) {
                    for(int a = 0; a <= xTop;a ++) {
                        if(b == xArr[a] || b == xArr[a] + index - a || b == xArr[a] - index + a) {
                            b ++;
                            continue A;
                        }
                    }
                    xArr[++ xTop] = b;
                    test(++ index, xArr);
                    b ++;
                    index --;
                    xTop --;
                }
                return;
            }
            total ++;
        }
    }
}
