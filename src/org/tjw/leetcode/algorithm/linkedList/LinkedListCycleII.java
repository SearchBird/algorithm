package org.tjw.leetcode.algorithm.linkedList;

/**
 * Given a linked list, return the node where the cycle begins.
 * If there is no cycle, return null.
 *
 * To represent a cycle in the given linked list, we use an integer
 * pos which represents the position (0-indexed) in the linked list where
 * tail connects to. If pos is -1, then there is no cycle in the linked list.
 *
 * Note: Do not modify the linked list.
 *
 *
 *
 * Example 1:
 *
 * Input: head = [3,2,0,-4], pos = 1
 * Output: tail connects to node index 1
 * Explanation: There is a cycle in the linked list, where tail connects to
 * the second node.
 *
 *
 * Example 2:
 *
 * Input: head = [1,2], pos = 0
 * Output: tail connects to node index 0
 * Explanation: There is a cycle in the linked list, where tail connects
 * to the first node.
 *
 *
 * Example 3:
 *
 * Input: head = [1], pos = -1
 * Output: no cycle
 * Explanation: There is no cycle in the linked list.
 *
 *
 *
 *
 * Follow-up:
 * Can you solve it without using extra space?
 */
public class LinkedListCycleII {

    public static void main(String[] args) {
        Solution solution = new LinkedListCycleII().new Solution();
        ListNode head = new ListNode(-1);
        ListNode node01 = new ListNode(-7);
        ListNode node02 = new ListNode(7);
        ListNode node03 = new ListNode(-4);
        ListNode node04 = new ListNode(19);
        ListNode node05 = new ListNode(6);
        ListNode node06 = new ListNode(-9);
        ListNode node07 = new ListNode(-5);
        ListNode node08 = new ListNode(-2);
        ListNode node09 = new ListNode(-5);
        head.next = node01;
        node01.next = node02;
        node02.next = node03;
        node03.next = node03;
        System.out.println(solution.detectCycle(head));
    }

    class Solution {
        public ListNode detectCycle(ListNode head) {
            if(head == null || head.next == null) return null;
            ListNode runner01 = head;
            ListNode runner02 = head;
            while(true) {
                if(runner02.next == null || runner02.next.next == null) return null;
                runner01 = runner01.next;
                runner02 = runner02.next.next;
                if(runner01 == runner02) break;
            }

            runner02 = head;
            while(runner01 != runner02) {
                runner01 = runner01.next;
                runner02 = runner02.next;
            }
            return runner02;
        }
    }
}
