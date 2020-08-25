package org.tjw.luogu.tree.treap;

import java.util.Scanner;

public class Treap_Array {
    private int BIN = 100005, LEFT = 0, RIGHT = 1, index = 0, ROOT = 0;
    private int[] val = new int[BIN], num = new int[BIN],
                  size = new int[BIN], random = new int[BIN];
    private int[][] child = new int[BIN][2];

    public void pushup(int r) {
        size[r] = num[r] + size[child[r][LEFT]] + size[child[r][RIGHT]];
    }

    // true - left, false - right
    public int rotate(int r, boolean dir) {
        int temp;
        if(dir) {
            temp = child[r][RIGHT];
            child[r][RIGHT] = child[temp][LEFT];
            child[temp][LEFT] = r;
        } else {
            temp = child[r][LEFT];
            child[r][LEFT] = child[temp][RIGHT];
            child[temp][RIGHT] = r;
        }
        pushup(r); pushup(temp); return temp;
    }

    public int insert(int r, int v) {
        if(val[r] == 0) { // check val
            r = ++ index;
            val[r] = v; num[r] = size[r] = 1;
            random[r] = (int)(Math.random() * BIN);
        }
        else if(val[r] == v)  ++ num[r];
        else {
            int dir = val[r] > v ? 0 : 1;
            child[r][dir] = insert(child[r][dir], v);
            if(random[r] < random[child[r][dir]]) r = rotate(r, dir != 0);
        }
        pushup(r); return r;
    }

    public int delete(int r, int v) {
        if(val[r] == 0) return r; // check val
        else if(val[r] > v) child[r][LEFT] = delete(child[r][LEFT], v);
        else if(val[r] < v) child[r][RIGHT] = delete(child[r][RIGHT], v);
        else {
            boolean lf = child[r][LEFT] == 0;
            boolean rf = child[r][RIGHT] == 0;
            if (lf && rf) {
                size[r] = -- num[r];
                if(num[r] == 0) { val[r] = 0; return 0; }
            } else if (lf) {
                r = rotate(r, true);
                child[r][LEFT] = delete(child[r][LEFT], v);
            } else if (rf) {
                r = rotate(r, false);
                child[r][RIGHT] = delete(child[r][RIGHT], v);
            } else { // true - left, false - right
                int dir = random[child[r][LEFT]] > random[child[r][RIGHT]] ? 1 : 0;
                r = rotate(r, dir == 0);
                child[r][dir] = delete(child[r][dir], v);
            }
        }
        pushup(r); return r;
    }

    public int find(int r, int n) {
        if(r == 0) return 0;
        int left = size[child[r][LEFT]];
        if(left >= n) return find(child[r][LEFT], n);
        else if(left + num[r] < n) return find(child[r][RIGHT], n - left - num[r]);
        else return val[r];
    }

    public int rank(int r, int v) {
        if(r == 0) return 1;
        else if(val[r] > v) return rank(child[r][LEFT], v);
        else if(val[r] < v) return num[r] + size[child[r][LEFT]] + rank(child[r][RIGHT], v);
        else return 1 + size[child[r][LEFT]];
    }

    public int pre(int r, int v) {
        if(r == 0) return Integer.MIN_VALUE;
        else if(val[r] >= v) return pre(child[r][LEFT], v);
        else return Math.max(val[r],pre(child[r][RIGHT], v));
    }

    public int sub(int r, int v) {
        if(r == 0) return Integer.MAX_VALUE;
        else if(val[r] <= v) return sub(child[r][RIGHT], v);
        else return Math.min(val[r], sub(child[r][LEFT], v));
    }

    public static void main(String[] args) {
        Treap_Array tree = new Treap_Array();
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        while(num -- > 0) {
            int opt = scanner.nextInt();
            int val = scanner.nextInt();
            switch (opt) {
                case 1:
                    tree.ROOT = tree.insert(tree.ROOT, val);
                    break;
                case 2:
                    tree.ROOT = tree.delete(tree.ROOT, val);
                    break;
                case 3:
                    System.out.println(tree.rank(tree.ROOT, val));
                    break;
                case 4:
                    System.out.println(tree.find(tree.ROOT, val));
                    break;
                case 5:
                    System.out.println(tree.pre(tree.ROOT, val));
                    break;
                case 6:
                    System.out.println(tree.sub(tree.ROOT,val));
            }
        }
    }
}