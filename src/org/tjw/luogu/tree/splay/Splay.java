package org.tjw.luogu.tree.splay;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Splay {
    private static int BIN = (int)Math.pow(2,10);

    int val[] = new int[BIN];
    // 多了个father，因为需要溯源
    int father[] = new int[BIN];
    int num[] = new int[BIN];
    int size[] = new int[BIN];
    int child[][] = new int[BIN][2];

    int total;
    int root;

    public void pushup(int r) {
        size[r] = size[child[r][0]] + size[child[r][1]] + num[r];
    }

    public int get(int r) {
        return r == child[father[r]][1] ? 1 : 0;
    }

    public void clear(int r) {
        child[r][0] = child[r][1] = father[r] = val[r] = size[r] = num[r] = 0;
    }

    public void rotate(int r) {
        int y = father[r];
        int z = father[y];
        int chk = get(r);

        // 完成 r y 旋转，多了点，要修改 father
        child[y][chk] = child[r][chk ^ 1];
        father[child[r][chk ^ 1]] = y;
        child[r][chk ^ 1] = y;
        father[y] = r;

        // 修改 r z 关系
        father[r] = z;
        if(z != 0) child[z][y == child[z][1] ? 1 : 0] = r;
        // 重新统计
        pushup(r);
        pushup(y);
    }

    public void splay(int r) {
        int f;
        while((f = father[r]) != 0) {
            if(father[f] != 0) rotate(get(r) == get(f) ? f : r);
            rotate(r);
        }
        root = r;
    }

    public void insert(int v) {
        if(root == 0) {
            val[++ total] = v;
            num[total] ++;
            root = total;
            pushup(total);
            return;
        }
        int cnr = root, fa = 0;
        while(true) {
            if(val[cnr] == v) {
                num[root] ++;
                pushup(cnr);
                pushup(fa);
                splay(cnr);
                break;
            }
            fa = cnr;
            cnr = child[cnr][val[cnr] < v ? 1 : 0];
            if(cnr == 0) {
                val[++ total] = v;
                num[total] ++;
                father[total] = fa;
                child[fa][val[fa] < v ? 1 : 0] = total;
                pushup(total);
                pushup(fa);
                splay(total);
                break;
            }
        }
    }

    public int rank(int v) {
        int res = 0;
        int r = root;
        while(true) {
            if(v < val[r])
            {
                r = child[r][0];
            }
            else
            {
                res += size[child[r][0]];
                if(v == val[r])
                {
                    splay(r);
                    return res + 1;
                }
                res += num[r];
                r = child[r][1];
            }
        }
    }

    public int find(int n) {
        int r = root;
        while(true) {
            if(child[r][0] != 0 && n <= size[child[r][0]])
                r = child[r][0];
            else {
                n -= size[child[r][0]] + num[r];
                if(n <= 0) {
                    splay(r);
                    return val[r];
                }
                r = child[r][1];
            }
        }
    }

    public int pre(int v) {
        rank(v);
        int n = child[root][0];
        while(child[n][1] != 0) n = child[n][1];
        splay(n);
        return val[n];
    }

    public int nxt(int v) {
        rank(v);
        int n = child[root][1];
        while(child[n][0] != 0) n = child[n][0];
        splay(n);
        return val[n];
    }

    public void delete(int v) {
        rank(v);
        if(num[root] > 1) {
            num[root] --;
            pushup(root);
        } else if(child[root][0] == 0 && child[root][1] == 0) {
            clear(root);
            root = 0;
        } else if(child[root][0] == 0) {
            int r = root;
            root = child[root][1];
            father[root] = 0;
            clear(r);
        } else if(child[root][1] == 0) {
            int r = root;
            root = child[root][0];
            father[root] = 0;
            clear(r);
        } else {
            int r = root;
            int p = pre(val[r]);
            splay(p);
            father[child[r][1]] = p;
            child[p][1] = child[r][1];
            clear(r);
            pushup(root);
        }
    }

    public static void main(String[] args) {
        Splay tree = new Splay();
        Scanner sc = new Scanner(System.in);
        int n = 99;
        while (-- n > 0) {
            int opt = sc.nextInt();
            int v = sc.nextInt();
            if (opt == 1)
                tree.insert(v);
            else if (opt == 2)
                tree.delete(v);
            else if (opt == 3)
                System.out.println(tree.rank(v));
            else if (opt == 4)
                System.out.println(tree.find(v));
            else if (opt == 5)
                System.out.println(tree.pre(v));
            else
                System.out.println(tree.nxt(v));
        }
    }
}
