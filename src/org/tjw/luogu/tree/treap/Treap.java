package org.tjw.luogu.tree.treap;

import java.util.Scanner;

public class Treap {
    private static int BIN = (int)Math.pow(2,20);
    private static int LEFT = 0;
    private static int RIGHT = 1;

    int[] size = new int[BIN];
    int[] num = new int[BIN];
    int[] rank = new int[BIN];
    int[] value = new int[BIN];
    int[][] child = new int[BIN][2];

    int arrindex = 0;
    int ROOT = 0;

    public void lookup(int root) {
        size[root] = size[child[root][0]] + size[child[root][1]] + num[root];
    }

    /**
     * 0 - 左旋
     * 1 - 右旋
     */
    public int rotate(int root, int dir) {
        int op = dir ^ 1;
        int opc = child[root][op];

        child[root][op] = child[opc][dir];
        child[opc][dir] = root;

        lookup(opc);
        lookup(root);

        return opc;
    }

    /**
     *
     */
    public int insert(int root, int v) {
        if(root == 0) {
            root = ++ arrindex;
            size[root] ++;
            num[root] ++;
            rank[root] = (int)(Math.random() * 100000);
            value[root] = v;
        } else if(value[root] == v) {
            size[root] ++;
            num[root] ++;
        } else {
            int d = value[root] > v ? 0 : 1;
            child[root][d] = insert(child[root][d], v);
            if(rank[root] < rank[child[root][d]]) root = rotate(root, d ^ 1);
            lookup(root);
        }
        return root;
    }

    public int delete(int root, int v) {
        if(root == 0) return root;

        // 删除后，子节点重新赋值
        if(value[root] < v) child[root][1] = delete(child[root][1], v);
        else if(value[root] > v) child[root][0] = delete(child[root][0], v);
        else {
            boolean lf = child[root][0] == 0;
            boolean rf = child[root][0] == 0;
            if(lf && rf) {
                size[root] --;num[root] --;
                if(num[root] == 0) {
                    value[root] = 0;
                    return 0;
                }
            } else if(lf) {
                root = rotate(root, LEFT);
                child[root][0] = delete(child[root][0], v);
            } else if(rf) {
                root = rotate(root, RIGHT);
                child[root][1] = delete(child[root][1], v);
            } else {
                int dir = rank[child[root][0]] < rank[child[root][1]] ? LEFT : RIGHT;
                root = rotate(root, dir);
                child[root][dir] = delete(child[root][dir], dir);
            }
        }

        size[root] --;
        return root;
    }

    /**
     * 找以 root 为根节点， site 排名的 元素
     * @param root
     * @param site
     * @return
     */
    public int find(int root, int site) {
        // 排除排名不存在
        if(root <= 0) return 0;
        int left = size[child[root][0]];
        if(left >= site) {
            // 左边 <= 排名，就跑左边去
            return find(child[root][0], site);
        }
        // 发现左边 + 根节点 < 排名
        else if(left + num[root] < site) {
            // 找右边就需要把左边根节点排名去掉
            return find(child[root][1],site - num[root] - left);
        }
        else {
            // 相当于 left + num[root] == site
            return value[root];
        }
    }

    // root 根节点, 找值为 v 前驱值, root > max(v*)
    public int pre(int root, int v) {
        if(root <= 0) return Integer.MIN_VALUE;
        else if(value[root] >= v) return pre(child[root][0], v);
        else return Math.max(value[root], pre(child[root][1], v));
    }

    //  root 根节点, 找值为 v 后继值, root < min(v*)
    public int suc(int root, int v) {
        if(root <= 0) return Integer.MAX_VALUE;
        else if(value[root] <= v) return suc(child[root][1], v);
        else return Math.min(value[root], suc(child[root][0], v));
    }

    // 找某个值排名
    public int rank(int root, int v) {
        if(root <= 0) return 0;
        else if(value[root] < v) return rank(child[root][1], v) + num[root] + size[child[root][0]];
        else if(value[root] > v) return rank(child[root][0], v);
        else return size[child[root][0]] + 1;
    }


}
