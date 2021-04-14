package org.tjw.leetcode.algorithm.changlle30;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MinimumEffortPath {



    class Solution {
        public int minimumEffortPath(int[][] heights) {
            int rowLength = heights.length;
            int colLength = rowLength == 0 ? 0 : heights[0].length;

            int[][] efforts = new int[rowLength][colLength];
            for (int[] row : efforts) Arrays.fill (row, Integer.MAX_VALUE);
            efforts[0][0] = 0;
            // 优先队列其实用了最小堆, 所以可以放心搞二叉树
            PriorityQueue<int[]> minHeap = new PriorityQueue <>((a, b) -> a[2] - b[2]);
            minHeap.offer (new int[] {0, 0, 0});

            int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

            while (!minHeap.isEmpty ()) {
                int[] mat = minHeap.poll ();
                if (mat[0] == rowLength - 1 && mat[1] == colLength - 1) {
                    return mat[2];
                }

                for (int[] direction : directions) {
                    int row = mat[0] + direction[0];
                    int col = mat[1] + direction[1];

                    if (row < 0 || row == rowLength || col < 0 || col == colLength) {
                        continue;
                    }

                    int effort = Math.max (mat[2], Math.abs (heights[row][col] - heights[mat[0]][mat[1]]));

                    if (effort < efforts[row][col]) {
                        efforts[row][col] = effort;
                        minHeap.offer (new int[] {row, col, effort});
                    }
                }
            }

            return -1;
        }
    }
}
