package org.tjw.openjudge;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * http://poj.org/problem?id=2823
 */
public class poj_monthly_2006 {
    private static int min;
    private static int max;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), k = sc.nextInt(), nd = n - k, minArrIndex = 0, maxArrIndex = 0, top = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        int[] minArr = new int[n], maxArr = new int[n];
        max = min = sc.nextInt();
        queue.add(min);
        while(k -- > 1) {
            top = check(queue, sc.nextInt(), ++ top);
        }
        minArr[minArrIndex ++] = min;
        maxArr[maxArrIndex ++] = max;
        for(int i = nd;i > 0;i --) {
            top = check(queue, sc.nextInt(), ++ top);
            minArr[minArrIndex ++] = min;
            maxArr[maxArrIndex ++] = max;
        }
        for(int i = 0;i <= nd;i ++) {
            System.out.print(minArr[i] + " ");
        }
        System.out.println();
        for(int i = 0;i <= nd;i ++) {
            System.out.print(maxArr[i] + " ");
        }
    }

    public static int check(LinkedList<Integer> queue, int cur, int top) {
        while(top > 0)
            if(queue.getLast() < cur) {
                queue.removeLast();
                top --;
                max = Math.max(max, cur);
            }
            else {
                queue.add(cur);
                min = Math.min(min, cur);
                return top;
            }
        queue.addLast(cur);
        return top;
    }
}
