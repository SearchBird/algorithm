package org.tjw.luogu.tree.splay;

import java.util.Scanner;

public class Spley_OI {

    class Node {
        int val;
        int num;
        int size;
        Node father;
        Node left;
        Node right;
        public Node(int val) {
            this.val = val;
        }
    }

    Node root;

    public void pushup(Node r) {
        if (r != null) {
            int left = r.left == null ? 0 : r.left.size;
            int right = r.right == null ? 0 : r.right.size;
            r.size = left + right + r.num;
        }
    }

    // 对象比较麻烦就是 要判空和比较方向
    public void clear(Node r) {
        if (r.father != null)
            if (getDir(r)) r.father.right = null;
            else r.father.left = null;
        r.father = null;
        r.right = r.left = null;
    }

    public boolean getDir(Node r) {
        return r.val == (r.father.right == null ? 0 : r.father.right.val);
    }

    // dir true-left false-right
    public void rotate(Node r) {
        Node fa = r.father;
        Node za = fa.father;

        boolean dir = getDir(r);
        if (dir) {
            fa.right = r.left;
            r.left = fa;
            if (fa.right != null)
                fa.right.father = fa;
        } else {
            fa.left = r.right;
            r.right = fa;
            if (fa.left != null)
                fa.left.father = fa;
        }
        fa.father = r;
        r.father = za;
        if (za != null) {
            if (za.left != null && za.left.val == fa.val)
                za.left = r;
            else
                za.right = r;
        }
        pushup(fa);
        pushup(r);
    }

    // TO ROOT
    public void splay(Node r) {
        Node fa;
        while ((fa = r.father) != null) {
            if (fa.father != null) rotate(getDir(r) == getDir(fa) ? fa : r);
            rotate(r);
        }
        root = r;
    }

    public void insert(int v) {
        if (root == null) {
            root = new Node(v);
            root.num ++;
            pushup(root);
            return;
        }
        Node current = root, fa;
        while (true) {
            if (current.val == v) {
                current.num++;
                current.size++;
                pushup(current.father);
                splay(current);
                return;
            }
            fa = current;
            current = current.val < v ? current.right : current.left;
            if (current == null) {
                current = new Node(v);
                current.num++;
                pushup(current);
                current.father = fa;
                if (v > fa.val)
                    fa.right = current;
                else
                    fa.left = current;
                pushup(fa);
                splay(current);
                return;
            }
        }
    }

    public int rank(int v) {
        int res = 0;
        Node r = root;
        while (true) {
            if (r == null)
                return 0;
            if (v < r.val) {
                r = r.left;
            } else {
                res += r.left == null ? 0 : r.left.size;
                if (r.val == v) {
                    splay(r);
                    return res + 1;
                }
                res += r.num;
                r = r.right;
            }
        }
    }

    // 和 Treap.find 一样逻辑
    public int find(Node r, int x) {
        if (r == null) return 0;
        int left = r.left == null ? 0 : r.left.size;
        if (left >= x) return find(r.left, x);
        else if (left + r.num < x) return find(r.right, x - r.num - left);
        else {
            splay(r);
            return r.val;
        }
    }

    public Node pre(int v) {
        Node r = root == null ? null : root.left;
        while (r != null && r.right != null) r = r.right;
        if (r != null)
            splay(r);
        return r;
    }

    public Node sub(int v) {
        Node r = root == null ? null : root.right;
        while (r != null && r.left != null) r = r.left;
        splay(r);
        return r;
    }

    public void delete(int v) {
        rank(v);
        if (root != null) {
            boolean leftN = root.left == null;
            boolean rightN = root.right == null;
            if (root.num > 1) {
                root.num--;
                pushup(root);
            } else if (leftN && rightN) {
                root = null;
            } else if (leftN) {
                Node r = root;
                root = root.right;
                root.father = null;
                clear(r);
            } else if (rightN) {
                Node r = root;
                root = root.left;
                root.father = null;
                clear(r);
            } else {
                // use pre
                Node r = root;
                splay(pre(r.val));
                Node right = r.right;
                clear(r);
                root.right = right;
                right.father = root;
                pushup(root);
            }
        }
    }
    public static void main(String[] args) {
        Spley_OI splay = new Spley_OI();
        Scanner sc = new Scanner(System.in);
        int opn = sc.nextInt();
        while (opn-- > 0) {
            int opt = sc.nextInt();
            int v = sc.nextInt();
            switch (opt) {
                case 1:
                    splay.insert(v);
                    break;
                case 2:
                    splay.delete(v);
                    break;
                case 3:
                    System.out.println(splay.rank(v));
                    break;
                case 4:
                    System.out.println(splay.find(splay.root, v));
                    break;
                case 5:
                    // 因为 delete 还得调用 pre，如果把 insert delete 放到 pre 那就递归了
                    splay.insert(v);
                    Node node = splay.pre(v);
                    System.out.println(node == null ? Integer.MIN_VALUE : node.val);
                    splay.delete(v);
                    break;
                case 6:
                    // 和 pre 一个意思
                    splay.insert(v);
                    Node node2 = splay.sub(v);
                    System.out.println(node2 == null ? Integer.MAX_VALUE : node2.val);
                    splay.delete(v);
                    break;
            }
        }
    }
}
