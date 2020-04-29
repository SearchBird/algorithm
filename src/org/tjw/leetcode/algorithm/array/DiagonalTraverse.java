package org.tjw.leetcode.algorithm.array;

/**
 * Given a matrix of M x N elements (M rows, N columns),
 * return all elements of the matrix in diagonal order as shown in the below image.
 *
 *
 *
 * Example:
 *
 * Input:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 *
 * Output:  [1,2,4,7,5,3,6,8,9]
 *
 * Explanation:
 */
public class DiagonalTraverse {
    public static void main(String[] args) {
        Solution solution = new DiagonalTraverse().new Solution();

        int[][] arr = new int[][]{
                {1,2,1},
                {1,2,1},
                {1,2,1},
                {1,2,1}};
        int[] rtA = solution.findDiagonalOrder(arr);
        StringBuilder builder = new StringBuilder();
        for(int i = 0;i < rtA.length;i ++) {
            builder.append(rtA[i]).append(",");
        }
        System.out.println(builder);
    }

    class Solution {
        public int[] findDiagonalOrder(int[][] matrix) {
            if(matrix.length == 0 || matrix[0].length == 0) return new int[0];
            int i = 0;
            int x = 0;
            int y = -1;
            int index = 0;
            int[] arr = new int[matrix.length * matrix[0].length];
            for(;;i ++) {
                if(x == matrix[0].length - 1 && y == matrix.length - 1) return arr;
                if(i % 2 == 1) {
                    if(x < matrix[0].length - 1) x ++;
                    else y ++;
                    for(;x >= 0 && y < matrix.length;x --) {
                        arr[index ++] = matrix[y ++][x];
                    }
                    x ++;
                    y --;
                } else {
                    if(y < matrix.length - 1) y ++;
                    else x ++;
                    for(;y >= 0 && x < matrix[0].length;y --) {
                        arr[index ++] = matrix[y][x ++];
                    }
                    y ++;
                    x --;
                }
            }
        }
    }
}
