package org.tjw.leetcode.algorithm.array;


public class SelfCrossing {
    public static void main(String[] args) {
        Solution solution = new SelfCrossing().new Solution();


        int[] x03 = new int[]{1,1,2,2,3,3,4,4,10,4,4,3,3,2,2,1,1};
        System.out.print(solution.isSelfCrossing(x03));

        int[] x02 = new int[]{3,3,4,2,2};
        System.out.print(solution.isSelfCrossing(x02));
    }

    /**
     * 注意坐标轴规定
     *        X2 (-)
     *           |
     *           |
     * Y2(-) - - O - -> Y1 (+)
     *           |
     *           |
     *        X1 (+)
     */
    class Solution {
        public boolean isSelfCrossing(int[] x) {
            int len = x.length;
            // 第一象限
            int X1 = 0;
            int Y1 = 0;
            // 第四象限
            int X2 = 0;
            int Y2 = 0;
            for(int i = len;i -- > 0;)
                if(i % 2 == 0 && i % 4 != 0) X2 = Math.max(x[i],X2);
                else if(i % 2 == 0) X1 = Math.max(x[i],X1);
                else if((i - 1) % 4 != 0) Y1 = Math.max(x[i],Y1);
                else Y2 = Math.max(x[i],Y2);

            // 原点
            int X = X1;
            int Y = Y2;
            boolean[][] visited = new boolean[X1 + X2 + 1][Y1 + Y2 + 1];
            visited[X][Y] = true;

            // visited 表发现标记是 true ，就相交，否则标记为 true
            for(int i = 0;i < len;i ++) {
                if(i % 2 == 0 && i % 4 != 0) {
                    for(int j = X + 1;j <= X + x[i];j ++) {
                        if(visited[j][Y]) return true;
                        else visited[j][Y] = true;
                    }
                    X += x[i];
                }
                else if(i % 2 == 0) {
                    for(int j = X;j -- > X - x[i];) {
                        if(visited[j][Y]) return true;
                        else visited[j][Y] = true;
                    }
                    X -= x[i];
                }
                else if((i - 1) % 4 != 0) {
                    for(int j = Y + 1;j <= Y + x[i];j ++) {
                        if(visited[X][j]) return true;
                        else visited[X][j] = true;
                    }
                    Y += x[i];
                }
                else {
                    for(int j = Y;j -- > Y - x[i];) {
                        if(visited[X][j]) return true;
                        else visited[X][j] = true;
                    }
                    Y -= x[i];
                }
            }

            return false;
        }
    }
}
