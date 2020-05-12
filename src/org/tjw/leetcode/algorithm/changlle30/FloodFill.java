package org.tjw.leetcode.algorithm.changlle30;

public class FloodFill {

    public static void main(String[] args) {
        Solution solution = new FloodFill().new Solution();
        // 问题1 出现错误答案
        int[][] int01 = new int[][]{{0,0,0},{0,0,0}};
        int[][] results = solution.floodFill(int01,0,0,2);
        for(int i = 0;i < results.length;i ++) {
            for(int j = 0;j < results[0].length;j ++) {
                System.out.print(results[i][j] + ",");
            }
            System.out.println();
        }
    }

    class Solution {

        public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
            int[][] visited = new int[image.length][image[0].length];

            int[][] queue = new int[image.length * image[0].length][2];
            int begin = 0;
            int end = 0;

            queue[begin] = new int[]{sr,sc};
            int re = image[sr][sc];
            visited[sr][sc] = 1;
            while(begin <= end) {
                int[] site = queue[begin ++];
                int y = site[0];
                int x = site[1];

                if(image[y][x] == re) image[y][x] = newColor;
                if(y + 1 < image.length && visited[y + 1][x] != 1 && image[y + 1][x] == re) {
                    queue[++ end] = new int[]{y + 1, x};
                    visited[y + 1][x] = 1;
                }
                if(y - 1 >= 0 && visited[y - 1][x] != 1 && image[y - 1][x] == re) {
                    queue[++ end] = new int[]{y - 1, x};
                    visited[y - 1][x] = 1;
                }
                if(x + 1 < image[0].length && visited[y][x + 1] != 1 && image[y][x + 1] == re) {
                    queue[++ end] = new int[]{y, x + 1};
                    visited[y][x + 1] = 1;
                }
                if(x - 1 >= 0 && visited[y][x - 1] != 1 && image[y][x - 1] == re) {
                    queue[++ end] = new int[]{y, x - 1};
                    visited[y][x - 1] = 1;
                }
            }
            return image;
        }
    }
}
