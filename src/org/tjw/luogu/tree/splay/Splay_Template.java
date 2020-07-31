package org.tjw.luogu.tree.splay;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Splay_Template {
    class Node {
        int val;
        int num;
        int size;
        Node father;
        Node left;
        Node right;
    }

    Node root;

    public void pushup(Node r) {
        if(r != null) {
            int left = r.left == null ? 0 : r.left.size;
            int right = r.right == null ? 0 : r.right.size;
            r.size = left + right + r.num;
        }
    }

    public void clear(Node r) {
        if(r.father != null)
            if(getDir(r)) r.father.right = null;
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
        if(dir) {
            fa.right = r.left;
            r.left = fa;
            if(fa.right != null) fa.right.father = fa;
        }
        else {
            fa.left = r.right;
            r.right = fa;
            if(fa.left != null) fa.left.father = fa;
        }
        fa.father = r;
        r.father = za;
        if(za != null) {
            if(za.left != null && za.left.val == fa.val) za.left = r;
            else za.right = r;
        }
        pushup(fa);
        pushup(r);
    }

    // TO ROOT
    public void splay(Node r) {
        Node fa;
        while((fa = r.father) != null) {
            if (fa.father != null) rotate(getDir(r) == getDir(fa) ? fa : r);
            rotate(r);
        }
        root = r;
    }

    public void insert(int v) {
        if(root == null) {
            root = new Node();
            root.val = v;
            root.size ++;
            root.num ++;
            return;
        }
        Node current = root, fa;
        while(true) {
            if(current.val == v) {
                current.num ++;
                current.size ++;
                pushup(current.father);
                splay(current);
                return;
            }
            fa = current;
            current = current.val < v ? current.right : current.left;
            if(current == null) {
                current = new Node();
                current.size ++;
                current.num ++;
                current.val = v;
                current.father = fa;
                if(v > fa.val) fa.right = current;
                else fa.left = current;
                pushup(fa);
                splay(current);
                return;
            }
        }
    }

    // need to splay once
    public int rank(int v) {
        int res = 0;
        Node r = root;
        while(true) {
            if(r == null) return 0;
            if(v < r.val) {
                r = r.left;
            } else {
                res += r.left == null ? 0 : r.left.size;
                if(r.val == v) {
                    splay(r);
                    return res + 1;
                }
                res += r.num;
                r = r.right;
            }
        }
    }

    public int find(Node r, int x) {
        if(r == null) return 0;
        int left = r.left == null ? 0 : r.left.size;
        if(left >= x) {
            return find(r.left, x);
        }
        else if(left + r.num < x) {
            return find(r.right,x - r.num - left);
        }
        else {
            splay(r);
            return r.val;
        }
    }

    public Node pre(int v) {
        Node r = root == null ? null : root.left;
        while(r != null && r.right != null) r = r.right;
        if(r != null) splay(r);
        return r;
    }

    public Node sub(int v) {
        Node r = root == null ? null : root.right;
        while(r != null && r.left != null) r = r.left;
        splay(r);
        return r;
    }

    public void delete(int v) {
        rank(v);
        if(root != null) {
            boolean leftN = root.left == null;
            boolean rightN = root.right == null;
            if (root.num > 1) {
                root.num--;
                pushup(root);
            } else if(leftN && rightN) {
                root = null;
            } else if(leftN) {
                Node r = root;
                root = root.right;
                root.father = null;
                clear(r);
            } else if(rightN) {
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

    private static int st = 0;
    private static int cur = 1;
    private static int pre = 0;
    private static Node[][] stack = new Node[2][10000];
    public String printTree() {
        StringBuilder builder = new StringBuilder();
        List<List<String>> res = new ArrayList<List<String>>();
        if(root != null) stack[cur][st ++] = root;

        while(st != 0) {
            cur ^= 1;
            pre ^= 1;
            int top = 0;
            List<String> levlist = new ArrayList<>();
            boolean contains = false;
            for(int i = 0;i < st ;i ++) {
                Node node = stack[pre][i];
                if(node == null) {
                    stack[cur][top ++] = null;
                    stack[cur][top ++] = null;
                    levlist.add("-------");
                } else {
                    levlist.add(builder.append(String.valueOf(node.val))
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
        Splay_Template splay = new Splay_Template();
        Scanner sc = new Scanner(System.in);
        int opn = sc.nextInt();
        while(opn -- > 0) {
            int opt = sc.nextInt();
            int v = sc.nextInt();
            switch (opt) {
                case 1 : splay.insert(v); break;
                case 2 : splay.delete(v); break;
                case 3 : System.out.println(splay.rank(v)); break;
                case 4 : System.out.println(splay.find(splay.root, v)); break;
                case 5 :
                    splay.insert(v);
                    Node node = splay.pre(v);
                    System.out.println(node == null ? Integer.MIN_VALUE : node.val);
                    splay.delete(v);
                    break;
                case 6 :
                    splay.insert(v);
                    Node node2 = splay.sub(v);
                    System.out.println(node2 == null ? Integer.MAX_VALUE : node2.val);
                    splay.delete(v);
                    break;
            }
            //System.out.println(splay.printTree());
        }
    }
}
