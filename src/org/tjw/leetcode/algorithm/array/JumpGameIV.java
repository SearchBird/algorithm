package org.tjw.leetcode.algorithm.array;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JumpGameIV {

    public static void main(String[] args) throws Throwable {
        Solution s = new JumpGameIV().new Solution();
        System.out.println(s.minJumps(new int[]{100,-23,-23,404,100,23,23,23,3,404})); // 3
        System.out.println(s.minJumps(new int[]{7})); // 0
        System.out.println(s.minJumps(new int[]{7,6,9,6,9,6,9,7})); // 1
        System.out.println(s.minJumps(new int[]{6,1,9})); // 2
        System.out.println(s.minJumps(new int[]{11,7,8,13,7,7,7,7,7,7,22,12,13})); // 4
        System.out.println(s.minJumps(new int[]{11,22,7,7,7,7,7,7,7,22,13})); // 3

        BufferedReader reader = new BufferedReader(new FileReader("E:\\glib\\algorithm\\src\\org\\tjw\\leetcode\\algorithm\\array\\data\\jumpgameiv"));
        String[] temp = reader.readLine().split(",");
        int[] test = new int[temp.length];
        for(int i = 0;i < temp.length;i ++) test[i] = Integer.valueOf(temp[i]);
        System.out.println(s.minJumps(test)); // 3

    }

    class Solution {
        public int minJumps(int[] arr) {
            int len = arr.length, begin = 0, end = 1, count = 0;
            int queue[] = new int[len];
            boolean[] visited = new boolean[len];
            visited[0] = true;
            Map<Integer, List<Integer>> cache = new HashMap<>();
            for(int i = 0; i < len; i++) {
                cache.putIfAbsent(arr[i], new ArrayList<Integer>());
                cache.get(arr[i]).add(i);
            }
            while(begin < end) {
                int e = end;
                for(; begin < e; begin ++) {
                    int temp = queue[begin], bTemp = temp - 1, nTemp = temp + 1;
                    if(temp == len - 1) return count;
                    if(cache.containsKey(arr[temp]))
                        for(Integer index : cache.get(arr[temp]))
                            if(!visited[index] && index != temp) {
                                queue[end ++] = index;
                                visited[index] = true;
                            }
                    cache.remove(arr[temp]);
                    if(bTemp > 0 && !visited[bTemp])  {
                        queue[end ++] = bTemp;
                        visited[bTemp] = true;
                    }
                    if(nTemp < len && !visited[nTemp]) {
                        queue[end ++] = nTemp;
                        visited[nTemp] = true;
                    }
                }
                count ++;
            }
            return -1;
        }
    }
}
