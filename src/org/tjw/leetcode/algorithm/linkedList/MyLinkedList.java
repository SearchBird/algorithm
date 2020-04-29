package org.tjw.leetcode.algorithm.linkedList;

public class MyLinkedList {

    int size = 0;
    MyNode headNode;

    /** Initialize your data structure here. */
    public MyLinkedList() {

    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        int i = 0;
        MyNode node = headNode;
        A:if(node != null) {
            while(i < index) {
                if(node.next == null) break A;
                node = node.next;
                i ++;
            }
            return node.val;
        }
        return -1;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        MyNode head = new MyNode(val);
        head.next = headNode;
        headNode = head;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        MyNode node = headNode;
        if(node != null) {
            while(node.next != null) {
                node = node.next;
            }
            node.next = new MyNode(val);
        } else {
            headNode = new MyNode(val);
        }
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if(index == 0) {
            addAtHead(val);
            return;
        }
        int i = 0;
        MyNode node = headNode;
        if(node != null) {
            while(i < index - 1) {
                if(node.next == null) return;
                node = node.next;
                i ++;
            }
            MyNode next = node.next;
            node.next = new MyNode(val);
            node.next.next = next;
        }
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        int i = 0;
        MyNode node = headNode;
        if(node != null) {
            if(index == 0) {
                headNode = node.next;
                return;
            }
            while(i < index - 1) {
                if(node.next == null) return;
                node = node.next;
                i ++;
            }
            if(node.next != null) {
                node.next = node.next.next;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        MyNode node = headNode;
        while(node != null) {
            builder.append(node.val).append(",");
            node = node.next;
        }
        return builder.toString();
    }

    class MyNode {
        MyNode next;
        int val;
        MyNode(int val) {this.val = val;}
    }
}
