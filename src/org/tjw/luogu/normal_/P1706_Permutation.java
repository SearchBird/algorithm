package org.tjw.luogu.normal_;

import java.util.Scanner;

public class P1706_Permutation {
    private static String space = "    ";
    private static StringBuilder builder = new StringBuilder();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        recurtion(n, new boolean[n + 1], 0, 0);
    }

    private static void recurtion(int n, boolean[] visited, int num, int size) {
        if(num == n) {
            System.out.println(builder.toString()); return;
        }
        for(int i = 1; i <= n; i ++) {
            if(!visited[i]) {
                visited[i] = true;
                builder.append(space).append(i);
                recurtion(n, visited, num + 1, builder.length());
                visited[i] = false;
                builder.setLength(size);
            }
        }
    }
}
