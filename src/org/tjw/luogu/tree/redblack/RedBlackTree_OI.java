package org.tjw.luogu.tree.redblack;
import java.util.Scanner;

public class RedBlackTree_OI {
    private static boolean RED = true;
    private static boolean BLC = false;
    class RBNode {
        boolean color;
        RBNode right, left;
        int val, num, size;
        RBNode(int val) {
            this.val = val;
            this.num = this.size = 1;
            this.color = RED;
        }
    }

    RBNode ROOT;

    public void pushup(RBNode r) {
        if (r != null) {
            int left = r.left == null ? 0 : r.left.size;
            int right = r.right == null ? 0 : r.right.size;
            r.size = r.num + left + right;
        }
    }
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
        return fixUp(root);
    }

    public RBNode fixUp(RBNode root) {
        if (root == null)
            return root;
        boolean leftRed = root.left != null && root.left.color == RED;
        boolean leftLeftRed = root.left != null && root.left.left != null && root.left.left.color == RED;
        boolean rightRed = root.right != null && root.right.color == RED;

        if (rightRed && !leftRed)
            root = rotate(root, true);
        if (leftRed && leftLeftRed)
            root = rotate(root, false);
        if (leftRed && rightRed)
            colorFlip(root);
        pushup(root);
        return root;
    }

    public void colorFlip(RBNode root) {
        root.color = RED;
        if (root.left != null)
            root.left.color = BLC;
        if (root.right != null)
            root.right.color = BLC;
    }

    public RBNode rotate(RBNode root, boolean dir) {
        RBNode temp;
        if (dir) {
            temp = root.right;
            root.right = temp.left;
            temp.left = root;
        } else {
            temp = root.left;
            root.left = temp.right;
            temp.right = root;
        }
        pushup(root);
        pushup(temp);
        return temp;
    }

    public RBNode delete(RBNode root, int val) {
        if (root == null)
            return root;
        boolean leftRed = root.left != null && root.left.color == RED;
        boolean leftLeftRed = root.left != null && root.left.left != null && root.left.left.color == RED;
        boolean rightRed = root.right != null && root.right.color == RED;
        boolean rightLeftRed = root.right != null && root.right.left != null && root.right.left.color == RED;

        if (root.val > val) {
            if (!leftRed && !leftLeftRed)
                root = moveRedLeft(root);
            root.left = delete(root.left, val);
        } else {
            if (leftRed)
                root = rotate(root, false);
            if (root.val == val && root.right == null) {
                root.num--;
                root.size--;
                if (root.num == 0)
                    return root.left;
                return root;
            }
            if (!rightRed && !rightLeftRed)
                root = moveRedRight(root);
            if (root.val == val) {
                root.num--;
                if (root.num == 0) {
                    exchange(root, getMin(root.right));
                    root.right = deleteMin(root.right);
                }
                pushup(root);
                return root;
            } else { // root.val > val
                root.right = delete(root.right, val);
            }
        }
        return fixUp(root);
    }

    private RBNode deleteMin(RBNode r) {
        if (r.left == null)
            return r.right;

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
        while (r.left != null) r = r.left;
        return r;
    }

    private void exchange(RBNode r, RBNode e) {
        r.val = e.val;
        r.num = e.num;
        pushup(r);
    }

    public RBNode moveRedLeft(RBNode r) {
        colorFlip(r);
        boolean rightLeftRed = r.right != null && r.right.left != null && r.right.left.color == RED;

        if (rightLeftRed) {
            r.right = rotate(r.right, false);
            r = rotate(r, true);
            colorFlip(r);
        }
        return r;
    }

    public RBNode moveRedRight(RBNode r) {
        colorFlip(r);
        boolean leftLeftRed = r.left != null && r.left.left != null && r.left.left.color == RED;

        if (leftLeftRed) {
            r = rotate(r, false);
            colorFlip(r);
        }
        return r;
    }

    public int find(RBNode r, int n) {
        if (r == null)
            return 0;
        int left = r.left == null ? 0 : r.left.size;
        if (left >= n)
            return find(r.left, n);
        else if (left + r.num < n)
            return find(r.right, n - left - r.num);
        else
            return r.val;
    }

    public int rank(RBNode r, int v) {
        if (r == null)
            return 1;
        else if (r.val > v)
            return rank(r.left, v);
        else if (r.val < v)
            return rank(r.right, v) + (r.left == null ? 0 : r.left.size) + r.num;
        else
            return r.left == null ? 1 : r.left.size + 1;
    }

    public int pre(RBNode r, int v) {
        if (r == null)
            return Integer.MIN_VALUE;
        else if (r.val >= v)
            return pre(r.left, v);
        else
            return Math.max(pre(r.right, v), r.val);
    }

    public int sub(RBNode r, int v) {
        if (r == null)
            return Integer.MAX_VALUE;
        else if (r.val <= v)
            return sub(r.right, v);
        else
            return Math.min(sub(r.left, v), r.val);
    }

    private static Scanner sc = new Scanner(System.in);
    private static int num;
    public static void main(String[] args) {
        num = sc.nextInt();
        RedBlackTree_OI tree = new RedBlackTree_OI();
        while (num-- > 0) {
            int op = sc.nextInt();
            int val = sc.nextInt();
            switch (op) {
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
                    System.out.println(tree.sub(tree.ROOT, val));
            }
        }
    }
}
