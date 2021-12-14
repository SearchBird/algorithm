package org.tjw.leetcode.algorithm.string;

import java.util.ArrayList;
import java.util.List;

public class SuggestedProducts {

    public static void main(String[] args) {
        Solution s = new SuggestedProducts().new Solution();
        s.suggestedProducts(new String[]{"aab", "aaabb", "bbc"}, "ab");
    }

    class Solution {
        public List<List<String>> suggestedProducts(String[] products, String searchWord) {
            TireNode root = new TireNode();
            for(String str : products) {
                char[] chs = str.toCharArray();
                TireNode node = root;
                for(int i = 0, len = str.length(); i < len; i ++) {
                    int solt = chs[i] - 'a';
                    if(node.child[solt] == null) node.child[solt] = new TireNode();
                    node = node.child[solt];
                }
                node.isEnd = true;
                node.val = str;
            }

            char[] sch = searchWord.toCharArray();
            List<List<String>> res = new ArrayList<List<String>>();
            for(int i = 0, len = searchWord.length(); i < len; i ++) {
                char ch = sch[i];
                List<String> list = new ArrayList<String>();
                if(root != null) root = root.child[ch - 'a'];
                if(root != null) recurtion(list, root, 0);
                res.add(list);
            }
            return res;
        }

        public int recurtion(List<String> list, TireNode root, int num) {
            if(root.isEnd) {
                list.add(root.val); num ++;
            }
            if(num == 3) return 3;
            for(int i = 0; i < 26; i ++) {
                TireNode node = root.child[i];
                if(node != null) {
                    num = recurtion(list, node, num);
                    if(num == 3) return 3;
                }
            }
            return num;
        }
    }

    class TireNode {
        TireNode[] child = new TireNode[26];
        String val;
        boolean isEnd;
    }
}
