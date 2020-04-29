package org.tjw.leetcode.algorithm.changlle30;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class lkl {

    public static void main(String[] args) {
        lkl l = new lkl();
        BinaryMatrix m = l.new BinaryMatrix();
        Solution solution = l.new Solution();

        System.out.println(solution.leftMostColumnWithOne(m));
    }

      class BinaryMatrix {
          // rows 行 cols 列
          int[][] arr2 = new int[][]{
                  {0,0,0,1},
                  {0,0,1,1},
                  {0,1,1,1}};
          // 往上移动时候 j += 2，如果没匹配过j ++
          int[][] arr = new int[][]{
                  {0,0,0,0,1,1,1,1,1},
                  {0,0,0,0,0,1,1,1,1},
                  {0,0,0,0,1,1,1,1,1},
                  {0,0,0,0,0,0,1,1,1},
                  {0,0,0,0,0,0,0,1,1},
                  {0,0,0,1,1,1,1,1,1},
                  {0,0,0,0,1,1,1,1,1},
                  {0,0,0,0,1,1,1,1,1},
                  {0,0,0,0,0,0,0,0,1}
          };
          // 遇到整行0，要j ++
          int[][] arr4 = new int[][]{
                  {1,1,1,1,1},
                  {0,0,0,1,1},
                  {0,0,1,1,1},
                  {0,0,0,0,1},
                  {0,0,0,0,0}
          };
          // 题意似乎不是说只有一个，有就行
          int[][] arr5  = new int[][]{
                  {0,0,0,0,0,1,1,1,1,1,1,1},
                  {0,0,0,0,0,0,0,1,1,1,1,1},
                  {0,0,0,0,0,0,0,0,1,1,1,1},
                  {0,0,0,0,0,1,1,1,1,1,1,1},
                  {0,0,0,0,0,0,1,1,1,1,1,1},
                  {0,0,0,0,0,1,1,1,1,1,1,1},
                  {0,0,0,0,0,0,0,0,1,1,1,1},
                  {0,0,0,0,0,0,0,1,1,1,1,1},
                  {0,0,0,0,0,0,1,1,1,1,1,1},
                  {0,0,0,0,0,1,1,1,1,1,1,1},
                  {0,0,0,0,0,0,1,1,1,1,1,1},
                  {0,0,0,0,0,0,1,1,1,1,1,1}
          };
          int[][] arr6  = new int[][]{
                  {0,0},{0,0}
          };
          // 遇到整行1，要j 不变
          int[][] arr3 = new int[][]{
                  {0,0},
                  {1,1}};
          public int get(int row, int col) {
              return arr[row][col];
          }
          public List<Integer> dimensions() {
              ArrayList<Integer> r = new ArrayList<>();
              r.add(arr.length);
              r.add(arr[0].length);
              return r;
          }
      }
    class Solution {
        public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
            List<Integer> dim = binaryMatrix.dimensions();
            int rows = dim.get(0);
            int cols = dim.get(1);
            int index = -1;
            boolean tF = true;

            System.out.println(rows + ":" + cols);
            int j = cols;
            for(int i = rows;i -- > 0;) {
                for(;j -- > 0;) {
                    int temp = binaryMatrix.get(i,j);
                    if(temp == 0) {
                        if(tF) j ++;
                        else j += 2;
                        break;
                    } else {
                            index = j;
                            tF = false;
                    }
                }
                tF = true;
            }

            return index;
        }
    }
}
