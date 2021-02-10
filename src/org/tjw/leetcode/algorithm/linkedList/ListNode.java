package org.tjw.leetcode.algorithm.linkedList;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) { val = x; }

    public static ListNode buildList(int[] arr) {
        ListNode root = new ListNode(-1), next = root;
        for(int i : arr) {
            next.next = new ListNode(i);
            next = next.next;
        }
        return root.next;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}
