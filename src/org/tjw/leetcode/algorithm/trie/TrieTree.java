package org.tjw.leetcode.algorithm.trie;

import com.sun.deploy.util.StringUtils;

import java.io.*;

public class TrieTree {

    // 最后还有个没完成的设计
    public static void main(String[] a) throws IOException {
        Trie obj = new Trie();
        obj.insert("apple");
        boolean param_2 = obj.search("app");
        boolean param_3 = obj.startsWith("app");
        System.out.println(param_2);
        System.out.println(param_3);
    }

    // 节点
    static class TrieNode {
        private final int BIN = 26;
        private TrieNode[] links = new TrieNode[BIN];
        private boolean isEnd;

        public boolean containKey(char ch) {
            return links[ch - 'a'] != null;
        }

        public TrieNode get(char ch) {
            return links[ch - 'a'];
        }

        public void put(char ch) {
            links[ch - 'a'] = new TrieNode();
        }

        public void setEnd() {
            isEnd = true;
        }

        public boolean isEnd() {
            return isEnd;
        }
    }

    static class Trie {
        private TrieNode node = new TrieNode();

        /** Inserts a word into the trie. */
        public void insert(String word) {
            if(word == null || word.trim() == "") return;
            int len = word.length();
            TrieNode temp = node;
            for(int i = 0;i < len;i ++) {
                char ch = word.charAt(i);
                if(!temp.containKey(ch)) {
                    temp.put(ch);
                }
                temp = temp.get(ch);
            }
            // 插入完毕，该节点设置为末尾节点标记
            temp.setEnd();
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            if(word == null || word.trim() == "") return false;
            TrieNode temp = check(word.length(), word);
            if(temp != null && temp.isEnd()) return true;
            return false;
        }

        private TrieNode check(int len, String word) {
            TrieNode temp = node;
            for(int i = 0;i < len;i ++) {
                char ch = word.charAt(i);
                if(!temp.containKey(ch)) {
                    return null;
                }
                temp = temp.get(ch);
            }
            return temp;
        }
        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            if(prefix == null || prefix.trim() == "") return false;
            TrieNode temp = check(prefix.length(), prefix);
            if(temp != null) return true;
            return false;
        }
    }





    /**
     * 这个很遗憾，没有设计出来，TrieTest_FailTest.txt里面的数据是不通过的，找不到原因
     * Trie trie = new Trie();
     *         InputStreamReader fis =  new InputStreamReader(new FileInputStream(new File("TrieTest_FailTest.txt")));
     *         BufferedReader bis = new BufferedReader(fis);
     *         String line = "";
     *         String[] strs = null;
     *         while((line = bis.readLine()) != null){
     *             strs = line.split(",");
     *         }
     *         for(String str : strs) {
     *             String ss = str.replace("[\"","").replace("\"]","");
     *             trie.insert(ss);
     *             if(trie.search(ss) == false) System.out.println(ss);
     *         }
     */
    static class Trie01 {

        private int[][] tree = new int[50][26];

        /** Initialize your data structure here. */
        public Trie01() {

        }

        public int countPos(int yslot, char ch) {
            int chint = ch - 'a';
            tree[yslot][chint] |= 2;
            return chint;
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            if(word.equals("")) return;
            char[] words = word.toCharArray();
            int len = words.length - 1;
            int prev = countPos(0, words[0]);
            for(int i = 0;i ++ < len;) {
                int preTemp = prev;
                prev = countPos(i, words[i]);
                tree[i - 1][preTemp] |= (1 << prev + 2);
            }
            tree[words.length - 1][prev] |= 1;
        }

        public int checkPre(String prefix) {
            char[] words = prefix.toCharArray();
            lastChint = words[words.length - 1] - 'a';
            int yslot = 0;
            if((tree[yslot][words[0] - 'a'] & 2) == 0) return -1;
            int len = words.length - 1;
            for(yslot = 0;yslot < len;yslot ++) {
                int chint = words[yslot] - 'a';
                int pos = 1 << words[yslot + 1] - 'a' + 2;
                if((tree[yslot][chint] & pos) == 0) return -1;
            }
            return yslot;
        }
        int lastChint = 0;
        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            int yslot = checkPre(word);
            if(yslot == -1) return false;
            if((tree[yslot][lastChint] & 1) == 0) return false;
            return true;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            if(checkPre(prefix) == -1) return false;
            return true;
        }
    }


}
