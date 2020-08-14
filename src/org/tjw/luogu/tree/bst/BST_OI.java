package org.tjw.luogu.tree.bst;

import java.util.Scanner;

public class BST_OI {
    class Node {
        int val;
        Node left;
        Node right;
        int num;
        int size;
        Node(int val) {
            this.val = val;
            this.num = 1;
        }
    }

    Node root;

    public void pushup(Node r) {
        if (r == null)
            return;
        int left = r.left == null ? 0 : r.left.size;
        int right = r.right == null ? 0 : r.right.size;
        r.size = left + right + r.num;
    }

    public Node rotate(Node r, boolean dir) {
        Node child = dir ? r.right : r.left;
        if (dir) {
            r.right = child.left;
            child.left = r;
        } else {
            r.left = child.right;
            child.right = r;
        }
        pushup(r);
        pushup(child);
        return child;
    }

    public Node insert(Node r, int v) {
        if (r == null)
            r = new Node(v);
        else if (r.val == v)
            r.num++;
        else if (r.val > v)
            r.left = insert(r.left, v);
        else
            r.right = insert(r.right, v);
        pushup(r);
        return r;
    }

    public void clear(Node r) {
        if (r != null) {
            r.left = null;
            r.right = null;
        }
    }

    public Node delete(Node r, int v) {
        if (r == null)
            return r;
        else if (r.val < v)
            r.right = delete(r.right, v);
        else if (r.val > v)
            r.left = delete(r.left, v);
        else {
            r.num--;
            if (r.num == 0) {
                boolean ln = r.left == null;
                boolean rn = r.right == null;
                if (ln && rn)
                    return null;
                else if (ln) {
                    Node res = r.right;
                    clear(r);
                    return res;
                } else if (rn) {
                    Node res = r.left;
                    clear(r);
                    return res;
                } else {
                    Node res = deletemin(r.left);
                    res.left = res.val == r.left.val ? r.left.left : r.left;
                    res.right = r.right;
                    clear(r);
                    pushup(res);
                    return res;
                }
            }
        }
        pushup(r);
        return r;
    }

    public Node deletemin(Node r) {
        if (r.right == null)
            return r;
        else {
            Node res = deletemin(r.right);
            if (r.right.val == res.val)
                r.right = res.left;
            r.size -= res.num;
            return res;
        }
    }

    public Node find(Node r, int n) {
        if (r == null)
            return null;
        int left = r.left == null ? 0 : r.left.size;
        if (left >= n)
            return find(r.left, n);
        else if (n > left + r.num)
            return find(r.right, n - left - r.num);
        return r;
    }

    public int rank(Node r, int v) {
        if (r == null)
            return Integer.MIN_VALUE;
        else if (r.val > v)
            return rank(r.left, v);
        else if (r.val < v)
            return r.num + rank(r.right, v) + (r.left == null ? 0 : r.left.size);
        else
            return r.left == null ? 1 : r.left.size + 1;
    }

    public int pre(Node r, int v) {
        if (r == null)
            return Integer.MIN_VALUE;
        else if (r.val >= v)
            return pre(r.left, v);
        else
            return Math.max(r.val, pre(r.right, v));
    }

    public int sub(Node r, int v) {
        if (r == null)
            return Integer.MAX_VALUE;
        else if (r.val <= v)
            return sub(r.right, v);
        else
            return Math.min(r.val, sub(r.left, v));
    }

    public Node findMax(Node r) {
        if (r == null || r.right == null)
            return r;
        else
            return findMax(r.right);
    }

    public Node findMin(Node r) {
        if (r == null || r.left == null)
            return r;
        else
            return findMin(r.left);
    }

    public static void main(String[] args) {
        BST_OI tree = new BST_OI();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while (n-- > 0) {
            int op = sc.nextInt();
            int v = sc.nextInt();
            switch (op) {
                case 1:
                    tree.root = tree.insert(tree.root, v);
                    break;
                case 2:
                    tree.root = tree.delete(tree.root, v);
                    break;
                case 3:
                    System.out.println(tree.rank(tree.root, v));
                    break;
                case 4:
                    Node find = tree.find(tree.root, v);
                    System.out.println(find == null ? -1 : find.val);
                    break;
                case 5:
                    System.out.println(tree.pre(tree.root, v));
                    break;
                case 6:
                    System.out.println(tree.sub(tree.root, v));
                    break;
                case 7:
                    Node max = tree.findMax(tree.root);
                    System.out.println(max == null ? 0 : max.val);
                    break;
                case 8:
                    Node min = tree.findMin(tree.root);
                    System.out.println(min == null ? 0 : min.val);
                    break;
            }
        }
    }
}
