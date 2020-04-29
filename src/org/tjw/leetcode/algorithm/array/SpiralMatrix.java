package org.tjw.leetcode.algorithm.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a matrix of m x n elements (m rows, n columns),
 * return all elements of the matrix in spiral order.
 *
 * Example 1:
 *
 * Input:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * Output: [1,2,3,6,9,8,7,4,5]
 * Example 2:
 *
 * Input:
 * [
 *   [1, 2, 3, 4],
 *   [5, 6, 7, 8],
 *   [9,10,11,12]
 * ]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class SpiralMatrix {
    public static void main(String[] args) {
        Solution solution = new SpiralMatrix().new Solution();

        int[][] arr5 = new int[][]{
                {1},{2},{3}};
        int[][] arr2 = new int[][]{
                {1,2,3}};
        int[][] arr3 = new int[][]{
                {1,2,3},
                {5,6,7},
                {9,10,11}};
        int[][] arr4 = new int[][]{
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12}};
        int[][] arr = new int[][]{
                {1,2,3},
                {5,6,7},
                {9,10,11},
                {9,10,11}};
        List<Integer> intList = solution.spiralOrder(arr);
        StringBuilder builder = new StringBuilder();
        for(int i = 0;i < intList.size();i ++) {
            builder.append(intList.get(i)).append(",");
        }
        System.out.println(builder);
    }

    class Solution {

        public List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> orderList = new ArrayList<Integer>();
            if(matrix.length == 0 || matrix[0].length == 0) return orderList;
            int top = 0;
            int bottom = matrix.length - 1;
            int left = 0;
            int right = matrix[0].length - 1;

            int x = 0;
            int y = 0;
            int xT = -1;
            int yT = -1;

            int ff = 0;
            for(;;ff ++) {
                if(ff % 2 == 0) {
                    if(x < left || x > right) break;
                    xT -= (xT << 1);
                    for(;x >= left && x <= right;) {
                        orderList.add(matrix[y][x]);
                        x += xT;
                    }
                    if(xT > 0) {
                        right --;
                        x --;
                        y ++;
                    } else {
                        left ++;
                        x ++;
                        y --;
                    }
                } else {
                    if(y < top || y > bottom) break;
                    yT -= (yT << 1);
                    for(;y >= top && y <= bottom;) {
                        orderList.add(matrix[y][x]);
                        y += yT;
                    }
                    if(yT > 0) {
                        top ++;
                        y --;
                        x --;
                    } else {
                        bottom --;
                        y ++;
                        x ++;
                    }
                }
            }
            return orderList;
        }
    }
}
