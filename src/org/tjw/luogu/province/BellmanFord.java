package org.tjw.luogu.province;

public class BellmanFord {

    public static void main(String[] args) {
        BellmanFord bellmanFord = new BellmanFord();

        int[][] test01 = new int[][]{{0,1,10},{0,2,20},{2,4,200},{4,5,10},{1,3,100},{3,4,50}};

        // 0 - 1 - 3 - 4 - 5 total:170 最小
        // 0 - 2 - 4 - 5 total:230
        System.out.println(bellmanFord.func(6,0, 5, test01));
    }

    public int func(int nodenum, int begin, int end,int[][] edges) {
        // 创建一个和节点数量一致的数组
        int[] dis = new int[nodenum];

        // 初始化图，将非开始节点值变为最大
        for(int i = 0;i < nodenum;i ++)
            if(i != begin) dis[i] = Integer.MAX_VALUE;

        // 松弛操作，逐步逼近
        for(int i = 0;i < nodenum;i ++)
            for(int j = 0;j < edges.length;j ++) {
                int[] edge = edges[j];
                dis[edge[1]] = Math.min(dis[edge[1]], dis[edge[0]] + edge[2]);
            }

        return dis[end] == Integer.MAX_VALUE ? -1 : dis[end];
    }
}
