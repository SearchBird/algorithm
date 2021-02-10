package org.tjw.leetcode.algorithm.linkedList;

public class FlattenaMultilevelDoublyLinkedList {

    public static void main(String[] args) {
        Solution s = new FlattenaMultilevelDoublyLinkedList().new Solution();

        Node root1 = new Node(1);
        Node root2 = new Node(2);
        Node root3 = new Node(3);

        root1.next = root2;
        root2.prev = root1;
        root1.child = root3;

        Node res = s.flatten(root1);
        while(res != null) {
            System.out.print(res.val + " ");
            res = res.next;
        }
    }

    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
        public Node(int val) {this.val = val;}
    }

    class Solution {
        public Node flatten(Node head) {
            recurtion(head);
            return head;
        }

        public Node recurtion(Node head) {
            Node pre = head;
            while(head != null) {
                pre = head;
                Node next = head.next;
                if(head.child != null) {
                    head.next = head.child;
                    head.child.prev = head;
                    Node last = recurtion(head.child);
                    last.next = next;
                    if(next != null) next.prev = last;
                    else next = last;
                    head.child = null;
                }
                head = next;
            }
            return pre;
        }
    }
}

