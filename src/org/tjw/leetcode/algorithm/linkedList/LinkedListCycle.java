package org.tjw.leetcode.algorithm.linkedList;

/**
 * Given a linked list, determine if it has a cycle in it.
 *
 * To represent a cycle in the given linked list, we use an integer
 * pos which represents the position (0-indexed) in the linked list where
 * tail connects to. If pos is -1, then there is no cycle in the linked list.
 *
 *
 *
 * Example 1:
 *
 * Input: head = [3,2,0,-4], pos = 1
 * Output: true
 * Explanation: There is a cycle in the linked list, where tail connects
 * to the second node.
 *
 *
 * Example 2:
 *
 * Input: head = [1,2], pos = 0
 * Output: true
 * Explanation: There is a cycle in the linked list, where tail
 * connects to the first node.
 *
 *
 * Example 3:
 *
 * Input: head = [1], pos = -1
 * Output: false
 * Explanation: There is no cycle in the linked list.
 */
public class LinkedListCycle {

    public static void main(String[] args) {
        Solution solution = new LinkedListCycle().new Solution();
        ListNode head = new ListNode(1);
        ListNode node01 = new ListNode(2);
        ListNode node02 = new ListNode(3);
        ListNode node03 = new ListNode(4);
        head.next = node01;
        node01.next = node02;
        node02.next = node03;
        node03.next = node02;
        System.out.println(solution.hasCycle(head));
    }

    class Solution {
        public boolean hasCycle(ListNode head) {
            if(head == null || head.next == null) return false;
            ListNode runner01 = head;
            ListNode runner02 = head;
            while(runner02.next != null && runner02.next.next != null) {
                runner01 = runner01.next;
                runner02 = runner02.next.next;
                if(runner01.hashCode() == runner02.hashCode()) return true;
            }
            return false;
        }
    }
}
