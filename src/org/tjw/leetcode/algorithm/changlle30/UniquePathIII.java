package org.tjw.leetcode.algorithm.changlle30;

public class UniquePathIII {

    public static void main(String[] args) {
        Solution s = new UniquePathIII().new Solution();
        System.out.println(s.uniquePathsIII(new int[][]{{1,0,0,0},{0,0,0,0},{0,0,2,-1}}));
        System.out.println(s.uniquePathsIII(new int[][]{{0,0,0,0,0,0,2,0,0,0},
                                                        {0,0,0,0,0,0,0,0,1,0}}));
    }

    private static int x = 0;
    private static int y = 0;
    private static int count = 1;
    class Solution {

        public int uniquePathsIII(int[][] grid) {
            count = 1;
            x = grid.length;
            y = grid[0].length;
            int bi = 0, bj = 0;
            A:for(; bi < x; bi ++)
                for (bj = 0; bj < y; bj++)
                    if (grid[bi][bj] == 1) break A;

            for(int i = 0;i < x;i ++)
                for(int j = 0;j < y;j ++) if(grid[i][j] == 0) count ++;

            return recurtion(bi, bj, grid, 0);
        }

        public int recurtion(int bi, int bj, int[][] grid, int c) {
            if(!(bi < 0 || bi == x || bj < 0 || bj == y)) {
                if(grid[bi][bj] == 0 || grid[bi][bj] == 1) {
                    grid[bi][bj] = 5;
                    int cc = c + 1;
                    int r1 = recurtion(bi - 1, bj, grid, cc);
                    int r2 = recurtion(bi + 1, bj, grid, cc);
                    int r3 = recurtion(bi, bj - 1, grid, cc);
                    int r4 = recurtion(bi, bj + 1, grid, cc);
                    grid[bi][bj] = 0;
                    return r1 + r2 + r3 + r4;
                } else if(grid[bi][bj] == 2 && c == count) return 1;
            }
            return 0;
        }
    }
}
