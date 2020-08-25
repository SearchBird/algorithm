package org.tjw.luogu.tree.treap;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Treap_Obj {
    private static int BIN = (int)Math.pow(2,20);

    class TreapNode {
        // 容量记录，该节点下面存放节点数量
        int size;
        // 元素本身值
        int value;
        // 随机值
        int random;
        // 权值一样的节点重复数
        int num;
        // 存子节点
        TreapNode left;
        TreapNode right;
    }
    private static boolean LEFT = true;
    private static boolean RIGHT = false;
    int sum = 0;
    TreapNode ROOT = null;

    // 更新节点数
    public void pushup(TreapNode r) {
        // 左右子节点数 + 自己重复数
        int left = r.left == null ? 0 : r.left.size;
        int right = r.right == null ? 0 : r.right.size;
        r.size = left + right + r.num;
    }

    // true - 左旋转 false - 右旋转
    // 左旋 是 逆时针旋转，以根节点为中心，将右边逆时针转上去
    /**
     * 左旋
     *    3              3    6           6
     *   / \            / \  / \         / \
     *  2   6     =>   2   4   7  =>    3  7
     *     / \                         / \
     *    4   7                       2  4
     * 右旋反过来
     *      6            6    3           3
     *     / \          / \  / \         / \
     *    3   7   =>   7   4    2  =>   2  6
     *   / \                              / \
     *  2  4                             4  7
     */
    public TreapNode rotate(TreapNode r, boolean dir) {
        // 暂存 旋转点
        TreapNode op = dir ? r.right : r.left;

        // 将根连接旋转点枝的对立枝，改为旋转点下面同向枝
        if(dir) r.right = op.left;
        else r.left = op.right;
        // 旋转点的同向枝，变为根节点
        if(dir) op.left = r;
        else op.right = r;
        // 重新统计 p 节点的节点数
        pushup(r);
        // 重新统计 op 节点节点数
        pushup(op);
        return op;
    }

    // 索引 r 根节点，插入 x值 节点
    // 每次 insert 需要统计一次 size
    public TreapNode insert(TreapNode r, int x) {
        if(r == null) {
            r = new TreapNode();
            ++ sum;
            r.size = r.num = 1;
            r.value = x;
            r.random = (int)(Math.random() * 10000);
            return r;
        }
        // 发现重复
        if(r.value == x) {
            r.num ++;
            r.size ++;
            return r;
        }

        TreapNode d;
        if(x > r.value) {
            r.right = insert(r.right, x);
            d = r.right;
        } else {
            r.left = insert(r.left, x);
            d = r.left;
        }

        if(r.random < d.random) r = rotate(r,x > r.value);
        pushup(r);
        return r;
    }

    public TreapNode delete(TreapNode r, int x) {
        if(r == null) return r;
        // 二分递归
        if(x < r.value) r.left = delete(r.left, x);
        else if(x > r.value) r.right = delete(r.right, x);
        // 命中
        else {
            boolean lf = r.left == null;
            boolean rf = r.right == null;
            if(lf && rf) {
                // 已经末尾节点
                r.num --;
                if(r.num == 0) return null;
            } else if(lf) {
                r = rotate(r,LEFT);
                r.left = delete(r.left, x);
            } else if(rf) {
                r = rotate(r,RIGHT);
                r.right = delete(r.right, x);
            } else {
                boolean dir = r.left.random < r.right.random;
                r = rotate(r,dir);
                if(dir) r.left = delete(r.left, x);
                else r.right = delete(r.right, x);
            }
        }

        // 如果是删除，就需要把所有节点数统计减去 1
        -- r.size;
        return r;
    }

    public int find(TreapNode r, int x) {
        if(r == null) return 0;
        int left = r.left == null ? 0 : r.left.size;
        if(left >= x) {
            return find(r.left, x);
        }
        else if(left + r.num < x) {
            return find(r.right,x - r.num - left);
        }
        return r.value;
    }

    /**
     * 前驱，小于x最大数
     * @param r
     * @param x
     * @return
     */
    public int pre(TreapNode r, int x) {
        if(r == null) return Integer.MIN_VALUE;
        if(r.value >= x) return pre(r.left,x);
        else return Math.max(r.value, pre(r.right, x));
    }

    public int suc(TreapNode r, int x) {
        if(r == null) return Integer.MAX_VALUE;
        if(r.value <= x) return suc(r.right, x);
        else return Math.min(suc(r.left, x), r.value);
    }

    public int rank(TreapNode r, int x) {
        if(r == null) return 0;
        else if(r.value == x) return r.left == null ? 1 : r.left.size + 1;
        else if(r.value < x) return  r.num + rank(r.right,x) + (r.left == null ? 0 : r.left.size);
        else return rank(r.left, x);
    }

    private static int st = 0;
    private static int cur = 1;
    private static int pre = 0;
    private static TreapNode[][] stack = new TreapNode[2][10000];
    public String printTree() {
        StringBuilder builder = new StringBuilder();
        List<List<String>> res = new ArrayList<List<String>>();
        if(ROOT != null) stack[cur][st ++] = ROOT;

        while(st != 0) {
            cur ^= 1;
            pre ^= 1;
            int top = 0;
            List<String> levlist = new ArrayList<>();
            boolean contains = false;
            for(int i = 0;i < st ;i ++) {
                TreapNode node = stack[pre][i];
                if(node == null) {
                    stack[cur][top ++] = null;
                    stack[cur][top ++] = null;
                    levlist.add("-------");
                } else {
                    levlist.add(builder.append(String.valueOf(node.value))
                            .append("(").append(node.size).append(")").append("-").append(node.num).toString());
                    builder.setLength(0);
                    stack[cur][top ++] = node.left;
                    stack[cur][top ++] = node.right;
                    contains = true;
                }
            }
            if(contains) {
                st = top;
                res.add(levlist);
            } else st = 0;
        }

        int lev = res.size();
        for(int i = 0;i < lev;i ++) {
            List<String> list = res.get(i);
            int size = list.size();
            for(int j = size;j -- > 1;) {
                for(int x = (int)Math.pow(2, lev - i) - 1;x -- > 0;) {
                    list.add(j,"         ");
                }
            }
            for(int j = (int)Math.pow(2, lev - 1 - i) - 1;j -- > 0;) {
                list.add(0,"         ");
                list.add(list.size(),"         ");
            }
        }

        for(int i = 0;i < lev;i ++) {
            List<String> list = res.get(i);
            int size = list.size();
            for(int j = 0;j < size;j ++) {
                builder.append(list.get(j));
            }
            builder.append("\r\n");
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Treap_Obj treap1 = new Treap_Obj();
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        for(int i = 0;i < num;i ++) {
            int opt,x;
            opt = sc.nextInt();
            x = sc.nextInt();
            switch(opt) {
                case 1 : treap1.ROOT = treap1.insert(treap1.ROOT,x); break;
                case 2 : treap1.ROOT = treap1.delete(treap1.ROOT,x); break;
                case 3 : System.out.println(treap1.rank(treap1.ROOT,x)); break;
                case 4 : System.out.println(treap1.find(treap1.ROOT,x)); break;
                case 5 : System.out.println(treap1.pre(treap1.ROOT,x)); break;
                case 6 : System.out.println(treap1.suc(treap1.ROOT,x));
            }

            //System.out.println(treap1.printTree());
        }
    }
}
