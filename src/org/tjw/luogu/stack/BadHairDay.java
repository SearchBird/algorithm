package org.tjw.luogu.stack;

import java.util.Scanner;

public class BadHairDay {
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int n, top;
        long res;
        n = sc.nextInt();
        int[] stack = new int[n], height = new int[n];
        top = 0;
        res = 0;
        stack[top] = 0;
        height[0] = sc.nextInt();
        for (int i = 1; i < n; i++) {
            height[i] = sc.nextInt();
            while (top >= 0 && height[i] >= height[stack[top]])
                res += i - stack[top--] - 1;
            stack[++top] = i;
        }
        while (top >= 0) res += n - stack[top--] - 1;
        System.out.println(res);
    }
}
