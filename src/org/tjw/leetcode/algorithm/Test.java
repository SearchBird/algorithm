package org.tjw.leetcode.algorithm;

import java.util.Map;
import java.util.TreeMap;

public class Test {

    String str = new String("good");
    char[] ch = {'a','b','c'};
    public static void main(String[] args) {
        TreeMap<Integer,Integer> map = new TreeMap<>();
            Integer e = map.firstKey();
    }

    public void change(String str, char ch[]){
        double d = 1.1e1;
    }

    private static int[][] stack = new int[2][1000];
    class Solution {
        public String countAndSay(int n) {
            int pre = 1, cur = 0, idx1 = 0, idx2 = 0;
            stack[cur][idx1 ++] = 1;
            while(n -- > 1) {
                for(int i = 0, len = idx1; i < idx1;) {
                    int tmp = stack[cur][i], cnt = 0;
                    while(stack[cur][i] == tmp) {
                        i ++; cnt ++;
                    }
                    stack[pre][idx2 ++] = cnt;
                    stack[pre][idx2 ++] = tmp;
                }
                pre ^= 1;
                cur ^= 1;
                idx1 = idx2;
                idx2 = 0;
            }
            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < idx1; i ++) builder.append(stack[cur][i]);
            return builder.toString();
        }
    }

}
