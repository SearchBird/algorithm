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
    // head 封装 n 个元素，第 n 个元素代表 第 n 个节点 头部边 在 edges 的下标
    int[] head;
    // 进行(边 -> 价值)动态规划
    long[][] dp;
    // 封装边，用于存放边的数组，其中链表关系由edge.next指向的，关于edges的index下标维护
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

    // 封边，然后添加链表，类似于jdk1.7 hashmap头插法
    public void addEdge(int prev, int to) {
        // 将当前边指向下一条边，即指向当前head存放的索引
        edges[++ eid].next = head[prev];
        // 设置指向节点值
        edges[eid].to = to;
        // 将head当前索引指向edges存放的当前边索引
        head[prev] = eid;
    }

    // 传入当前节点 val，前一个节点 pval
    public void dp(int val,int pval) {
        sz[val] = 1;
        // 都需要弄 1，因为是乘法，但是其他没遍历到的地方就不需要弄成 1
        dp[val][0] = 1;
        dp[val][1] = 1;

        // 拿到当前节点head下的 边 edges索引，因为是一个链表结构，开始从头遍历，edges[i].next为链表下一条 边
        for(int i = head[val];i != 0;i = edges[i].next) {
            int toval = edges[i].to;
            // 不遍历指向前一个节点的 边
            if(toval == pval) continue;
            dp(toval, val);

            // 将上个节点大小拍进当前节点大小，相当于联通了，当前节点成了连通块
            sz[val] += sz[toval];

            // 进入背包问题，先遍历当前节点连通块大小
            for(int j = sz[val]; j >= 1;j --) {
                // 遍历子树根结点所在联通块大小
                for(int k = Math.min(j, sz[val] - sz[toval]);k >= Math.max(1, j - sz[toval]);k --) {
                    dp[val][j] = Math.max(dp[val][j], dp[val][k] * dp[toval][j-k]);
                }
            }
        }

        for(int i = 1; i <= sz[val]; ++i) {
            dp[val][0] = Math.max(dp[val][0], dp[val][i] * i);
        }
    }

    class Edge {
        // 链表结构，使用数组，next代表数组edges的下标，指向edge的下一条边
        int next;
        // to是存放，从当前节点指向另一个节点的值
        int to;
    }
}
