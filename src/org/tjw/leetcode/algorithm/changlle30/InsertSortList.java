package org.tjw.leetcode.algorithm.changlle30;

import org.tjw.leetcode.algorithm.linkedList.ListNode;

import java.util.Arrays;

public class InsertSortList {

    public static void main(String[] args) {
        Solution s = new InsertSortList().new Solution();

        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(1);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);

        n1.next = n2;
        n2.next = n3;

        ListNode n = s.insertionSortList(n1);

        while(n != null) {
            System.out.println(n.val);
            n = n.next;
        }
    }

    private static ListNode node = new ListNode(-1);
    private static int[] arr = new int[10000];
    class Solution {
        public ListNode insertionSortList(ListNode head) {
            if(head != null) {
                int len = 0;
                ListNode h = head;
                while(head != null) {
                    arr[len ++] = head.val;
                    head = head.next;
                }
                int[] newArr = Arrays.copyOf(arr, len);
                Arrays.sort(newArr);
                head = h;
                int i = 0;
                while(head != null) {
                    head.val = newArr[i ++];
                    head = head.next;
                }
                return h;
            }
            return null;
        }
    }
}