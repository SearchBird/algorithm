package org.tjw.luogu.tree.redblack;

import java.util.Scanner;

public class RedBlackTree {
    private static boolean RED = true;
    private static boolean BLC = false;
    static class RBNode {
        boolean color;
        RBNode right,left;
        int val, num, size;
        RBNode(int val) {this.val = val; this.num = this.size = 1; this.color = RED;}
    }

    RBNode ROOT;

    public void pushup(RBNode r) {
        if(r != null) {
            int left = r.left == null ? 0 : r.left.size;
            int right = r.right == null ? 0 : r.right.size;
            r.size = r.num + left + right;
        }
    }

    private void cleanUp(RBNode r) {
        if(r != null) {
            cleanUp(r.left);
            cleanUp(r.right);
            r.left = r.right = null;
        }
    }

    /**
     * 和二叉搜索树一样插入节点, 新节点默认红色
     */
    public RBNode insert(RBNode root, int val) {
        root = insert_inner(root, val);
        root.color = BLC;
        return root;
    }

    public RBNode insert_inner(RBNode root, int val) {
        if(root == null) return new RBNode(val);
        if(root.val == val) {
            root.num ++; root.size ++; return root;
        }
        else if(root.val < val) root.right = insert_inner(root.right, val);
        else root.left = insert_inner(root.left, val);
        return fixUp(root); // 对红黑节点进行平衡
    }

    /**
     * 进行颜色平衡
     */
    public RBNode fixUp(RBNode root) {
        if(root == null) return root;
        // 处理标记
        boolean leftRed = root.left != null
                        && root.left.color == RED;
        boolean leftLeftRed = root.left != null
                            && root.left.left != null
                            && root.left.left.color == RED;
        boolean rightRed = root.right != null
                        && root.right.color == RED;

        // 先判断是否只是右边的红色
        if(rightRed && !leftRed) root = rotate(root, true);
        // 在判断是否连续红色，因为考虑到旋转后的问题
        if(leftRed && leftLeftRed) root = rotate(root, false);
        // 最后判断是否两边红色，因为考虑到上面两次旋转后的问题
        if(leftRed && rightRed) colorFlip(root);
        pushup(root);
        return root;
    }

    /**
     * 反转颜色, 当双边都红色的时候
     */
    public void colorFlip(RBNode root) {
        root.color = RED;
        if(root.left != null) root.left.color = BLC;
        if(root.right != null) root.right.color = BLC;
    }

    /**
     * dir = true (左旋) | flase (右旋)
     */
    public RBNode rotate(RBNode root, boolean dir) {
        RBNode temp;
        if(dir) {
            temp = root.right;
            root.right = temp.left;
            temp.left = root;
        } else {
            temp = root.left;
            root.left = temp.right;
            temp.right = root;
        }
        temp.color = root.color;
        root.color = RED;
        pushup(root); pushup(temp); return temp;
    }

    public RBNode delete(RBNode root, int val) {
        if(root == null) return root;
        // 获取标记
        boolean leftRed = root.left != null
                        && root.left.color == RED;
        boolean leftLeftRed = root.left != null
                            && root.left.left != null
                            && root.left.left.color == RED;
        boolean rightRed = root.right != null
                        && root.right.color == RED;
        boolean rightLeftRed = root.right != null
                            && root.right.left != null
                            && root.right.left.color == RED;

        // 对标记进行处理
        if(root.val > val) {
            if(!leftRed && !leftLeftRed) root = moveRedLeft(root);
            root.left = delete(root.left, val);
        } else {
            if(leftRed) root = rotate(root, false);
            if(root.val == val && root.right == null) {
                root.num --; root.size --;
                if(root.num == 0) return root.left;
                return root;
            }
            if(!rightRed && !rightLeftRed) root = moveRedRight(root);
            if(root.val == val) {
                root.num --;
                if(root.num == 0) {
                    exchange(root, getMin(root.right));
                    root.right = deleteMin(root.right);
                } pushup(root);
                return root;
            } else { // root.val > val
                root.right = delete(root.right, val);
            }
        }
        return fixUp(root);
    }

    private RBNode deleteMin(RBNode r) {
        if (r.left == null) return r.right;

        boolean leftRed = r.left.color == RED;
        boolean leftLeftRed = r.left.left != null && r.left.left.color == RED;
        if (!leftRed && !leftLeftRed) {
            r = moveRedLeft(r);
        }
        r.left = deleteMin(r.left);
        pushup(r);
        return fixUp(r);
    }

    private RBNode getMin(RBNode r) {
        while(r.left != null) r = r.left;
        return r;
    }

    private void exchange(RBNode r, RBNode e) {
        r.val = e.val; r.num = e.num;
        pushup(r);
    }

    public RBNode moveRedLeft(RBNode r) {
        colorFlip(r);
        // 处理标记
        boolean rightLeftRed = r.right != null
                && r.right.left != null
                && r.right.left.color == RED;

        if(rightLeftRed) {
            r.right = rotate(r.right, false);
            r = rotate(r, true);
            colorFlip(r);
        }
        return r;
    }

    public RBNode moveRedRight(RBNode r) {
        colorFlip(r);
        // 处理标记
        boolean leftLeftRed = r.left != null
                && r.left.left != null
                && r.left.left.color == RED;

        if(leftLeftRed) {
            r = rotate(r, false);
            colorFlip(r);
        }
        return r;
    }

    /**
     * 找排名 n
     */
    public int find(RBNode r, int n) {
        if(r == null) return 0;
        int left = r.left == null ? 0 : r.left.size;
        if(left >= n) return find(r.left, n);
        else if(left + r.num < n) return find(r.right, n - left - r.num);
        else return r.val;
    }

    /**
     * 找值 v 排名
     */
    public int rank(RBNode r, int v) {
        if(r == null) return 1;
        else if(r.val > v) return rank(r.left, v);
        else if(r.val < v) return rank(r.right, v) + (r.left == null ? 0 : r.left.size) + r.num;
        else return r.left == null ? 1 : r.left.size + 1;
    }

    /**
     * 前驱 最大的 小值
     */
    public int pre(RBNode r, int v) {
        if(r == null) return Integer.MIN_VALUE;
        else if(r.val >= v) return pre(r.left, v);
        else return Math.max(pre(r.right, v), r.val);
    }

    /**
     * 后继 最小的 大值
     */
    public int sub(RBNode r, int v) {
        if(r == null) return Integer.MAX_VALUE;
        else if(r.val <= v) return sub(r.right, v);
        else return Math.min(sub(r.left, v), r.val);
    }

    private static int cur = 0;
    private static int pre = 1;
    private static int top1 = 0;
    private static String ll = "(";
    private static String rr = ")";
    private static String bb = "    ";
    private static StringBuilder builder = new StringBuilder();
    private static RBNode[][] stack = new RBNode[2][(int)Math.pow(2,12)];
    public String printTree() {
        RBNode r = ROOT;
        if(r != null) stack[cur][top1 ++] = r;
        while(top1 != 0) {
            int top2 = 0;
            for(int i = 0;i < top1;i ++) {
                RBNode temp = stack[cur][i];
                builder.append(temp.val).append(ll).append(temp.color).append(rr).append(bb);
                if(temp.left != null) stack[pre][top2 ++] = temp.left;
                if(temp.right != null) stack[pre][top2 ++] = temp.right;
            }
            builder.append("\r\n");
            cur ^= 1;
            pre ^= 1;
            top1 = top2;
        }
        String res = builder.toString();
        builder.setLength(0);
        return res;
    }

    private static Scanner sc = new Scanner(System.in);
    private static int num;
    public static void main(String[] args) {
        num = sc.nextInt();
        RedBlackTree tree = new RedBlackTree();
        while(num -- > 0) {
            int op = sc.nextInt();
            int val = sc.nextInt();
            switch (op) {
                case 1:
                    tree.ROOT = tree.insert(tree.ROOT, val);
                    //System.out.println(tree.printTree());
                    break;
                case 2:
                    tree.ROOT = tree.delete(tree.ROOT, val);
                    //System.out.println(tree.printTree());
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
                    System.out.println(tree.sub(tree.ROOT, val));
            }
        }
    }
}
