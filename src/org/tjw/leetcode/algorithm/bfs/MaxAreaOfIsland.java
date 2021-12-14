package org.tjw.leetcode.algorithm.bfs;

import org.tjw.leetcode.algorithm.Test;

import java.util.ArrayList;
import java.util.List;

public class MaxAreaOfIsland {

    public static void main(String[] args) {
        Solution s = new MaxAreaOfIsland().new Solution();
        System.out.print(s.maxAreaOfIsland(new int[][]{{0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},{0,0,0,0,0,0,0,0,0,0,1,0,0},{0,0,0,0,0,0,0,1,1,1,0,0,0},{0,0,0,0,0,0,0,1,1,0,0,0,0}}));
    }

    class Solution {
        public int maxAreaOfIsland(int[][] grid) {
            if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
            int x = grid.length, y = grid[0].length, max = 0;
            boolean[][] visited = new boolean[x][y];
            List<Integer> stack = new ArrayList<Integer>();

            for(int i = 0; i < x; i ++)
                for(int j = 0; j < y; j ++) {
                    if (grid[i][j] == 1 && !visited[i][j]) {

                        int area = 0;
                        stack.add(i);
                        stack.add(j);
                        visited[i][j] = true;

                        while (!stack.isEmpty()) {
                            area++;
                            int x0 = stack.remove(0), y0 = stack.remove(0);
                            check(x0, y0 - 1, x, y, visited, grid, stack);
                            check(x0, y0 + 1, x, y, visited, grid, stack);
                            check(x0 - 1, y0, x, y, visited, grid, stack);
                            check(x0 + 1, y0, x, y, visited, grid, stack);
                        }

                        max = Math.max(max, area);
                    }
                    visited[i][j] = true;
                }
            return max;
        }

        public void check(int x,int y,int xlen,int ylen,boolean[][] visited,int[][] grid,List<Integer> stack) {
            if(x < xlen && x >= 0 && y < ylen && y >= 0 && !visited[x][y] && grid[x][y] == 1) {
                stack.add(x); stack.add(y);
                visited[x][y] = true;
            }
        }
    }
}
