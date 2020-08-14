package org.tjw.leetcode.algorithm.changlle30;

public class RottingOranges {

    public static void main(String[] args) {
        Solution solution = new RottingOranges().new Solution();
        // 问题1 出现错误答案
        int[][] int01 = new int[][]{{2,1,1},{1,1,0},{0,1,1}};
        int results = solution.orangesRotting(int01);
        System.out.print(results);
    }

    class Solution {
        public int orangesRotting(int[][] grid) {
            int x = grid.length;
            int y = grid[0].length;
            // 创建暂存
            int rotted[][] = new int[x][y];
            int minutes = 0;
            // 每一代是否有腐烂
            boolean rotting = true;

            while(rotting) {
                rotting = false;
                for(int i = 0;i < x;i ++) {
                    for(int j = 0;j < y;j ++) {
                        if(rotted[i][j] != 2)
                            rotted[i][j] = grid[i][j];
                        if(grid[i][j] == 2) {
                            if(i + 1 < x && grid[i + 1][j] == 1) {
                                rotted[i + 1][j] = 2;rotting = true;
                            }
                            if(i - 1 >= 0 && grid[i - 1][j] == 1) {
                                rotted[i - 1][j] = 2;rotting = true;
                            }
                            if(j + 1 < y && grid[i][j + 1] == 1) {
                                rotted[i][j + 1] = 2;rotting = true;
                            }
                            if(j - 1 >= 0 && grid[i][j - 1] == 1) {
                                rotted[i][j - 1] = 2;rotting = true;
                            }
                        }
                    }
                }
                if(rotting) {
                    minutes ++;
                    int temp[][] = grid;
                    grid = rotted;
                    rotted = temp;
                }
            }

            // 最后遍历是否还有好橙子
            for(int i = 0;i < x;i ++)
                for(int j = 0;j < y;j ++)
                    if(grid[i][j] == 1) return -1;
            return minutes;
        }
    }
}
