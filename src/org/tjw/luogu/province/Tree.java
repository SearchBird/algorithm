package org.tjw.luogu.province;

import java.util.ArrayList;
import java.util.List;

public class Tree {
    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.cutTree(4,new int[][]{{1,2},{2,3},{3,4}});

    }


    public void cutTree(int nodes, int[][] links) {

        int[][] dp = new int[links.length + 1][links.length + 1];
        for(int i = dp.length - 1;i -- > 0;) {
            for(int j = dp.length - 1;j -- > 0;) {
                dp[i][j] = getSum(nodes,links,i);
            }
        }
    }

    private int getSum(int nodes,int[][] links, int index) {
        int[][] map = new int[nodes][nodes];
        for(int i = 0;i < links.length;i ++) {
            if(i != index) {
                int[] link = links[i];
                map[link[0]][link[1]] = 1;
            }
        }
        return 0;
    }

}
