package org.tjw.leetcode.algorithm.Recursion;


/**
 *  Swap Nodes in Pairs
 * Solution
 * Given a linked list, swap every two adjacent nodes and return its head.
 *
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 *
 *
 *
 * Example:
 *
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 *
 * [1,2,3]
 * Output:
 * [2,1,3]
 */
public class SwapNodesInPairs {
    public static void main(String[] args) {
        Solution01 solution01 = new SwapNodesInPairs().new Solution01();
        Solution02 solution02 = new SwapNodesInPairs().new Solution02();

        //type01(solution);
        //type02(solution);
        //type03(solution);
        //type04(solution);
        //type05(solution);
        type06(solution01);
        type06(solution02);
    }

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */
    interface Solution{ListNode swapPairs(ListNode head);}


    /**
     *  * [1,2,3]
     *  * Output:
     *  * [2,1,3]
     */
    class Solution02 implements Solution {
        public ListNode swapPairs(ListNode head) {
            if(head == null || head.next == null) return head;
            ListNode next = head.next;
            ListNode node = swap(head);
            if(next == node) {
                head.next = next.next;
                next.next = head;
                return next;
            }
            return head;
        }

        public ListNode swap(ListNode head) {
            ListNode now = head.next;
            ListNode nex = null;
            if(now.next != null) {
                int nextHash = now.next.hashCode();
                nex = swap(now);

                if(now.next.hashCode() == nextHash) {
                    now.next = nex.next;
                    nex.next = now;
                    head.next = nex;
                }
            }
            return head.next;
        }
    }

    /**
     *  * [1,2,3]
     *  * Output:
     *  * [1,3,2]
     */
    class Solution01 implements Solution {
        public ListNode swapPairs(ListNode head) {
            if(head == null || head.next == null) return head;
            ListNode next = head.next;
            ListNode node = swap(head);
            if(next == node) {
                head.next = next.next;
                next.next = head;
                return next;
            }
            return head;
        }

        public ListNode swap(ListNode head) {
            ListNode now = head.next;
            ListNode nex = null;
            if(now.next != null) {
                int nextHash = now.next.hashCode();
                nex = swap(now);

                if(now.next.hashCode() == nextHash) {
                    now.next = nex.next;
                    nex.next = now;
                    head.next = nex;
                }
            }
            return head.next;
        }
    }

    private static void type06(Solution solution) {
        ListNode node01 = new ListNode(1);
        ListNode node02 = new ListNode(2);
        ListNode node03 = new ListNode(3);

        node01.next = node02;
        node02.next = node03;
        int[] nodeArr01 = new int[]{
                node01.hashCode(),
                node02.hashCode(),
                node03.hashCode()};

        ListNode changeNode = solution.swapPairs(node01);

        int[] nodeArr02 = new int[3];
        int index = 0;
        nodeArr02[index] = changeNode.hashCode();
        while(changeNode.next != null) {
            index += 1;
            changeNode = changeNode.next;
            nodeArr02[index] = changeNode.hashCode();
        }

        for(int i = 0;i < nodeArr01.length;i ++) {
            System.out.print(nodeArr01[i] + ",");
        }
        System.out.println();
        for(int i = 0;i < nodeArr02.length;i ++) {
            System.out.print(nodeArr02[i] + ",");
        }
        System.out.println();
    }

    private static void type05(Solution solution) {
        ListNode node01 = new ListNode(1);
        ListNode node02 = new ListNode(2);

        node01.next = node02;
        int[] nodeArr01 = new int[]{
                node01.hashCode(),
                node02.hashCode()};

        ListNode changeNode = solution.swapPairs(node01);

        int[] nodeArr02 = new int[2];
        int index = 0;
        nodeArr02[index] = changeNode.hashCode();
        while(changeNode.next != null) {
            index += 1;
            changeNode = changeNode.next;
            nodeArr02[index] = changeNode.hashCode();
        }

        for(int i = 0;i < nodeArr01.length;i ++) {
            System.out.print(nodeArr01[i] + ",");
        }
        System.out.println();
        for(int i = 0;i < nodeArr02.length;i ++) {
            System.out.print(nodeArr02[i] + ",");
        }
        System.out.println();
    }


    private static void type04(Solution solution) {
        ListNode node01 = null;
        ListNode changeNode = solution.swapPairs(node01);
        System.out.println();
    }

    private static void type03(Solution solution) {
        ListNode node01 = new ListNode(1);

        int[] nodeArr01 = new int[]{
                node01.hashCode()};

        ListNode changeNode = solution.swapPairs(node01);

        int[] nodeArr02 = new int[1];
        int index = 0;
        nodeArr02[index] = changeNode.hashCode();
        while(changeNode.next != null) {
            index += 1;
            changeNode = changeNode.next;
            nodeArr02[index] = changeNode.hashCode();
        }

        for(int i = 0;i < nodeArr01.length;i ++) {
            System.out.print(nodeArr01[i] + ",");
        }
        System.out.println();
        for(int i = 0;i < nodeArr02.length;i ++) {
            System.out.print(nodeArr02[i] + ",");
        }
        System.out.println();
    }

    private static void type02(Solution solution) {
        ListNode node01 = new ListNode(1);
        ListNode node02 = new ListNode(2);
        ListNode node03 = new ListNode(3);
        ListNode node04 = new ListNode(4);

        node01.next = node02;
        node02.next = node03;
        node03.next = node04;
        int[] nodeArr01 = new int[]{
                node01.hashCode(),
                node02.hashCode(),
                node03.hashCode(),
                node04.hashCode()};

        ListNode changeNode = solution.swapPairs(node01);

        int[] nodeArr02 = new int[4];
        int index = 0;
        nodeArr02[index] = changeNode.hashCode();
        while(changeNode.next != null) {
            index += 1;
            changeNode = changeNode.next;
            nodeArr02[index] = changeNode.hashCode();
        }

        for(int i = 0;i < nodeArr01.length;i ++) {
            System.out.print(nodeArr01[i] + ",");
        }
        System.out.println();
        for(int i = 0;i < nodeArr02.length;i ++) {
            System.out.print(nodeArr02[i] + ",");
        }
        System.out.println();
    }

    private static void type01(Solution solution) {
        ListNode node01 = new ListNode(2);
        ListNode node02 = new ListNode(5);
        ListNode node03 = new ListNode(2);
        ListNode node04 = new ListNode(2);
        ListNode node05 = new ListNode(2);
        ListNode node06 = new ListNode(9);
        ListNode node07 = new ListNode(11);

        node01.next = node02;
        node02.next = node03;
        node03.next = node04;
        node04.next = node05;
        node05.next = node06;
        node06.next = node07;
        node07.next = null;
        int[] nodeArr01 = new int[]{
                node01.hashCode(),
                node02.hashCode(),
                node03.hashCode(),
                node04.hashCode(),
                node05.hashCode(),
                node06.hashCode(),
                node07.hashCode()};

        ListNode changeNode = solution.swapPairs(node01);

        int[] nodeArr02 = new int[7];
        int index = 0;
        nodeArr02[index] = changeNode.hashCode();
        while(changeNode.next != null) {
            index += 1;
            changeNode = changeNode.next;
            nodeArr02[index] = changeNode.hashCode();
        }

        for(int i = 0;i < nodeArr01.length;i ++) {
            System.out.print(nodeArr01[i] + ",");
        }
        System.out.println();
        for(int i = 0;i < nodeArr02.length;i ++) {
            System.out.print(nodeArr02[i] + ",");
        }
        System.out.println();
    }




}
