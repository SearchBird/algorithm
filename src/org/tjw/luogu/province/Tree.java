package org.tjw.luogu.province;

import java.math.BigDecimal;

public class Tree {

    int eid;
    int[] sz;
    int[] head;
    long[][] f;
    Edge[] e;

    public Tree(int n) {
        sz = new int[n + 5];
        head = new int[n + 5];
        f = new long[n + 5][n + 5];
        e = new Edge[(n << 1) + 5];
    }

    public void doit(int[][] nn,Tree tree,int n) {
        for(int i = 0;i < e.length;i ++) e[i] = new Edge();

        // 进行边封装
        for(int i = 0; i < n - 1; i ++) {
            tree.addEdge(nn[i][0],  nn[i][1]);
            tree.addEdge( nn[i][1], nn[i][0]);
        }
        tree.dp(1, 0);
    }

    // 根据背包问题，封边
    public void addEdge(int u, int v) {
        e[++ eid].next = head[u];
        e[eid].to = v;
        head[u] = eid;
    }

    public void dp(int u,int fa) {
        sz[u] = 1;
        f[u][0] = 1;
        f[u][1] = 1;

        for(int i = head[u];i != 0;i = e[i].next) {
            int v = e[i].to;
            if(v == fa) continue;
            dp(v,u);

            sz[u] += sz[v];
            for(int j = sz[u]; j >= 1; --j) { //枚举i所在的联通块大小
                for(int k = Math.min(j, sz[u]-sz[v]); k >= Math.max(1, j-sz[v]); --k) { //枚举子树根结点所在联通块大小
                    f[u][j] = Math.max(f[u][j], f[u][k]*f[v][j-k]);
                }
            }
        }

        for(int i = 1; i <= sz[u]; ++i) {
            f[u][0] = Math.max(f[u][0], f[u][i]*i);
        }
    }

    class Edge {
        int next;
        int to;
    }

    public static void main(String[] args) {
        int n1 = 5;
        int[][] nn1 = new int[][]{{1,2},{2, 3},{3, 4},{4, 5}};
        int n2 = 8;
        int[][] nn2 = new int[][]{{1, 2},{1, 3},
                {2, 4},{2, 5},
                {3, 6},{3, 7},{6, 8}};

        Tree tree1 = new Tree(n1);
        tree1.doit(nn1,tree1, n1);
        System.out.println(tree1.f[1][0]);

        Tree tree2 = new Tree(n2);
        tree2.doit(nn2,tree2, n2);
        System.out.println(tree2.f[1][0]);
    }
}
