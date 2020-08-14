package org.tjw.luogu.tree.avl;

import java.util.Scanner;

public class AVLTree
{
    class AVLNode {
        int val;
        int num;
        int size;
        int height;
        AVLNode left;
        AVLNode right;
        public AVLNode(int val) {this.val = val; this.height = this.size = this.num = 1;}
    }
    AVLNode root;

    private void pushup(AVLNode r) {
        if(r != null) {
            r.size = r.num
                   + (r.left == null ? 0 : r.left.size)
                   + (r.right == null ? 0 : r.right.size);

            int lh = r.left == null ? 0 : r.left.height;
            int rh = r.right == null ? 0 : r.right.height;
            r.height = lh >= rh ? lh + 1 : rh + 1;
        }
    }

    private void clear (AVLNode r) {
        if(r != null) { r.left = null; r.right = null; }
    }

    public AVLNode insert(AVLNode r, int v) {
        if(r == null) return new AVLNode(v);
        else if(r.val == v) ++ r.num;
        else if(r.val > v) r.left = insert(r.left, v);
        else r.right = insert(r.right, v);
        pushup(r);
        return maintain(r); // 先统计，再进行平衡
    }

    public AVLNode delete(AVLNode r, int v) {
        if(r == null) return r;
        else if(r.val < v) r.right = delete(r.right, v);
        else if(r.val > v) r.left = delete(r.left, v);
        else {
            -- r.num;
            if(r.num == 0) {
                boolean ln = r.left == null, rn = r.right == null;
                if(ln && rn) return null;
                else if(ln) { // 返回右边
                    AVLNode res = r.right; clear(r); return res;
                }
                else if(rn) { // 返回左边
                    AVLNode res = r.left; clear(r); return res;
                }
                else {
                    AVLNode leftMax = deleteLeft(r.left);
                    leftMax.left = leftMax.val == r.left.val ? leftMax.left : r.left;
                    leftMax.right = r.right;
                    clear(r); r = leftMax;
                }
            }
        }
        pushup(r);
        return maintain(r); // 先统计，再进行平衡
    }

    public AVLNode deleteLeft(AVLNode r) {
        if(r.right == null) return r;
        else {
            AVLNode right = deleteLeft(r.right);
            if(r.right.val == right.val) r.right = right.left; // 注意最右节点可能有个左节点
            r.size -= right.num;
            return right;
        }
    }

    /**
     * 进行右旋，左边子节点 的
     *
     * 左边 孙子节点 高度 > 右边 孙子节点 高度
     *       o              o
     *      / \   right    / \
     *     o   o   =》    o   o        (平衡了)
     *    / \            /   / \
     *   o   o          o   o   o
     *  /
     * o
     *
     * 左边 孙子节点 高度 < 右边 孙子节点 高度
     *       o              o
     *      / \   right    / \
     *     o   o   =》    o   o        (不行)
     *    / \                / \
     *   o   o              o   o
     *        \              \
     *         o              o
     * 应该：
     *       o                o             o
     *      / \              / \   right   / \
     *     o   o            o   o    =》  o   o   (可以了)
     *    / \      left    / \           /   / \
     *   o   o      =》   o   o         o   o   o
     *        \         /
     *         o       o
     */
    public AVLNode maintain(AVLNode r) {
        int lh = r.left == null ? 0 : r.left.height;
        int rh = r.right == null ? 0 : r.right.height;
        if(lh - rh == 2) {
            int llh = r.left.left == null ? 0 : r.left.left.height;
            int lrh = r.left.right == null ? 0 : r.left.right.height;
            if(llh >= lrh) return rotate(r, false);
            else { // 双旋
                r.left = rotate(r.left, true);
                return rotate(r, false);
            }
        } else if(lh - rh == -2) {
            int rlh = r.right.left == null ? 0 : r.right.left.height;
            int rrh = r.right.right == null ? 0 : r.right.right.height;
            if(rlh <= rrh) return rotate(r, true);
            else { // 双旋
                r.right = rotate(r.right, false);
                return rotate(r, true);
            }
        }
        return r;
    }

    public AVLNode rotate(AVLNode r, boolean dir) {
        AVLNode temp;
        if(dir) { // true 左旋， false 右旋
            temp = r.right; r.right = temp.left; temp.left = r;
        } else {
            temp = r.left; r.left = temp.right; temp.right = r;
        }
        pushup(r); pushup(temp); // 旋转点和根节点都需要重新统计
        return temp;
    }

    public int find(AVLNode r, int n) {
        if(r == null) return 0;
        int left = r.left == null ? 0 : r.left.size;
        if(left >= n) return find(r.left, n);
        else if(left + r.num < n) return find(r.right, n - left - r.num);
        else return r.val;
    }

    public int rank(AVLNode r, int v) {
        if(r == null) return 1;
        else if(r.val > v) return rank(r.left, v);
        else if(r.val < v) return rank(r.right, v) + r.num + (r.left == null ? 0 : r.left.size);
        else return r.left == null ? 1 : r.left.size + 1;
    }

    public int pre(AVLNode r, int v) {
        if(r == null) return Integer.MIN_VALUE;
        else if(r.val >= v) return pre(r.left, v);
        else return Math.max(r.val, pre(r.right, v));
    }

    public int sub(AVLNode r, int v) {
        if(r == null) return Integer.MAX_VALUE;
        else if(r.val <= v) return sub(r.right, v);
        else return Math.min(r.val, sub(r.left, v));
    }

    private static int CUR = 0;
    private static int PRE = 1;
    private static int TOP = 0;
    private static AVLNode[][] TWOSTACK = new AVLNode[2][(int)Math.pow(2,11)];
    public static StringBuilder builder = new StringBuilder();
    public String printTree(AVLNode r) {
        if(r != null) TWOSTACK[CUR][TOP ++] = r;
        builder.append("\r\n");
        while(TOP != 0) {
            int TOP2 = 0;
            for(int i = 0;i < TOP;i ++) {
                AVLNode temp = TWOSTACK[CUR][i];
                if(temp.left != null) TWOSTACK[PRE][TOP2 ++] = temp.left;
                if(temp.right != null) TWOSTACK[PRE][TOP2 ++] = temp.right;
                builder.append(temp.val + "  ");
            } builder.append("\r\n");
            TOP = TOP2;CUR ^= 1;PRE ^= 1;
        } builder.append("\r\n");
        return builder.toString();
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        while(num -- > 0) {
            int opt = sc.nextInt();
            int v = sc.nextInt();
            switch (opt) {
                case 1 : tree.root = tree.insert(tree.root, v); break;
                case 2 : tree.root = tree.delete(tree.root, v); break;
                case 3 : System.out.println(tree.rank(tree.root, v)); break;
                case 4 : System.out.println(tree.find(tree.root, v)); break;
                case 5 : System.out.println(tree.pre(tree.root, v)); break;
                case 6 : System.out.println(tree.sub(tree.root, v)); break;
            }
            //System.out.print(tree.printTree(tree.root));builder.setLength(0);
            //Thread.sleep(200L);
        }
    }
}
