package org.tjw.leetcode.algorithm.changlle30;


import java.util.Arrays;

public class ReorderLogFiles {

    public static void main(String[] args) throws Throwable {
        ReorderLogFiles.Solution s = new ReorderLogFiles().new Solution();
        System.out.println((char)('z' + 1));
        String[] res = s.reorderLogFiles(new String[]{"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"});
        for(String str : res) System.out.print(str + "，");
    }

    private static char d = 'z';
    class Solution {
        public String[] reorderLogFiles(String[] logs) {
            int len = logs.length, index = 1;
            Node[] nodes = new Node[len];
            for(int i = 0; i < len; i ++) {
                int begin = logs[i].indexOf(" "), l = logs[i].length();
                char[] hash = logs[i].substring(begin + 1, l).toCharArray(); // 思路根据 hash 的出来
                char[] preHash = logs[i].substring(0, begin + 1).toCharArray();
                Node node = new Node();
                int l1 = hash.length, l2 = preHash.length;

                for(int j = 0; j < l1; j ++) // 把所有数字都改成 z + 1 排序比字母后
                    if(hash[j] > ' ' && hash[j] < 'a') {
                        hash[j] = (char)(d + index ++); break;
                    }
                for(int j = 0; j < l2; j ++)
                    if(preHash[j] > ' ' && preHash[j] < 'a') {
                        preHash[j] = (char)(d + index ++); break;
                    }
                node.hash = new String(hash);
                node.preHash = new String(preHash);

                node.val = logs[i];
                nodes[i] = node;
            }
            // 然后先排序后缀，再排序前缀
            Arrays.sort(nodes, (o1, o2) -> o1.hash.equals(o2.hash) ? (o1.preHash.compareTo(o2.preHash)) : o1.hash.compareTo(o2.hash));
            String res[] = new String[len];
            for(int i = 0; i < len; i ++) res[i] = nodes[i].val;
            return res;
        }
    }

    class Node {
        String val, hash, preHash;
    }
}
