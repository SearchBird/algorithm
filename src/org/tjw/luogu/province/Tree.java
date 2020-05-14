package org.tjw.luogu.province;

import java.math.BigDecimal;

public class Tree {

    public static void main(String[] args) {
        int n1 = 5;
        int[][] nn1 = new int[][]{{1,2},{2, 3},{3, 4},{4, 5}};
        int n2 = 4;
        int[][] nn2 = new int[][]{{1, 2},{1, 3},{1, 4}};
        int n3 = 8;
        int[][] nn3 = new int[][]{{1 ,2},{1 ,3},{2 ,4},{2 ,5},{3 ,6},{3 ,7},{6 ,8}};

        Tree tree1 = new Tree(n1);
        tree1.doit(nn1,tree1, n1);
        System.out.println(tree1.dp[1][0]); // 6

        Tree tree2 = new Tree(n2);
        tree2.doit(nn2,tree2, n2);
        System.out.println(tree2.dp[1][0]); // 4

        Tree tree3 = new Tree(n3);
        tree3.doit(nn3,tree3, n3);
        System.out.println(tree3.dp[1][0]); // 18
    }

    // 一维用于计算背包问题放置的连通块大小
    int[] sz;
    // head 封装 n 个元素，代表每个节点头部 edge 的下标
    int[] head;
    // 进行(边 -> 价值)动态规划
    long[][] dp;
    // 封装边
    Edge[] edges;
    // edge索引
    int eid;

    public Tree(int n) {
        sz = new int[n + 5];
        head = new int[n + 5];
        dp = new long[n + 5][n + 5];
        edges = new Edge[(n << 1) + 5];
    }

    public void doit(int[][] nn,Tree tree,int n) {
        for(int i = 0;i < edges.length;i ++) edges[i] = new Edge();

        // 进行链表封装edge，头插法
        for(int i = 0; i < n - 1; i ++) {
            tree.addEdge(nn[i][0],  nn[i][1]);
            tree.addEdge( nn[i][1], nn[i][0]);
        }
        tree.dp(1, 0);
    }

    // 封边，然后添加链表，类似于jdk1.7头插法
    public void addEdge(int u, int v) {
        edges[++ eid].next = head[u];
        edges[eid].to = v;
        head[u] = eid;
    }

    public void dp(int u,int fa) {
        sz[u] = 1;
        dp[u][0] = 1;
        dp[u][1] = 1;

        // 从头部遍历链表
        for(int i = head[u];i != 0;i = edges[i].next) {
            int v = edges[i].to;
            if(v == fa) continue;
            dp(v,u);

            sz[u] += sz[v];
            for(int j = sz[u]; j >= 1; --j) { //枚举i所在的联通块大小
                for(int k = Math.min(j, sz[u]-sz[v]); k >= Math.max(1, j-sz[v]); --k) { //枚举子树根结点所在联通块大小
                    dp[u][j] = Math.max(dp[u][j], dp[u][k] * dp[v][j-k]);
                }
            }
        }

        for(int i = 1; i <= sz[u]; ++i) {
            dp[u][0] = Math.max(dp[u][0], dp[u][i] * i);
        }
    }

    class Edge {
        // 链表结构，使用数组，next代表数组edges的下标，指向edge的下一条边
        int next;
        // to是指向节点
        int to;
    }
}
