package org.tjw.leetcode.algorithm.changlle30;

public class CountSquareSubmatriceswithAllOnes {
    public static void main(String[] args) {

        Solution solution = new CountSquareSubmatriceswithAllOnes().new Solution();

        int[][] arr = new int[][]{{0,1,1,1},{1,1,1,1},{0,1,1,1}};
        int[][] arr2 = new int[][]{ {0,1,1,1},
                                    {1,1,0,1},
                                    {1,1,1,1},
                                    {1,0,1,0}};
        int[][] arr3 = new int[][]{ {1,1,1,1,1,1,0}, // 6
                                    {1,1,1,1,0,0,1}, // 5
                                    {1,1,1,1,1,1,1}, // 7
                             /*30*/ {1,1,1,1,0,0,1}};// 5

        System.out.println(solution.countSquares(arr));
        System.out.println(solution.countSquares(arr2));
        System.out.println(solution.countSquares(arr3));

    }

class Solution {

    public int countSquares(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int count = 0;
        for (int i = n; i -- > 0;){
            count += matrix[i][0];
        }

        for (int i = m; i -- > 1;){
            count += matrix[0][i];
        }

        for (int i = 1; i < n; ++i){
            for (int j = 1; j < m; ++j){
                if (matrix[i][j] == 1){
                    // 使用min是因为出现0空心的地方就不进行统计
                    matrix[i][j] += Math.min(matrix[i - 1][j], Math.min(matrix[i][j - 1], matrix[i - 1][j - 1]));
                    count += matrix[i][j];
                }
            }
        }

        return count;
    }

        public int countSquares2(int[][] matrix) {
            int count = 0;
            int xlen = matrix.length;
            int ylen = matrix[0].length;
            // 逐个计算联通块
            for(int i = xlen;i -- > 0;) {
                A:for(int j = ylen;j -- > 0;) {
                    // 计算本行联通块, 0的不计算
                    if(matrix[i][j] != 0) {
                        // 将本行前一个联通块加进来
                        matrix[i][j] += j + 1 < ylen ? matrix[i][j + 1] : 0;

                        // 将本行联通块大小k作为边，往下k行的联通块是否符合要求
                        for (int k = 0; k < matrix[i][j]; k++) {
                            if (i + k < xlen) {
                                // 往下k行的联通块是否符合要求
                                for(int l = 0;l <= k;l ++) {
                                    if (k >= matrix[i + l][j]) continue A;
                                }
                                count ++;
                            } else break;
                        }
                    }
                }
            }
            return count;
        }
    }
}

