package org.tjw.leetcode.algorithm.changlle30;

public class UniquePathII {

    public static void main(String[] args) {
        Solution s = new UniquePathII().new Solution();
        System.out.println(s.uniquePathsWithObstacles(new int[][]{{1, 0}}));
        System.out.println(s.uniquePathsWithObstacles(new int[][]{{0}}));
        System.out.println(s.uniquePathsWithObstacles(new int[][]{{1},{0}}));
        System.out.println(s.uniquePathsWithObstacles(new int[][]{{0},{1}}));
        System.out.println(s.uniquePathsWithObstacles(new int[][]{{0,0,0},{1,1,0}}));
    }

    class Solution {
        public int uniquePathsWithObstacles(int[][] ob) {
            int x = ob.length;
            int y = ob[0].length;
            boolean block = ob[0][0] == 1;
            for(int i = 0;i < x;i ++)
                if(ob[i][0] != 1) ob[i][0] = 1;
                else
                    for(;i < x;i ++) ob[i][0] = 0;
            for(int i = 1;i < y;i ++)
                if(!block && ob[0][i] != 1) ob[0][i] = 1;
                else
                    for(;i < y;i ++) ob[0][i] = 0;

            for(int i = 1;i < x;i ++)
                for(int j = 1;j < y;j ++)
                    ob[i][j] = ob[i][j] != 1 ? ob[i - 1][j] + ob[i][j - 1] : 0;
            return ob[x - 1][y - 1];
        }
    }
}
