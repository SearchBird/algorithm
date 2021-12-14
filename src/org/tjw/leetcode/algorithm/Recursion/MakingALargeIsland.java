package org.tjw.leetcode.algorithm.Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MakingALargeIsland {

    public static void main(String[] args) {
        Solution s = new MakingALargeIsland().new Solution();
        int[][] grid = new int[][] {
                {0,0,0,0,0,0,0},
                {0,1,1,1,1,0,0},
                {0,1,0,0,1,0,0},
                {1,0,1,0,1,0,0},
                {0,1,0,0,1,0,0},
                {0,1,0,0,1,0,0},
                {0,1,1,1,1,0,0}
        };
        System.out.println(s.largestIsland(grid));
    }

    class Solution {

        private int max = Integer.MIN_VALUE, index = 0;
        private List<Integer> cache = new ArrayList<Integer>();

        public int largestIsland(int[][] grid) {
            if(grid.length == 0 || grid[0].length == 0) return 0;
            int x = grid.length, y = grid[0].length;
            int[][] board = new int[x][y];
            for(int i = 0; i < x; i ++) Arrays.fill(board[i], -1);
            boolean[][] visited = new boolean[x][y];
            for(int i = 0; i < x; i ++)
                for(int j = 0; j < y; j ++)
                    if(!visited[i][j] && grid[i][j] == 1) {
                        int tmp = count(grid, i, j, visited, 0, grid.length, grid[0].length, board);
                        max = Math.max(max, tmp);
                        cache.add(tmp);
                        index ++;
                        visited[i][j] = true;
                    }

            for(int i = 0; i < x; i ++)
                for(int j = 0; j < y; j ++)
                    if(!visited[i][j]) {
                        max = Math.max(max, search(i, j, grid, board));
                        visited[i][j] = true;
                    }

            return max;
        }

        public int search(int i, int j, int[][] grid, int[][] board) {
            int val = 1, idx = -1;
            int arr[] = new int[4];
            Arrays.fill(arr, -1);

            if(isValid2(grid, i + 1, j, grid.length, grid[0].length, board, arr)) {
                val += cache.get(board[i + 1][j]);
                arr[++ idx] = board[i + 1][j];
            }
            if(isValid2(grid, i - 1, j, grid.length, grid[0].length, board, arr)) {
                val += cache.get(board[i - 1][j]);
                arr[++ idx] = board[i - 1][j];
            }
            if(isValid2(grid, i, j + 1, grid.length, grid[0].length, board, arr)) {
                val += cache.get(board[i][j + 1]);
                arr[++ idx] = board[i][j + 1];
            }
            if(isValid2(grid, i, j - 1, grid.length, grid[0].length, board, arr)) {
                val += cache.get(board[i][j - 1]);
                arr[++ idx] = board[i][j - 1];
            }

            return val;
        }

        public int count(int[][] grid, int x, int y, boolean[][] visited, int val, int xlen, int ylen, int[][] board) {
            val ++;
            board[x][y] = index;
            visited[x][y] = true;
            val = count2(grid, x + 1, y, visited, val, xlen, ylen, board);
            val = count2(grid, x - 1, y, visited, val, xlen, ylen, board);
            val = count2(grid, x, y + 1, visited, val, xlen, ylen, board);
            val = count2(grid, x, y - 1, visited, val, xlen, ylen, board);
            return val;
        }

        public int count2(int[][] grid, int x, int y, boolean[][] visited, int val, int xlen, int ylen, int[][] board) {
            if(isValid(grid, x, y, visited, xlen, ylen))
                val = count(grid, x, y, visited, val, xlen, ylen, board);
            return val;
        }

        public boolean isValid(int[][] grid, int x, int y, boolean[][] visited, int xlen, int ylen) {
            return x >= 0 && x < xlen
                    && y >= 0 && y < ylen
                    && !visited[x][y]
                    && grid[x][y] == 1;
        }

        public boolean isValid2(int[][] grid, int x, int y, int xlen, int ylen, int[][] board, int[] arr) {
            if(x >= 0 && x < xlen
            && y >= 0 && y < ylen
            && grid[x][y] == 1) {
                int tmp = board[x][y];
                for(int i = 0; i < 4; i ++)
                    if(arr[i] == tmp) return false;
                return true;
            }
            return false;
        }
    }
}
